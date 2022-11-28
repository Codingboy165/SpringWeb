package com.JohnnyCodeZone.countriesapplication.model;

import java.util.Objects;

public class Transaction {

    private int id;
    private String product;
    private TransactionType type;
    private double amount;

    public Transaction(int id, String product, TransactionType type, double amount) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Transaction) obj;
        return this.id == that.id &&
                Objects.equals(this.product, that.product) &&
                Objects.equals(this.type, that.type) &&
                Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, type, amount);
    }

    @Override
    public String toString() {
        return "Transaction[" +
                "id=" + id + ", " +
                "product=" + product + ", " +
                "t=" + type + ", " +
                "amount=" + amount + ']';
    }


}
