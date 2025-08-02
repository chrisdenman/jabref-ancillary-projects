package org.jabref.logic.util;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeadlessExecutorService {
    public static final HeadlessExecutorService INSTANCE = new HeadlessExecutorService();

    private static final String EXECUTOR_NAME = "JabRef CachedThreadPool";

    private final ExecutorService executorService = Executors.newCachedThreadPool(r -> {
        Thread thread = new Thread(r);
        thread.setName(EXECUTOR_NAME);
        thread.setUncaughtExceptionHandler(new FallbackExceptionHandler());
        return thread;
    });

    private HeadlessExecutorService() {
    }

    public void execute(Runnable command) {
        Objects.requireNonNull(command);
        executorService.execute(command);
    }
}
