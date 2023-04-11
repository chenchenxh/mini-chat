package com.sparrow.mini.chat.seq.interfaces;

import com.sparrow.mini.chat.seq.entity.Scene;

public interface ISyncServiceManager {
    void register(ISyncService service);
    void unregister(ISyncService service);
    ISyncService getService(Scene scene);
    ISyncService getService(String sceneId);
}
