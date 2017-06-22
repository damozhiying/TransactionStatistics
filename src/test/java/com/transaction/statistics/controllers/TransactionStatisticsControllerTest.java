package com.transaction.statistics.controllers;

import com.transaction.statistics.domains.ImmutableTransactionStatistic;
import com.transaction.statistics.domains.TransactionStatistic;
import com.transaction.statistics.services.StatisticsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionStatisticsController.class)
public class TransactionStatisticsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void shouldReturnSampleStatistics() throws Exception {
        when(statisticsService.getTransactionStatistics()).thenReturn(ImmutableTransactionStatistic.builder()
                .count(3)
                .sum(43.8)
                .max(42.5)
                .min(0.3)
                .avg(14.6)
                .build());

        mvc.perform(get("/n26api/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(3)))
                .andExpect(jsonPath("sum", is(43.8)))
                .andExpect(jsonPath("avg", is(14.6)))
                .andExpect(jsonPath("max", is(42.5)))
                .andExpect(jsonPath("min", is(0.3)));
    }

    @Test
    public void shouldReturnZeroStatistics() throws Exception {
        when(statisticsService.getTransactionStatistics()).thenReturn(TransactionStatistic.ZERO);

        mvc.perform(get("/n26api/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(0)))
                .andExpect(jsonPath("sum", is(0.0)))
                .andExpect(jsonPath("avg", is("NaN")))
                .andExpect(jsonPath("max", is("NaN")))
                .andExpect(jsonPath("min", is("NaN")));
    }
}
