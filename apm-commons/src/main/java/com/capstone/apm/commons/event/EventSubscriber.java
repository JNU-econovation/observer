package com.capstone.apm.commons.event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;

public abstract class EventSubscriber<T extends Event<?>> implements Subscriber<T> {
    protected Subscription subscription;
}
