package com.sparrow.mini.chat.seq.entity;

public enum SyncSource {
    SYNC_SOURCE_EXAMPLE("example"),
    SYNC_SOURCE_COLD_START("cold_start"),
    SYNC_SOURCE_AGGREGATE("aggregate");

    String source;
    SyncSource(String source) {

    }

    public String getSource() {
        return source;
    }
}
