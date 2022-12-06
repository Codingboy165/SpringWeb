package com.JohnnyCodeZone.countriesapplication.controller;

import com.JohnnyCodeZone.countriesapplication.model.TransactionType;
import lombok.RequiredArgsConstructor;
import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.service.TransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionManager transactionManager;
    @GetMapping
    public List<Transaction> getAll(@RequestParam(required = false) String product,
                                    @RequestParam(required = false) TransactionType type,
                                    @RequestParam(required = false) Double minAmount,
                                    @RequestParam(required = false) Double maxAmount) {
        System.out.println("Request all transaction");
        return transactionManager.getAllTransactions(product, type, minAmount, maxAmount);
    }

    @GetMapping("{id}")
    public Transaction getById(@PathVariable int id) {
        System.out.println("Got a product by id");
        return transactionManager.getById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        System.out.println("Added a transaction");
        return transactionManager.add(transaction);
    }

    @PutMapping("{id}")
    public Transaction update(@PathVariable int id, @RequestBody Transaction transaction) {
        System.out.println("Updated a product");
        return transactionManager.update(id, transaction);
    }

    @DeleteMapping("{id}")
    public Transaction delete(@PathVariable int id) {
        System.out.println("Deleted a product");
        return transactionManager.deleteById(id);
    }

    @GetMapping("/reports/mapByAType/{type}")
    public Map<String, List<Transaction>> mapByAType(@PathVariable String type) {
        System.out.println("Mapped by a type");
        return transactionManager.mapFromATypeToList(type);
    }

    @GetMapping("/reports/mapByAProduct/{product}")
    public Map<String, List<Transaction>> mapByAProduct(@PathVariable String product) {
        System.out.println("Mapped by a product");
        return transactionManager.mapFromAProductToList(product);
    }

    @GetMapping("/reports/type")
    public Map<TransactionType, List<Transaction>> mapByTypes() {
        System.out.println("Sorted by types");
        return transactionManager.getTransactionsByType();
    }

    @GetMapping("products/{product}")
    public List<Transaction> getAListOfProduct(@PathVariable String product){
        System.out.println("Get products without mapping");
        return transactionManager.getAllTransactionByProduct(product);
    }

    @GetMapping("reports/transactionSummation")
    public Double summationOfTransactions(){
        System.out.println("Summation of transactions");
        return transactionManager.getTheSumOfTransactions();
    }
}
