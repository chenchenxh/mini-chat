package com.sparrow.mini.chat.sdk.impl;

import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.entity.SyncItem;
import com.sparrow.mini.chat.seq.entity.SyncSource;
import com.sparrow.mini.chat.seq.impl.SyncService;
import com.sparrow.mini.chat.seq.impl.SyncTask;

import java.util.Collections;

public class ExampleSyncService extends SyncService {
    @Override
    public Scene getScene() {
        return Scene.SCENE_EXAMPLE;
    }

    @Override
    public SyncTask createSyncTask() {
        SyncItem syncItem = new SyncItem(getCurSeqId(), getScene());
        return new SyncTask(Collections.singletonList(syncItem), SyncSource.SYNC_SOURCE_EXAMPLE);
    }
}
