package com.sparrow.mini.chat.seq.interfaces;

import com.sparrow.mini.chat.seq.entity.SyncSceneResult;
import com.sparrow.mini.chat.http.CommonHttpCallback;

/**
 * 处理成功队列Id才会递增
 * */
public interface ISyncResultHandler {
    void handleResult(SyncSceneResult result);
    void handleError(int errorCode, String errorMsg);
    void handleNetWorkError(CommonHttpCallback.Error error);
}
