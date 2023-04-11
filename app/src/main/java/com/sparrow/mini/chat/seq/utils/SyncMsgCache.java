package com.sparrow.mini.chat.seq.utils;

import androidx.annotation.Nullable;

import com.sparrow.mini.chat.seq.entity.Scene;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SyncMsgCache {
    private final MMKV mmkv;
    private SyncMsgCache(Scene scene) {
        mmkv = MMKV.mmkvWithID("sync_msg_cache_" + scene.getId());
    }

    public static SyncMsgCache get(Scene scene) {
        return new SyncMsgCache(scene);
    }

    public boolean containsMsg(long seqId) {
        return mmkv.contains(String.valueOf(seqId));
    }

    public boolean setMsg(long seqId, String msg) {
        return mmkv.encode(String.valueOf(seqId), msg);
    }

    @Nullable
    public String getMsg(long seqId) {
        return mmkv.getString(String.valueOf(seqId), null);
    }

    public void removeMsg(long seqId) {
        mmkv.removeValueForKey(String.valueOf(seqId));
    }

    public Map<Long, String> getAllMsg() {
        Map<Long, String> map = new HashMap<>();
        for (String key : mmkv.allKeys()) {
            map.put(Long.valueOf(key), mmkv.decodeString(key, null));
        }
        return map;
    }
}
