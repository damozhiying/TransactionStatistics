package com.transaction.statistics.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.transaction.statistics.utils.json.JsonCustomSnakeCase;
import com.transaction.statistics.utils.validation.WithinLast;

import javax.validation.constraints.Min;

import static java.time.temporal.ChronoUnit.SECONDS;

@JsonCustomSnakeCase
@JsonSerialize(as = ImmutableTransaction.class)
@JsonDeserialize(as = ImmutableTransaction.class)
public interface Transaction {

    @Min(0)
    double getAmount();

    @WithinLast(duration = 60, unit = SECONDS)
    long getTimestamp();
}
