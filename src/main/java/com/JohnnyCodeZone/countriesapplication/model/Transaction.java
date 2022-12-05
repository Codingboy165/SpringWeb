package com.JohnnyCodeZone.countriesapplication.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Transaction {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String product;
    @Column
    private TransactionType type;
    @Column
    private double amount;

    public Transaction(String product, TransactionType type, double amount) {
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int id() {
        return id;
    }

    public String product() {
        return product;
    }

    public TransactionType type() {
        return type;
    }

    public double amount() {
        return amount;
    }

}
