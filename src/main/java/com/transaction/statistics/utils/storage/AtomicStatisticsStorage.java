package com.transaction.statistics.utils.storage;

import com.google.common.annotations.VisibleForTesting;
import com.transaction.statistics.exceptions.InvalidTimestampException;
import com.sun.istack.internal.Nullable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.MILLIS;

public class AtomicStatisticsStorage<T> implements StatisticsStorage<T> {
    private final Supplier<Long> now;
    private final Supplier<T> factory;

    private final TemporalUnit targetUnit;
    private final TemporalUnit groupingUnit;

    private final AtomicReferenceArray<Reference<T>> store;

    public static <T> StatisticsStorage<T> lastMinute(Supplier<T> factory) {
        return new AtomicStatisticsStorage<>(ChronoUnit.MINUTES, ChronoUnit.SECONDS, 64, factory);
    }

    private AtomicStatisticsStorage(TemporalUnit targetUnit, TemporalUnit groupingUnit, int bufferSize, Supplier<T> factory) {
        this(targetUnit, groupingUnit, bufferSize, factory, System::currentTimeMillis);
    }

    @VisibleForTesting
    AtomicStatisticsStorage(TemporalUnit targetUnit, TemporalUnit groupingUnit, int bufferSize, Supplier<T> factory, Supplier<Long> now) {
        this.now = now;
        this.factory = factory;
        this.targetUnit = targetUnit;
        this.groupingUnit = groupingUnit;
        this.store = new AtomicReferenceArray<>(bufferSize);
    }

    @Override
    public void update(long timestamp, UnaryOperator<T> updater) {
        getReference(timestamp).update(updater);
    }

    @Override
    public T reduce(BinaryOperator<T> reducer) {
        return getReferenceStream().reduce(factory.get(), reducer);
    }

    @VisibleForTesting
    Reference<T> getReference(long timestamp) {
        int index = checkedIndexFor(timestamp);
        int offset = offset(index);
        return store.updateAndGet(offset, value -> actual(index, value));
    }

    private Stream<T> getReferenceStream() {
        long now = this.now.get();

        int firstIndex = minimalIndexFor(now);
        int lastIndex = currentIndexFor(now);

        return IntStream.rangeClosed(firstIndex, lastIndex)
                .mapToObj(index -> historical(index, store.get(offset(index))))
                .filter(Objects::nonNull)
                .map(Reference::getValue);
    }

    private Reference<T> historical(int index, @Nullable Reference<T> reference) {
        return reference != null && reference.getIndex() == index ? reference : null;
    }

    private Reference<T> actual(int index, @Nullable Reference<T> value) {
        return value == null || value.getIndex() < index ? new Reference<>(index, factory.get()) : value;
    }

    private int offset(int index) {
        return index % store.length();
    }

    private int currentIndexFor(long timestamp) {
        return (int) Duration.of(timestamp, MILLIS).get(groupingUnit);
    }

    private int minimalIndexFor(long timestamp) {
        return (int) Duration.of(timestamp, MILLIS).minus(1, targetUnit).get(groupingUnit);
    }

    private int checkedIndexFor(long timestamp) {
        long now = this.now.get();

        int minimalIndex = minimalIndexFor(now);
        int maximalIndex = currentIndexFor(now);

        int index = currentIndexFor(timestamp);

        InvalidTimestampException.check(index >= minimalIndex, "Timestamp is to old");
        InvalidTimestampException.check(index <= maximalIndex, "Timestamp is in young");

        return index;
    }

    @VisibleForTesting
    protected static class Reference<E> {
        private final long index;
        private final AtomicReference<E> value;

        Reference(long index, E value) {
            this.index = index;
            this.value = new AtomicReference<>(value);
        }

        void update(UnaryOperator<E> updater) {
            value.updateAndGet(updater);
        }

        E getValue() {
            return value.get();
        }

        long getIndex() {
            return index;
        }
    }

}
