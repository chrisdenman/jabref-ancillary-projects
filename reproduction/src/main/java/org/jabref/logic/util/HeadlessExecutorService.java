package org.jabref.logic.util;

import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HeadlessExecutorService {
    public static final HeadlessExecutorService INSTANCE = new HeadlessExecutorService();

    private static final String EXECUTOR_NAME = "JabRef CachedThreadPool";

    private final ExecutorService executorService = Executors.newCachedThreadPool(r -> {
        Thread thread = new Thread(r);
        thread.setName(EXECUTOR_NAME);
        thread.setUncaughtExceptionHandler(new FallbackExceptionHandler());
        return thread;
    });

    private final Timer timer = new Timer("timer", true);

    private HeadlessExecutorService() {
    }

    public void execute(Runnable command) {
        Objects.requireNonNull(command);
        executorService.execute(command);
    }

    public void shutdownEverything() {
        System.out.println("Gracefully shut down executor service");
        gracefullyShutdown(EXECUTOR_NAME, this.executorService, 15);

        System.out.println("Canceling timer");
        timer.cancel();

        System.out.println("Finished shutdownEverything");
    }

    public static void gracefullyShutdown(String name, ExecutorService executorService, int timeoutInSeconds) {
        try {
            // This is non-blocking. See https://stackoverflow.com/a/57383461/873282.
            executorService.shutdown();
            if (!executorService.awaitTermination(timeoutInSeconds, TimeUnit.SECONDS)) {
                System.out.printf("%d seconds passed, %s still not completed. Trying forced shutdown.%n", timeoutInSeconds, name);
                // those threads will be interrupted in their current task
                executorService.shutdownNow();
                if (executorService.awaitTermination(timeoutInSeconds, TimeUnit.SECONDS)) {
                    System.out.printf("%d seconds passed again - forced shutdown of %s worked.", timeoutInSeconds, name);
                } else {
                    System.out.printf("%s did not terminate", name);
                }
            }
        } catch (
                InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
