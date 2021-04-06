package com.capstone.apm.commons.event;

public abstract class Event<T>{
    private T content;

    public Event(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
