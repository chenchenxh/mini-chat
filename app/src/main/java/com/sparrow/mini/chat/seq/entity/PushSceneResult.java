package com.sparrow.mini.chat.seq.entity;

import java.util.List;

public class PushSceneResult {
    private Scene scene;

    // 数据字段
    private long baseSeqId;
    private List<SeqMsg> data;

    // 功能字段
    private boolean triggerSync;
    private boolean reset;
    private String reason;


    public Scene getScene() {
        return scene;
    }

    public long getBaseSeqId() {
        return baseSeqId;
    }

    public List<SeqMsg> getData() {
        return data;
    }

    public boolean isTriggerSync() {
        return triggerSync;
    }

    public boolean isReset() {
        return reset;
    }

    public String getReason() {
        return reason;
    }
}
