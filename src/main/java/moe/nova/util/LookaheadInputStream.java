package moe.nova.util;

import org.jetbrains.annotations.NotNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A wrapper for an {@link InputStream} that allows {@link #peek()}ing one byte ahead in the stream.
 * This is useful for detecting the end of a stream without actually consuming any data in the
 * process (e.g. so the stream can be passed to another library that doesn't handle end-of-stream as
 * the first byte well).
 */
public class LookaheadInputStream extends FilterInputStream {
    private Integer next;
    private Integer nextAtMark;

    public LookaheadInputStream(InputStream in) {
        super(in);
    }

    public int peek() throws IOException {
        if (next == null) {
            next = read();
        }

        return next;
    }

    @Override
    public int read() throws IOException {
        if (next == null) {
            return super.read();
        }

        Integer next = this.next;
        this.next = null;
        return next;
    }

    @Override
    public int read(byte @NotNull [] b, int off, int len) throws IOException {
        if (next == null) {
            return super.read(b, off, len);
        }

        if (len == 0) {
            return 0;
        }

        if (next == -1) {
            return -1;
        }

        b[off] = (byte) (int) next;
        next = null;

        if (len == 1) {
            return 1;
        }

        return super.read(b, off + 1, b.length - 1) + 1;
    }

    @Override
    public long skip(long n) throws IOException {
        if (next == null) {
            return super.skip(n);
        }

        if (n == 0) {
            return 0;
        }

        if (next == -1) {
            return 0;
        }

        next = null;

        if (n == 1) {
            return 1;
        }

        return super.skip(n - 1);
    }

    @Override
    public int available() throws IOException {
        if (next == null) {
            return super.available();
        }

        return super.available() + 1;
    }

    @Override
    public synchronized void mark(int readLimit) {
        if (next == null) {
            super.mark(readLimit);
        } else {
            nextAtMark = next;
            super.mark(readLimit - 1);
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        next = nextAtMark;
        super.reset();
    }
}
