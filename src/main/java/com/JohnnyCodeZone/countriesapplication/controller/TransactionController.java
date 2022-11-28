package com.JohnnyCodeZone.countriesapplication.controller;

import lombok.RequiredArgsConstructor;
import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.service.TransactionManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}
