package moe.nova.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Function;

/**
 * Utility class for working with {@link CompletableFuture}.
 */
public final class CompletableFutureUtil {

    private CompletableFutureUtil() {
    }

    /**
     * Convenience method for creating a future that is immediately completed
     * exceptionally with the given {@code Throwable}.
     * <p>
     * Similar to {@code CompletableFuture#failedFuture} which was added in Java 9.
     *
     * @param t   The failure.
     * @param <U> The type of the element.
     * @return The failed future.
     */
    public static <U> CompletableFuture<U> failedFuture(Throwable t) {
        CompletableFuture<U> cf = new CompletableFuture<>();
        cf.completeExceptionally(t);
        return cf;
    }

    /**
     * Wraps the given error in a {@link CompletionException} if necessary.
     * Useful if an exception needs to be rethrown from within {@link
     * CompletableFuture#handle(java.util.function.BiFunction)} or similar
     * methods.
     *
     * @param t The error.
     * @return The error as a CompletionException.
     */
    public static CompletionException errorAsCompletionException(Throwable t) {
        if (t instanceof CompletionException) {
            return (CompletionException) t;
        }
        return new CompletionException(t);
    }

    /**
     * Forward the {@code Throwable} from {@code src} to {@code dst}.
     *
     * @param src The source of the {@code Throwable}.
     * @param dst The destination where the {@code Throwable} will be forwarded to.
     * @return {@code src}.
     */
    public static <T> CompletableFuture<T> forwardExceptionTo(CompletableFuture<T> src, CompletableFuture<?> dst) {
        src.whenComplete((_, e) -> {
            if (e != null) {
                dst.completeExceptionally(e);
            }
        });
        return src;
    }


    /**
     * Forward the {@code Throwable} that can be transformed as per the transformationFunction
     * from {@code src} to {@code dst}.
     *
     * @param src                    The source of the {@code Throwable}.
     * @param dst                    The destination where the {@code Throwable} will be forwarded to
     * @param transformationFunction Transformation function that will be applied on to the forwarded exception.
     * @return
     */
    public static <T> CompletableFuture<T> forwardTransformedExceptionTo(CompletableFuture<T> src,
                                                                         CompletableFuture<?> dst,
                                                                         Function<Throwable, Throwable> transformationFunction) {
        src.whenComplete((_, e) -> {
            if (e != null) {
                dst.completeExceptionally(transformationFunction.apply(e));
            }
        });
        return src;
    }

    /**
     * Completes the {@code dst} future based on the result of the {@code src} future asynchronously on
     * the provided {@link Executor} and return the {@code src} future.
     *
     * @param src The source {@link CompletableFuture}
     * @param dst The destination where the {@code Throwable} or response will be forwarded to.
     * @return the {@code src} future.
     */
    public static <T> CompletableFuture<T> forwardResultTo(CompletableFuture<T> src,
                                                           CompletableFuture<T> dst) {
        src.whenComplete((r, e) -> {
            if (e != null) {
                dst.completeExceptionally(e);
            } else {
                dst.complete(r);
            }
        });

        return src;
    }

    /**
     * Completes the {@code dst} future based on the result of the {@code src} future asynchronously on
     * the provided {@link Executor} and return the {@code src} future.
     *
     * @param src      The source {@link CompletableFuture}
     * @param dst      The destination where the {@code Throwable} or response will be forwarded to.
     * @param executor the executor to complete the des future
     * @return the {@code src} future.
     */
    public static <T> CompletableFuture<T> forwardResultTo(CompletableFuture<T> src,
                                                           CompletableFuture<T> dst,
                                                           Executor executor) {
        src.whenCompleteAsync((r, e) -> {
            if (e != null) {
                dst.completeExceptionally(e);
            } else {
                dst.complete(r);
            }
        }, executor);

        return src;
    }

    /**
     * Completes the {@code dst} future based on the result of the {@code src} future, synchronously,
     * after applying the provided transformation {@link Function} if successful.
     *
     * @param src The source {@link CompletableFuture}
     * @param dst The destination where the {@code Throwable} or transformed result will be forwarded to.
     * @return the {@code src} future.
     */
    public static <SourceT, DestT> CompletableFuture<SourceT> forwardTransformedResultTo(CompletableFuture<SourceT> src,
                                                                                         CompletableFuture<DestT> dst,
                                                                                         Function<SourceT, DestT> function) {
        src.whenComplete((r, e) -> {
            if (e != null) {
                dst.completeExceptionally(e);
            } else {
                dst.complete(function.apply(r));
            }
        });

        return src;
    }

    /**
     * Similar to {@link CompletableFuture#allOf(CompletableFuture[])}, but
     * when any future is completed exceptionally, forwards the
     * exception to other futures.
     *
     * @param futures The futures.
     * @return The new future that is completed when all the futures in {@code
     * futures} are.
     */
    public static CompletableFuture<Void> allOfExceptionForwarded(CompletableFuture<?>[] futures) {

        CompletableFuture<Void> anyFail = anyFail(futures);

        anyFail.whenComplete((_, t) -> {
            if (t != null) {
                for (CompletableFuture<?> cf : futures) {
                    cf.completeExceptionally(t);
                }
            }
        });

        return CompletableFuture.allOf(futures);
    }

    /**
     * Returns a new CompletableFuture that is completed when any of
     * the given CompletableFutures completes exceptionally.
     *
     * @param futures the CompletableFutures
     * @return a new CompletableFuture that is completed if any provided
     * future completed exceptionally.
     */
    static CompletableFuture<Void> anyFail(CompletableFuture<?>[] futures) {
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        for (CompletableFuture<?> future : futures) {
            future.whenComplete((_, t) -> {
                if (t != null) {
                    completableFuture.completeExceptionally(t);
                }
            });
        }

        return completableFuture;
    }

    public static <T> T joinInterruptibly(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CompletionException("Interrupted while waiting on a future.", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new CompletionException(cause);
        }
    }

    public static void joinInterruptiblyIgnoringFailures(CompletableFuture<?> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            // Ignore
        }
    }

    /**
     * Joins (interruptibly) on the future, and re-throws any RuntimeExceptions or Errors just like the async task would have
     * thrown if it was executed synchronously.
     */
    public static <T> T joinLikeSync(CompletableFuture<T> future) {
        try {
            return joinInterruptibly(future);
        } catch (CompletionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                // Make sure we don't lose the context of where the join is in the stack...
                cause.addSuppressed(new RuntimeException("Task failed."));
                throw (RuntimeException) cause;
            }
            throw e;
        }
    }
}
