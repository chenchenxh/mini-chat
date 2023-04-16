package com.sparrow.mini.chat.seq.impl;

import com.google.gson.Gson;
import com.sparrow.mini.chat.seq.entity.PushSceneResult;
import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.interfaces.IPushResultHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushHandlerManager {
    private static final PushHandlerManager instance = new PushHandlerManager();
    private static final Map<Scene, List<IPushResultHandler>> handleMap = new HashMap<>();

    public static PushHandlerManager get() {
        return instance;
    }

    public void handlePushMsg(String data) {
        PushSceneResult result = (new Gson()).fromJson(data, PushSceneResult.class);
        if (result != null && result.getScene() != null && handleMap.containsKey(result.getScene())) {
            List<IPushResultHandler> handlers = handleMap.get(result.getScene());
            for (IPushResultHandler handler : handlers) {
                handler.handleResult(result);
            }
        }
    }
}
