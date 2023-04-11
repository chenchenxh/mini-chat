package com.sparrow.mini.chat.sdk.interfaces;

import com.sparrow.mini.chat.sdk.entity.ExampleBizMsg;
import com.sparrow.mini.chat.seq.interfaces.ISceneSDK;

public interface IExampleSceneSDK extends ISceneSDK {
    void saveMsg(ExampleBizMsg exampleBizMsg);
    void deleteMsg(ExampleBizMsg exampleBizMsg);
}
