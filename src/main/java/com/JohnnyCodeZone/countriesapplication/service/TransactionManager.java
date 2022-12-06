package com.JohnnyCodeZone.countriesapplication.service;

import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.model.TransactionType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("TManager")
public class TransactionManager {
    private final TransactionRepository transactionRepository;

    public TransactionManager(TransactionReader transactionReader,
                              TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        transactionRepository.saveAll(transactionReader.getTransactions());
        System.out.println("Finished reading transactions");
    }

    public List<Transaction> getAllTransactions(String product, TransactionType type,
                                                Double minAmount, Double maxAmount) {
        Stream<Transaction> stream = transactionRepository.findAll().stream();
        if (product != null) {
            stream = stream.filter(transaction -> transaction.product().equals(product));
        }
        if (type != null) {
            stream = stream.filter(transaction -> transaction.type().equals(type));
        }
        if (minAmount != null) {
            stream = stream.filter(transaction -> transaction.amount() >= minAmount);
        }
        if (maxAmount != null) {
            stream = stream.filter(transaction -> transaction.amount() <= maxAmount);
        }
        return transactionRepository.saveAll(stream.toList());
    }

    public List<Transaction> getAllTransactionByProduct(String product) {
        return transactionRepository.findAll().stream().filter(transaction ->
                (Objects.equals(product, transaction.product()))).collect(Collectors.toList());
    }

    public Transaction add(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(int id, Transaction transaction) {
        Transaction transactionResult = getById(id);
        transactionResult.setProduct(transaction.product());
        transactionResult.setType(transaction.type());
        transactionResult.setAmount(transaction.amount());
        return transactionResult;
    }

    public Transaction getById(int id) {
        return transactionRepository.findAll().stream().
                filter(t->t.id()==id).findFirst().orElseThrow(() ->
                new RuntimeException("Transaction missing"));
    }

    public Transaction deleteById(int id) {
        Transaction transaction = getById(id);
        transactionRepository.deleteById((long) id);
        return transaction;
    }

    public Map<String, List<Transaction>> mapFromATypeToList(String type) {
        Map<String, List<Transaction>> result = new HashMap<>();
        TransactionType transactionType = TransactionType.valueOf(type);
        result.put(type, transactionRepository.findAll().stream().filter(t ->
                t.type().equals(transactionType)).collect(Collectors.toList()));
        return result;
    }

    public Map<String, List<Transaction>> mapFromAProductToList(String product) {
        Map<String, List<Transaction>> result = new HashMap<>();
        result.put(product, transactionRepository.findAll().stream().filter(t ->
                t.product().equals(product)).collect(Collectors.toList()));
        return result;
    }

    public Map<TransactionType, List<Transaction>> getTransactionsByType() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public Double getTheSumOfTransactions() {

        Double buySum = 0.0;
        Double sellSum = 0.0;

        List<Double> buyTransactions = transactionRepository.
                findAll().stream().
                filter(transaction -> transaction.type() == TransactionType.BUY).
                map(Transaction::amount).toList();
        for (Double i : buyTransactions) {
            buySum += i;
        }

        List<Double> sellTransactions = transactionRepository.
                findAll().stream().
                filter(transaction -> transaction.type() == TransactionType.SELL).
                map(Transaction::amount).toList();
        for (Double i : sellTransactions) {
            sellSum += i;
        }

        return buySum-sellSum;
    }
}
