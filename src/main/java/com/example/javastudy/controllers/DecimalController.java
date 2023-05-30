package com.example.javastudy.controllers;

import com.example.javastudy.decimal.*;
import com.example.javastudy.service.DecimalService;
import com.example.javastudy.statistic.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decimal")
public class DecimalController {

    private final DecimalService storage;

    private final Statistics stats;

    public DecimalController(DecimalService storage, Statistics stats) {
        this.storage = storage;
        this.stats = stats;
    }

    @GetMapping
    public Decimal decToBin(@RequestParam(value = "value", defaultValue = "0") Double value) {
        stats.incCounter();
        return storage.getByValue(value);

    }

    @PostMapping
    public ResponseEntity<?> bulkDecBin(@RequestParam("values") List<Double> request) {
        DecimalStatistics values;
        List<Decimal> decimals = new ArrayList<>();
        request.forEach((x) -> {
            decimals.add(storage.getByValue(x));
        });
        values = new DecimalStatistics(decimals.stream().map((x) -> x.getDecForm())
                .collect(DoubleSummaryStatistics::new,
                        DoubleSummaryStatistics::accept,
                        DoubleSummaryStatistics::combine));
        stats.incCounter();
        return new ResponseEntity<>(new DecimalBulkResponse(decimals, values), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> asyncDecBin(@RequestParam("values") List<Double> request) {
        request.forEach((x) -> {
            CompletableFuture.runAsync(() -> storage.getByValue(x));
        });
        stats.incCounter();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
