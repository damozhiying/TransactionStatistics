package com.transaction.statistics.services;

import com.transaction.statistics.domains.Transaction;
import com.transaction.statistics.domains.TransactionStatistic;

public interface StatisticsService {
    void register(Transaction transaction);

    TransactionStatistic getTransactionStatistics();
}
