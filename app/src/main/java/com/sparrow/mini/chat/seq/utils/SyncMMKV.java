package com.sparrow.mini.chat.seq.utils;

import com.sparrow.mini.chat.seq.entity.Scene;
import com.tencent.mmkv.MMKV;

public class SyncMMKV {
    private static final SyncMMKV instance = new SyncMMKV();
    private static final MMKV mmkv = MMKV.mmkvWithID("sync");

    private SyncMMKV() {

    }

    public static SyncMMKV get() {
        return instance;
    }

    public boolean setSeqId(Scene scene, long seqId) {
        return mmkv.encode(scene.getId(), seqId);
    }

    public long getSeqId(Scene scene) {
        return mmkv.getLong(scene.getId(), 0);
    }
}
