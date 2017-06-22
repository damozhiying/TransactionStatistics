package com.transaction.statistics.controllers;

import com.transaction.statistics.domains.Transaction;
import com.transaction.statistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/n26api/transactions")
public class TransactionsController {
    private final StatisticsService statisticsService;

    @Autowired
    public TransactionsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTransaction(@Valid @RequestBody Transaction transaction) {
        statisticsService.register(transaction);
    }

}
