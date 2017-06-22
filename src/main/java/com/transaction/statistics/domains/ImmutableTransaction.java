package com.transaction.statistics.domains;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@Generated({"Immutables.generator", "Transaction"})
@Immutable
public class ImmutableTransaction implements Transaction {
    private final double amount;
    private final long timestamp;

    private ImmutableTransaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @JsonProperty
    @Override
    public double getAmount() {
        return amount;
    }

    @JsonProperty
    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public final ImmutableTransaction withAmount(double value) {
        if (Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(value)) return this;
        return new ImmutableTransaction(value, this.timestamp);
    }

    public final ImmutableTransaction withTimestamp(long value) {
        if (this.timestamp == value) return this;
        return new ImmutableTransaction(this.amount, value);
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another) return true;
        return another instanceof ImmutableTransaction
                && equalTo((ImmutableTransaction) another);
    }

    private boolean equalTo(ImmutableTransaction another) {
        return Double.doubleToLongBits(amount) == Double.doubleToLongBits(another.amount)
                && timestamp == another.timestamp;
    }

    @Override
    public int hashCode() {
        int h = 31;
        h += (h << 5) + Double.hashCode(amount);
        h += (h << 5) + Long.hashCode(timestamp);
        return h;
    }

    @Override
    public String toString() {
        return "Transaction{"
                + "amount=" + amount
                + ", timestamp=" + timestamp
                + "}";
    }

    @Deprecated
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    static final class Json implements Transaction {
        double amount;
        boolean amountIsSet;
        long timestamp;
        boolean timestampIsSet;
        @JsonProperty
        public void setAmount(double amount) {
            this.amount = amount;
            this.amountIsSet = true;
        }
        @JsonProperty
        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            this.timestampIsSet = true;
        }
        @Override
        public double getAmount() { throw new UnsupportedOperationException(); }
        @Override
        public long getTimestamp() { throw new UnsupportedOperationException(); }
    }

    @Deprecated
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    static ImmutableTransaction fromJson(Json json) {
        ImmutableTransaction.Builder builder = ImmutableTransaction.builder();
        if (json.amountIsSet) {
            builder.amount(json.amount);
        }
        if (json.timestampIsSet) {
            builder.timestamp(json.timestamp);
        }
        return builder.build();
    }

    public static ImmutableTransaction of(double amount, long timestamp) {
        return new ImmutableTransaction(amount, timestamp);
    }

    public static ImmutableTransaction copyOf(Transaction instance) {
        if (instance instanceof ImmutableTransaction) {
            return (ImmutableTransaction) instance;
        }
        return ImmutableTransaction.builder()
                .from(instance)
                .build();
    }

    public static ImmutableTransaction.Builder builder() {
        return new ImmutableTransaction.Builder();
    }

    @NotThreadSafe
    public static final class Builder {
        private double amount;
        private long timestamp;

        private Builder() {
        }

        public final Builder from(Transaction instance) {
            Objects.requireNonNull(instance, "instance");
            amount(instance.getAmount());
            timestamp(instance.getTimestamp());
            return this;
        }

        @JsonProperty
        public final Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        @JsonProperty
        public final Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ImmutableTransaction build() {
            return new ImmutableTransaction(amount, timestamp);
        }
    }
}
