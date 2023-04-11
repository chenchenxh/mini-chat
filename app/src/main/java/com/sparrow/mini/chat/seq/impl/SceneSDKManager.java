package com.sparrow.mini.chat.seq.impl;

import androidx.annotation.Nullable;

import com.sparrow.mini.chat.seq.interfaces.ISceneSDK;
import com.sparrow.mini.chat.seq.entity.Scene;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SceneSDKManager {
    private static final SceneSDKManager instance = new SceneSDKManager();
    private final Map<String, ISceneSDK> sdkMap = new ConcurrentHashMap<>();

    public static SceneSDKManager getInstance() {
        return instance;
    }

    public void setSceneSDK(Scene scene, ISceneSDK msgSDK) {
        sdkMap.put(scene.getId(), msgSDK);
    }

    @Nullable
    public ISceneSDK getMsgSDK(Scene scene) {
        return sdkMap.get(scene.getId());
    }
}
