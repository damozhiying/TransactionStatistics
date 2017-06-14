package com.n26.statistics.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.n26.statistics.utils.json.JsonCustomSnakeCase;
import com.n26.statistics.utils.validation.WithinLast;

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
