package moe.nova.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {

    private final ThreadFactory delegate;

    public DaemonThreadFactory(ThreadFactory delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
    }

    @Override
    public Thread newThread(@NotNull Runnable runnable) {
        Thread thread = delegate.newThread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
