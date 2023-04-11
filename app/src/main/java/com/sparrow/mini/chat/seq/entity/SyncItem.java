package com.sparrow.mini.chat.seq.entity;

/**
 * Sync项，请求参数
 * */
public class SyncItem {
    long seqId;
    Scene scene;

    public SyncItem() {

    }

    public SyncItem(long seqId, Scene scene) {
        this.seqId = seqId;
        this.scene = scene;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
