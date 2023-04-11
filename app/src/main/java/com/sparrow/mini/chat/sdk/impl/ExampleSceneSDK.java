package com.sparrow.mini.chat.sdk.impl;

import com.google.gson.Gson;
import com.sparrow.mini.chat.sdk.entity.ExampleBizMsg;
import com.sparrow.mini.chat.sdk.interfaces.IExampleSceneSDK;
import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.entity.SeqMsg;
import com.sparrow.mini.chat.seq.impl.SceneSDKManager;
import com.sparrow.mini.chat.seq.utils.SyncMsgCache;

import java.util.Map;

public class ExampleSceneSDK implements IExampleSceneSDK {

    public ExampleSceneSDK() {
        SceneSDKManager.getInstance().setSceneSDK(Scene.SCENE_EXAMPLE, this);
    }

    @Override
    public Scene getScene() {
        return Scene.SCENE_EXAMPLE;
    }

    @Override
    public void onSeqMsgArrived() {
        Map<Long, String> seqMsgMap = SyncMsgCache.get(getScene()).getAllMsg();
        for (String seqMsgBody : seqMsgMap.values()) {
            SeqMsg seqMsg = (new Gson()).fromJson(seqMsgBody, SeqMsg.class);
            ExampleBizMsg exampleBizMsg = (new Gson()).fromJson(seqMsg.getMsgBody(), ExampleBizMsg.class);
            saveMsg(exampleBizMsg);
        }
    }

    @Override
    public void saveMsg(ExampleBizMsg exampleBizMsg) {

    }

    @Override
    public void deleteMsg(ExampleBizMsg exampleBizMsg) {

    }
}
