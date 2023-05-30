package com.example.javastudy.decimal;

import java.util.DoubleSummaryStatistics;

public record DecimalStatistics(Double average, Double minimal, Double maximal) {
    public DecimalStatistics(DoubleSummaryStatistics stats) {
        this(stats.getAverage(), stats.getMin(), stats.getMax());
    }
}
