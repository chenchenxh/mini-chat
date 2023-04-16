package com.sparrow.mini.chat.sdk.impl;

import com.sparrow.mini.chat.seq.annotations.Push;
import com.sparrow.mini.chat.seq.entity.Scene;
import com.sparrow.mini.chat.seq.impl.PushResultHandler;

@Push(scenes = {Scene.SCENE_EXAMPLE})
public class ExamplePushHandler extends PushResultHandler {

    @Override
    public Scene getScene() {
        return Scene.SCENE_EXAMPLE;
    }
}
