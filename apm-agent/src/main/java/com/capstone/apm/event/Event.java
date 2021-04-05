package com.capstone.apm.event;

public abstract class Event<T> implements Runnable {
    protected T content;
    protected Runnable runnable;

    public Event(T content) {
        this.content = content;
        this.runnable = null;
    }

    public Event(T content, Runnable runnable) {
        this.content = content;
        this.runnable = runnable;
    }

    public abstract void run();
}
