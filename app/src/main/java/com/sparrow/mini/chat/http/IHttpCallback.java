package com.sparrow.mini.chat.http;

public interface IHttpCallback {
    void onSuccess(Object response);
    void onError(Object error);
}
