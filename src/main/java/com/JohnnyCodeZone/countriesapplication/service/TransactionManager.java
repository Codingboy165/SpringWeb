package com.JohnnyCodeZone.countriesapplication.service;

import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("TManager")
public class TransactionManager {
   private final TransactionRepository transactionRepository;

    public TransactionManager(TransactionReader transactionReader, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        transactionRepository.saveAll(transactionReader.getTransactions());
        System.out.println("Finished reading transactions");
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionByProduct(String product){
        return getAllTransactions().stream().filter(transaction-> (Objects.equals(product, transaction.product()))).collect(Collectors.toList());
    }

    public Transaction add(Transaction transaction){
       return transactionRepository.save(transaction);
    }

    public Transaction update(int id, Transaction transaction){
        Transaction transaction1=getById(id);
        transaction1.setProduct(transaction.product());
        transaction1.setType(transaction.type());
        transaction1.setAmount(transaction.amount());
        return transaction1;
    }

    public Transaction getById(int id) {
        return transactionRepository.findById((long)id).orElseThrow(()-> new RuntimeException("Transaction missing"));
    }

    public Transaction deleteById(int id){
        Transaction transaction=getById(id);
        transactionRepository.deleteById((long)id);
        return transaction;
    }

    public Map<String , List<Transaction>> mapFromATypeToList(String type){
        Map<String , List<Transaction>> result = new HashMap<>();
        result.put(type,getAllTransactions().stream().filter(t-> {
            t.type();
            return false;
        }).collect(Collectors.toList()));
        return result;
    }

    public Map<String , List<Transaction>> mapFromAProductToList(String product){
        Map<String , List<Transaction>> result = new HashMap<>();
        result.put(product,getAllTransactions().stream().filter(t->t.product().equals(product)).collect(Collectors.toList()));
        return result;
    }

}
