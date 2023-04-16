package com.sparrow.mini.chat.seq.impl;

import com.sparrow.mini.chat.seq.entity.PushSceneResult;
import com.sparrow.mini.chat.seq.entity.SeqMsg;
import com.sparrow.mini.chat.seq.interfaces.IPushResultHandler;
import com.sparrow.mini.chat.seq.interfaces.ISceneSDK;
import com.sparrow.mini.chat.seq.utils.SyncMMKV;
import com.sparrow.mini.chat.seq.utils.SyncMsgCache;
import com.sparrow.mini.chat.thread.ThreadPool;

public abstract class PushResultHandler implements IPushResultHandler {

    @Override
    public void handleResult(PushSceneResult result) {
        // 数据操作
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
        if (result.isTriggerSync()) {
            SyncServiceManager.get().getService(result.getScene()).startSync();
            if (result.getSleepTs() > 0) {
                ThreadPool.get().addDelayTask(() -> {
                        SyncServiceManager.get().getService(result.getScene()).joinSync();
                }, result.getSleepTs());
            } else {
                SyncServiceManager.get().getService(result.getScene()).joinSync();
            }
        } else if (result.isReset()) {
            setCurSeqId(0);
        }
    }

    public long getCurSeqId() {
        return SyncMMKV.get().getSeqId(getScene());
    }

    public void setCurSeqId(long seqId) {
        SyncMMKV.get().setSeqId(getScene(), seqId);
    }
}
