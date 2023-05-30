package com.example.javastudy.storage.entity;

import com.example.javastudy.decimal.Decimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "decimal")
public class DecimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id; 

    @Column(name = "decimal")
    private Double value;
    @Column(name = "binary")
    private String binary;

    public DecimalEntity(){}
    public DecimalEntity(Double i){
      this.value = i;
      this.binary = new Decimal(i).getBinForm();
    }

    public long id(){
        return id;
    }

    public Double value(){
        return value;
    }

    public String binary(){
        return binary;
    }

}
