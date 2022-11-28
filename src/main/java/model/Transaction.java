package model;

import java.util.Objects;

public class Transaction {
    private final int id;
    private final String product;
    private final TransactionType t;
    private final double amount;

    public Transaction(int id, String product, TransactionType t, double amount) {
        this.id = id;
        this.product = product;
        this.t = t;
        this.amount = amount;
    }

    public int id() {
        return id;
    }

    public String product() {
        return product;
    }

    public TransactionType t() {
        return t;
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
                Objects.equals(this.t, that.t) &&
                Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, t, amount);
    }

    @Override
    public String toString() {
        return "Transaction[" +
                "id=" + id + ", " +
                "product=" + product + ", " +
                "t=" + t + ", " +
                "amount=" + amount + ']';
    }


}
