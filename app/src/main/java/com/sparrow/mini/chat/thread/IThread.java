package com.sparrow.mini.chat.thread;

public interface IThread {
    void addTask(Runnable runnable);
    void addDelayTask(Runnable runnable, int delay);
    void addIoTask(Runnable runnable);
    void addDelayIoTask(Runnable runnable, int delay);
}
