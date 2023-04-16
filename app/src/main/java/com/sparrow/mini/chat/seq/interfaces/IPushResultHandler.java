package com.sparrow.mini.chat.seq.interfaces;

import com.sparrow.mini.chat.seq.entity.PushSceneResult;
import com.sparrow.mini.chat.seq.entity.Scene;

public interface IPushResultHandler {
    void handleResult(PushSceneResult result);
    Scene getScene();
}
