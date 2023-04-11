package com.sparrow.mini.chat.seq.entity;

/**
 * Sync网络层entity
 * */
public class SyncResponse {
    int errorCode;
    String errorMsg;
    boolean success;
    SyncResult result;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public SyncResult getResult() {
        return result;
    }

}
