package com.n26.statistics.services;

import com.google.common.annotations.VisibleForTesting;
import com.n26.statistics.domains.Transaction;
import com.n26.statistics.domains.TransactionStatistic;
import com.n26.statistics.utils.storage.AtomicStatisticsStorage;
import com.n26.statistics.utils.storage.StatisticsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.n26.statistics.domains.TransactionStatistic.ZERO;

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
