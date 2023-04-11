package com.sparrow.mini.chat.seq.impl;

import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.interfaces.ISyncService;
import com.sparrow.mini.chat.seq.interfaces.ISyncServiceManager;

import java.util.HashMap;
import java.util.Map;

public class SyncServiceManager implements ISyncServiceManager {
    private static final SyncServiceManager instance = new SyncServiceManager();
    private Map<String, ISyncService> map = new HashMap<>();

    public static SyncServiceManager get() {
        return instance;
    }

    @Override
    public void register(ISyncService service) {
        map.put(service.getScene().getId(), service);
    }

    @Override
    public void unregister(ISyncService service) {
        map.remove(service.getScene().getId());
    }

    @Override
    public ISyncService getService(Scene scene) {
        return map.get(scene.getId());
    }

    @Override
    public ISyncService getService(String sceneId) {
        return map.get(sceneId);
    }
}
