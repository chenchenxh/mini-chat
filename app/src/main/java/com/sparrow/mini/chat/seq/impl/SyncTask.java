package com.sparrow.mini.chat.seq.impl;

import com.sparrow.mini.chat.seq.entity.SyncItem;
import com.sparrow.mini.chat.seq.entity.SyncResponse;
import com.sparrow.mini.chat.seq.entity.SyncSource;
import com.sparrow.mini.chat.http.CommonHttpCallback;
import com.sparrow.mini.chat.http.HttpCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SyncTask implements Runnable {
    private final List<SyncItem> syncItems;
    private final SyncSource source;

    public SyncTask(List<SyncItem> items, SyncSource source) {
        this.syncItems = items;
        this.source = source;
    }

    public List<SyncItem> getSyncItems() {
        return syncItems;
    }

    // todo 网络层数据转化
    @Override
    public void run() {
        HttpCall.get()
                .url("api/sparrow/mini/chat/sync")
                .params(createParams())
                .callback(new CommonHttpCallback<SyncResponse>() {
                    @Override
                    public void onCommonSuccess(SyncResponse response) {
                        if (response.isSuccess()) {
                            // 各场景分别处理消息
                            response.getResult().getSceneResult().forEach((result -> {
                                SyncServiceManager.get().getService(result.getScene())
                                        .handleResult(result);
                            }));
                        } else {
                            syncItems.forEach((item) -> {
                                SyncServiceManager.get().getService(item.getScene())
                                        .handleError(response.getErrorCode(), response.getErrorMsg());
                            });
                        }
                    }

                    @Override
                    public void onCommonError(Error error) {
                        syncItems.forEach((item) -> {
                            SyncServiceManager.get().getService(item.getScene())
                                    .handleNetWorkError(error);
                        });
                    }
                }).build().execute();
    }

    private String createParams() {
        JSONObject request = new JSONObject();
        try {
            request.put("sync_items", syncItems.toString());
            request.put("source", source);
            request.put("uid", "xxx");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request.toString();
    }
}
