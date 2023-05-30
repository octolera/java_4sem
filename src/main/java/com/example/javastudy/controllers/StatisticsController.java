package com.example.javastudy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javastudy.statistic.Counter;
import com.example.javastudy.statistic.Statistics;

@RestController
@RequestMapping("/stat")
public class StatisticsController {

    private final Statistics stat;

    public StatisticsController(Statistics stats) {
        this.stat = stats;
    }

    @GetMapping
    public Counter stats() {
        return new Counter(stat.getCounter());
    }
}
