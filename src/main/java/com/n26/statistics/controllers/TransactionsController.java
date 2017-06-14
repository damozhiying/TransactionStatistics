package com.n26.statistics.controllers;

import com.n26.statistics.domains.Transaction;
import com.n26.statistics.services.StatisticsService;
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
