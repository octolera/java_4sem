package com.example.javastudy.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.javastudy.decimal.Decimal;
import com.example.javastudy.storage.entity.DecimalEntity;
import com.example.javastudy.storage.repository.DecimalRepository;

import java.lang.Double;

@Service
public class DecimalService {

    private final DecimalRepository repository;
    private static final Logger logger = LogManager.getLogger(DecimalService.class);

    DecimalService(DecimalRepository repository) {
        this.repository = repository;
    }

    @Cacheable("values")
    public Decimal getByValue(Double key) {
        var decimal = repository.findByValue(key);
        if (decimal == null) {
            decimal = new DecimalEntity(key);
            repository.save(decimal);
            logger.info(String.format("Saved to db - decimal %f, binary - %s", decimal.value(), decimal.binary()));
        }
        logger.info("Cached");
        return new Decimal(decimal);
    }
}