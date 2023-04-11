package com.sparrow.mini.chat.seq.entity;

public enum SyncState {
    SYNC_STATE_CREATE(0),
    SYNC_STATE_READY(1),
    SYNC_STATE_RUNNING(2),
    SYNC_STATE_SUSPEND(3),
    SYNC_STATE_BLOCK(4),
    SYNC_STATE_STOP(5);

    int state;

    SyncState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
