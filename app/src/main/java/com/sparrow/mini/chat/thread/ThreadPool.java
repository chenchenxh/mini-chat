package com.sparrow.mini.chat.thread;

public class ThreadPool {
    private static final ThreadPool instance = new ThreadPool();
    private final IThread threadImpl;

    private ThreadPool() {
        threadImpl = new ThreadImpl();
    }

    public static IThread get() {
        return instance.threadImpl;
    }
}
