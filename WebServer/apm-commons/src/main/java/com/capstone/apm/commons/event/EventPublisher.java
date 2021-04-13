package com.capstone.apm.commons.event;


import java.util.concurrent.*;
import java.util.function.BiConsumer;

import static java.util.concurrent.Flow.defaultBufferSize;

public class EventPublisher<T extends Event<?>> extends SubmissionPublisher<T> {

}
