package com.sparrow.mini.chat.seq.impl;

import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_BLOCK;
import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_CREATE;
import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_READY;
import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_RUNNING;
import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_STOP;
import static com.sparrow.mini.chat.seq.entity.SyncState.SYNC_STATE_SUSPEND;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sparrow.mini.chat.http.CommonHttpCallback;
import com.sparrow.mini.chat.seq.entity.SeqMsg;
import com.sparrow.mini.chat.seq.entity.SyncSceneResult;
import com.sparrow.mini.chat.seq.interfaces.ISceneSDK;
import com.sparrow.mini.chat.seq.interfaces.ISyncService;
import com.sparrow.mini.chat.seq.entity.SyncState;
import com.sparrow.mini.chat.seq.utils.SyncMsgCache;
import com.sparrow.mini.chat.seq.utils.SyncMMKV;
import com.sparrow.mini.chat.thread.ThreadPool;

public abstract class SyncService implements ISyncService {
    protected MutableLiveData<SyncState> syncState = new MutableLiveData<>(SYNC_STATE_CREATE);

    @Override
    public void startSync() {
        if (syncState.getValue() != SYNC_STATE_CREATE) {
            return;
        }
        syncState.setValue(SYNC_STATE_READY);
        SyncServiceManager.get().register(this);
    }

    @Override
    public void joinSync() {
        if (syncState.getValue() == SYNC_STATE_CREATE
                || syncState.getValue() == SYNC_STATE_RUNNING
                || syncState.getValue() == SYNC_STATE_STOP) {
            return;
        }
        syncState.setValue(SYNC_STATE_RUNNING);
        SyncTaskQueue.get().put(createSyncTask());
    }

    @Override
    public void sleepSync(int timeOut) {
        syncState.setValue(SYNC_STATE_SUSPEND);
        ThreadPool.get().addDelayTask(() -> {
            if (syncState.getValue() == SYNC_STATE_SUSPEND) {
                joinSync();
            }
        }, timeOut);
    }

    @Override
    public void waitSync() {
        syncState.setValue(SYNC_STATE_BLOCK);
    }

    @Override
    public void stopSync() {
        syncState.setValue(SYNC_STATE_STOP);
        SyncServiceManager.get().unregister(this);
    }

    @Override
    public LiveData<SyncState> getSyncState() {
        return this.syncState;
    }

    @Override
    public void handleResult(SyncSceneResult result) {
        if (result.getScene() != getScene()) {
            return;
        }

        // 数据操作，基于baseSeqId的n条顺序msg
        // 示例：从处理失败之后的seqId不递增
        if (result.getBaseSeqId() <= getCurSeqId() && result.getData() != null && result.getData().size() > 0) {
            for (SeqMsg seqMsg : result.getData()) {
                // 直接存缓存，业务启动再去缓存读取并处理，可以加快sync速度，但也会有缓存文件损坏的风险
                SyncMsgCache.get(result.getScene()).setMsg(seqMsg.getSeqId(), seqMsg.toString());
                if (seqMsg.getSeqId() > getCurSeqId()) {
                    setCurSeqId(seqMsg.getSeqId());
                }
            }
            // 弱通知sdk处理
            ISceneSDK msgSDK = SceneSDKManager.getInstance().getMsgSDK(result.getScene());
            if (msgSDK != null) {
                msgSDK.onSeqMsgArrived();
            }
        }

        // 功能操作
        if (result.isWaitSync()) {
            waitSync();
        } else if (result.getSleepTs() > 0) {
            sleepSync(result.getSleepTs());
        } else if (result.isReset()) {
            setCurSeqId(0);
        } else if (result.isHasMore()) {
            joinSync();
        }
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        // 可根据特定errorCode做调整
    }

    @Override
    public void handleNetWorkError(CommonHttpCallback.Error error) {

    }

    public long getCurSeqId() {
        return SyncMMKV.get().getSeqId(getScene());
    }

    public void setCurSeqId(long seqId) {
        SyncMMKV.get().setSeqId(getScene(), seqId);
    }
}
