package controller;

import lombok.RequiredArgsConstructor;
import model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.TransactionManager;

import java.util.List;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionManager transactionManager;

    @GetMapping
    public List<Transaction> getAll(@RequestParam(required = false)String product){
        System.out.println("Request all transaction");
        if(product!=null){
            return transactionManager.getAllTransactionByProduct(product);
        }else {
            return transactionManager.getAllTransactions();
        }
    }
}
