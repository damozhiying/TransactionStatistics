package com.n26.statistics.utils.storage;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public interface StatisticsStorage<T> {
    void update(long timestamp, UnaryOperator<T> updater);

    T reduce(BinaryOperator<T> reducer);
}
