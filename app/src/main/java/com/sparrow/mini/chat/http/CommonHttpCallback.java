package com.sparrow.mini.chat.http;


// todo Callback通用判断与转换
public abstract class CommonHttpCallback<T> implements IHttpCallback {
    @Override
    public void onSuccess(Object response) {

    }

    @Override
    public void onError(Object error) {

    }

    public abstract void onCommonSuccess(T object);
    public abstract void onCommonError(Error error);

    public static class Error {
        int errorCode;
        Object errorMsg;
    }
}
