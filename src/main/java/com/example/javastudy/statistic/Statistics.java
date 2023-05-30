package com.example.javastudy.statistic;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Statistics {
    private static final Logger logger = LogManager.getLogger(Statistics.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    public void incCounter() {
        logger.info("inc counter");
        counter.getAndIncrement();
    }

    public Integer getCounter() {
        logger.info("registered");
        return counter.get();
    }
}
