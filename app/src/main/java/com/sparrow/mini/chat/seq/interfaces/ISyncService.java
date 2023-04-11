package com.sparrow.mini.chat.seq.interfaces;

import androidx.lifecycle.LiveData;

import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.entity.SyncState;
import com.sparrow.mini.chat.seq.impl.SyncTask;

/**
 * 管理各自的同步服务：启动时间，状态变化
 * */
public interface ISyncService extends ISyncResultHandler {
    /**
     * 开始处理response
     * */
    void startSync();

    /**
     * 开始同步任务
     * */
    void joinSync();

    /**
     * 暂停同步任务；继续处理response
     * */
    void sleepSync(int timeOut);

    /**
     * 任务出错或服务端反馈，等待触发时机joinSync
     * 暂停同步任务；继续处理response
     * */
    void waitSync();

    /**
     * 停止同步任务；停止处理response
     * */
    void stopSync();

    /**
     * 获取任务状态
     * */
    LiveData<SyncState> getSyncState();

    /**
     * 获取Scene
     * */
    Scene getScene();

    SyncTask createSyncTask();
}
