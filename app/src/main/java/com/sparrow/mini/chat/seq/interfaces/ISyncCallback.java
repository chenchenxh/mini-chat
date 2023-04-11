package com.sparrow.mini.chat.seq.interfaces;

import com.sparrow.mini.chat.seq.entity.SyncSceneResult;

public interface ISyncCallback {
    void onSuccess(SyncSceneResult msg);
    void onError();
}
