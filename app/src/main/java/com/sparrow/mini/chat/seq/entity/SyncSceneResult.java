package com.sparrow.mini.chat.seq.entity;

import java.util.List;

/**
 * Sync entity
 * 功能操作与消息操作不冲突
 * */
public class SyncSceneResult {
    private Scene scene;

    // 数据字段
    private long baseSeqId;
    private List<SeqMsg> data;

    // 功能字段
    private boolean hasMore;
    private int sleepTs;
    private boolean waitSync;
    private String reason;
    private boolean reset;

    public Scene getScene() {
        return scene;
    }

    public long getBaseSeqId() {
        return baseSeqId;
    }

    public List<SeqMsg> getData() {
        return data;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public int getSleepTs() {
        return sleepTs;
    }

    public boolean isWaitSync() {
        return waitSync;
    }

    public String getReason() {
        return reason;
    }

    public boolean isReset() {
        return reset;
    }
}
