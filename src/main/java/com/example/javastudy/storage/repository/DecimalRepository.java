package com.example.javastudy.storage.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.javastudy.storage.entity.DecimalEntity;

public interface DecimalRepository extends CrudRepository<DecimalEntity, Long> {
    DecimalEntity findByValue(Double value);
}
