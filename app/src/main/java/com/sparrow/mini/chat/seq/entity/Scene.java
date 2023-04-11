package com.sparrow.mini.chat.seq.entity;

public enum Scene {
    SCENE_EXAMPLE("example"),
    SCENE_CHAT("chat"),
    SCENE_GROUP("group"),
    SCENE_SUBSCRIPTION("subscription")
    ;

    String id;
    Scene(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
