package com.JohnnyCodeZone.countriesapplication.service;

import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionManager {
    private final TransactionReader transactionReader;
    private List<Transaction> transactions;

    public TransactionManager(TransactionReader transactionReader) {
        this.transactionReader = transactionReader;
        transactions = transactionReader.getTransactions();
    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    public List<Transaction> getAllTransactionByProduct(String product){
        return transactions.stream().filter(transaction-> (Objects.equals(product, transaction.product()))).collect(Collectors.toList());
    }

    public Transaction getTransactionById(int Id){
        return transactions.stream().filter(transaction -> transaction.id()== Id).findFirst().get();
    }

    public Transaction add(Transaction transaction){
        transactions.add(transaction);
        return transaction;
    }

    public Transaction update(int id, Transaction transaction){
        Transaction transaction1=getById(id);
        transaction1.setProduct(transaction.product());
        transaction1.setType(transaction.type());
        transaction1.setAmount(transaction.amount());
        return transaction1;
    }

    public Transaction getById(int id) {
        return transactions.stream()
                .filter(c -> c.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country missing"));
    }

    public Transaction deleteById(int id){
        Transaction transaction=getById(id);
        transactions.remove(transaction);
        return transaction;
    }

    public Map<String , List<Transaction>> mapFromATypeToList(String type){
        Map<String , List<Transaction>> result = new HashMap<>();
        result.put(type,transactions.stream().filter(t-> {
            t.type();
            return false;
        }).collect(Collectors.toList()));
        return result;
    }

    public Map<String , List<Transaction>> mapFromAProductToList(String product){
        Map<String , List<Transaction>> result = new HashMap<>();
        result.put(product,transactions.stream().filter(t-> {
            t.product();
            return false;
        }).collect(Collectors.toList()));
        return result;
    }

}
