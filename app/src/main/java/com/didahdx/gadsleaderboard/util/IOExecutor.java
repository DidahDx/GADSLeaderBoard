package com.didahdx.gadsleaderboard.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Used to execute background tasks in separate thread.
 */
public final class IOExecutor implements Executor {

    private static IOExecutor executor = null;
    private static final Object LOCK = new Object();

    @Override
    public void execute(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }

    public static IOExecutor getInstance() {
        if (executor == null) {
            synchronized (LOCK) {
                executor = new IOExecutor();
            }
        }
        return executor;
    }
}