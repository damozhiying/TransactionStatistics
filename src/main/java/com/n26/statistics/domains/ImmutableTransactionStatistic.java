package com.n26.statistics.domains;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@Generated({"Immutables.generator", "TransactionStatistic"})
@Immutable
public class ImmutableTransactionStatistic implements TransactionStatistic {
    private final long count;
    private final double sum;
    private final double max;
    private final double min;
    private final double avg;

    private ImmutableTransactionStatistic(long count, double sum, double max, double min, double avg) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    @JsonProperty
    @Override
    public long getCount() {
        return count;
    }

    @JsonProperty
    @Override
    public double getSum() {
        return sum;
    }

    @JsonProperty
    @Override
    public double getMax() {
        return max;
    }

    @JsonProperty
    @Override
    public double getMin() {
        return min;
    }

    @JsonProperty
    @Override
    public double getAvg() {
        return avg;
    }

    public final ImmutableTransactionStatistic withCount(long value) {
        if (this.count == value) return this;
        return new ImmutableTransactionStatistic(value, this.sum, this.max, this.min, this.avg);
    }

    public final ImmutableTransactionStatistic withSum(double value) {
        if (Double.doubleToLongBits(this.sum) == Double.doubleToLongBits(value)) return this;
        return new ImmutableTransactionStatistic(this.count, value, this.max, this.min, this.avg);
    }

    public final ImmutableTransactionStatistic withMax(double value) {
        if (Double.doubleToLongBits(this.max) == Double.doubleToLongBits(value)) return this;
        return new ImmutableTransactionStatistic(this.count, this.sum, value, this.min, this.avg);
    }

    public final ImmutableTransactionStatistic withMin(double value) {
        if (Double.doubleToLongBits(this.min) == Double.doubleToLongBits(value)) return this;
        return new ImmutableTransactionStatistic(this.count, this.sum, this.max, value, this.avg);
    }

    public final ImmutableTransactionStatistic withAvg(double value) {
        if (Double.doubleToLongBits(this.min) == Double.doubleToLongBits(value)) return this;
        return new ImmutableTransactionStatistic(this.count, this.sum, this.max, this.min, value);
    }

    @Override
    public boolean equals(@Nullable Object another) {
        return this == another || another instanceof ImmutableTransactionStatistic && equalTo((ImmutableTransactionStatistic) another);
    }

    private boolean equalTo(ImmutableTransactionStatistic another) {
        return count == another.count
                && Double.doubleToLongBits(sum) == Double.doubleToLongBits(another.sum)
                && Double.doubleToLongBits(max) == Double.doubleToLongBits(another.max)
                && Double.doubleToLongBits(min) == Double.doubleToLongBits(another.min)
                && Double.doubleToLongBits(avg) == Double.doubleToLongBits(another.avg);
    }

    @Override
    public int hashCode() {
        int h = 31;
        h += (h << 5) + Long.hashCode(count);
        h += (h << 5) + Double.hashCode(sum);
        h += (h << 5) + Double.hashCode(max);
        h += (h << 5) + Double.hashCode(min);
        h += (h << 5) + Double.hashCode(avg);
        return h;
    }

    @Override
    public String toString() {
        return "TransactionStatistic{"
                + "count=" + count
                + ", sum=" + sum
                + ", max=" + max
                + ", min=" + min
                + ", avg=" + avg
                + "}";
    }

    @Deprecated
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    static final class Json implements TransactionStatistic {
        long count;
        boolean countIsSet;
        double sum;
        boolean sumIsSet;
        double max;
        boolean maxIsSet;
        double min;
        boolean minIsSet;
        double avg;
        boolean avgIsSet;
        @JsonProperty
        public void setCount(long count) {
            this.count = count;
            this.countIsSet = true;
        }
        @JsonProperty
        public void setSum(double sum) {
            this.sum = sum;
            this.sumIsSet = true;
        }
        @JsonProperty
        public void setMax(double max) {
            this.max = max;
            this.maxIsSet = true;
        }
        @JsonProperty
        public void setMin(double min) {
            this.min = min;
            this.minIsSet = true;
        }
        @JsonProperty
        public void setAvg(double avg) {
            this.avg = avg;
            this.avgIsSet = true;
        }
        @Override
        public long getCount() { throw new UnsupportedOperationException(); }
        @Override
        public double getSum() { throw new UnsupportedOperationException(); }
        @Override
        public double getMax() { throw new UnsupportedOperationException(); }
        @Override
        public double getMin() { throw new UnsupportedOperationException(); }
        @Override
        public double getAvg() { throw new UnsupportedOperationException(); }
    }

    @Deprecated
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    static ImmutableTransactionStatistic fromJson(Json json) {
        ImmutableTransactionStatistic.Builder builder = ImmutableTransactionStatistic.builder();
        if (json.countIsSet) {
            builder.count(json.count);
        }
        if (json.sumIsSet) {
            builder.sum(json.sum);
        }
        if (json.maxIsSet) {
            builder.max(json.max);
        }
        if (json.minIsSet) {
            builder.min(json.min);
        }
        if (json.avgIsSet) {
            builder.min(json.avg);
        }
        return builder.build();
    }

    public static ImmutableTransactionStatistic copyOf(TransactionStatistic instance) {
        if (instance instanceof ImmutableTransactionStatistic) {
            return (ImmutableTransactionStatistic) instance;
        }
        return ImmutableTransactionStatistic.builder()
                .from(instance)
                .build();
    }

    public static ImmutableTransactionStatistic.Builder builder() {
        return new ImmutableTransactionStatistic.Builder();
    }

    @NotThreadSafe
    public static final class Builder {
        private long count;
        private double sum;
        private double max;
        private double min;
        private double avg;

        private Builder() {
        }

        public final Builder from(TransactionStatistic instance) {
            Objects.requireNonNull(instance, "instance");
            count(instance.getCount());
            sum(instance.getSum());
            max(instance.getMax());
            min(instance.getMin());
            avg(instance.getAvg());
            return this;
        }

        @JsonProperty
        public final Builder count(long count) {
            this.count = count;
            return this;
        }

        @JsonProperty
        public final Builder sum(double sum) {
            this.sum = sum;
            return this;
        }

        @JsonProperty
        public final Builder max(double max) {
            this.max = max;
            return this;
        }

        @JsonProperty
        public final Builder min(double min) {
            this.min = min;
            return this;
        }

        @JsonProperty
        public final Builder avg(double avg) {
            this.avg = avg;
            return this;
        }

        public ImmutableTransactionStatistic build() {
            return new ImmutableTransactionStatistic(count, sum, max, min, avg);
        }
    }
}
