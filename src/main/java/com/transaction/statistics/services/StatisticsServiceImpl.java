package com.transaction.statistics.services;

import com.google.common.annotations.VisibleForTesting;
import com.transaction.statistics.domains.Transaction;
import com.transaction.statistics.domains.TransactionStatistic;
import com.transaction.statistics.utils.storage.AtomicStatisticsStorage;
import com.transaction.statistics.utils.storage.StatisticsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.transaction.statistics.domains.TransactionStatistic.ZERO;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsStorage<TransactionStatistic> transactions;

    @Autowired
    public StatisticsServiceImpl() {
        this(AtomicStatisticsStorage.lastMinute(() -> ZERO));
    }

    @VisibleForTesting
    StatisticsServiceImpl(StatisticsStorage<TransactionStatistic> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void register(Transaction transaction) {
        transactions.update(transaction.getTimestamp(), statistic -> statistic.register(transaction.getAmount()));
    }

    @Override
    public TransactionStatistic getTransactionStatistics() {
        return transactions.reduce(TransactionStatistic::merge);
    }
}
