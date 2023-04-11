package com.sparrow.mini.chat.seq.interfaces;

public interface ISyncQueue {
    void put(ISyncService syncService);
    void remove(ISyncService syncService);
}
