package com.sparrow.mini.chat.seq.interfaces;

import com.sparrow.mini.chat.seq.entity.Scene;

public interface ISceneSDK {
    Scene getScene();
    void onSeqMsgArrived();
}
