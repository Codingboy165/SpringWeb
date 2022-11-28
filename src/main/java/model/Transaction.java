package model;

import java.util.Objects;

public final class Transaction {

    private final TransactionType t;
    private final int id;
    private final String product;
    private final double amount;

    public Transaction(int id, String product,TransactionType type, double amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.t = type;
    }

    public int id() {
        return id;
    }

    public String product() {
        return product;
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
                Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, amount);
    }

    @Override
    public String toString() {
        return "Transaction[" +
                "id=" + id + ", " +
                "product=" + product + ", " +
                "amaunt=" + amount + ']';
    }

}
