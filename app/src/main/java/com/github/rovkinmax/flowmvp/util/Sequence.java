package com.github.rovkinmax.flowmvp.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Rovkin Max
 */
public final class Sequence {

    private final AtomicLong mInitialValue;

    public Sequence(long initialValue) {
        mInitialValue = new AtomicLong(initialValue);
    }

    public static Sequence get() {
        return Holder.INSTANCE;
    }

    public long nextLong() {
        return mInitialValue.incrementAndGet();
    }

    public int nextInt() {
        return (int) nextLong();
    }

    @SuppressWarnings("squid:S1118")
    private static final class Holder {
        public static final Sequence INSTANCE = new Sequence(9000);
    }

}
