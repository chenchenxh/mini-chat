package com.sparrow.mini.chat.seq.impl;

import com.sparrow.mini.chat.seq.entity.SyncItem;
import com.sparrow.mini.chat.seq.entity.SyncSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 同步队列
 * */
public class SyncTaskQueue {
    private static final SyncTaskQueue instance = new SyncTaskQueue();
    private LinkedBlockingQueue<SyncTask> queue = new LinkedBlockingQueue<>();

    private SyncTaskQueue() {

    }

    public static SyncTaskQueue get() {
        return instance;
    }

    public void put(SyncTask task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tryRunTask();
    }

    private void tryRunTask() {
        // todo 增加聚合操作
        aggregate().run();
    }

    private SyncTask aggregate() {
        List<SyncTask> tasks = new ArrayList<>();
        queue.drainTo(tasks);

        List<SyncItem> syncItems = new ArrayList<>();
        for (SyncTask task : tasks) {
            syncItems.addAll(task.getSyncItems());
        }
        return new SyncTask(syncItems, SyncSource.SYNC_SOURCE_AGGREGATE);
    }
}
