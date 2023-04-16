package com.sparrow.mini.chat.seq.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

// todo 完善优化Socket逻辑
public class PushSocket {
    private OkHttpClient client;

    public PushSocket() {
        client = new OkHttpClient();
    }

    public void connect() {
        String url = "api/sparrow/mini/chat/push";
        Request request = new Request.Builder().get().url(url).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                super.onMessage(webSocket, text);
                JsonObject msg = (new Gson()).fromJson(text, JsonObject.class);
                String type = msg.has("type") ? msg.get("type").getAsString() : "";
                String data = msg.has("data") ? msg.get("data").getAsString() : null;
                switch (type) {
                    case "push":
                        PushHandlerManager.get().handlePushMsg(data);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                super.onOpen(webSocket, response);
            }
        });
    }
}
