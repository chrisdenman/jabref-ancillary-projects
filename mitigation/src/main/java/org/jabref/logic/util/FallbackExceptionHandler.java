package org.jabref.logic.util;

import java.util.function.BiConsumer;

public class FallbackExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        System.out.println("thread: " + thread);
        System.out.println("throwable: " + exception);
    }
}
