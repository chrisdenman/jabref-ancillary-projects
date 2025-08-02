package org.jabref.logic.util;

import java.util.function.BiConsumer;

public class FallbackExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        System.out.printf("Uncaught exception %s occurred in %s", thread.toString(), exception.toString());
    }
}
