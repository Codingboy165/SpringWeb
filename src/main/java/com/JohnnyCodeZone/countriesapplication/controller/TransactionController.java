package com.JohnnyCodeZone.countriesapplication.controller;

import lombok.RequiredArgsConstructor;
import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.service.TransactionManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("hello")
public class TransactionController {

    private final TransactionManager transactionManager;

    @GetMapping
    public List<Transaction> getAll(@RequestParam(required = false) String product){
        System.out.println("Request all transaction");
        if(product!=null){
            return transactionManager.getAllTransactionByProduct(product);
        }else {
            return transactionManager.getAllTransactions();
        }
    }

    @GetMapping("{id}")
    public Transaction getById(@PathVariable int id){
        return  transactionManager.getById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction){
        return transactionManager.add(transaction);
    }

    @PutMapping("{id}")
    public Transaction update(@PathVariable int id , @RequestBody Transaction transaction){
    return transactionManager.update(id,transaction);
    }

    @DeleteMapping("{id}")
    public Transaction delete(@PathVariable int id){
        return transactionManager.deleteById(id);
    }

    @GetMapping("/reports/type")
    public Map<String , List<Transaction>> mapByType(String type){
        return transactionManager.mapFromATypeToList(type);
    }

    @GetMapping("/reports/product")
    public Map<String , List<Transaction>> mapByProduct(String product){
        return transactionManager.mapFromAProductToList(product);
    }
}
