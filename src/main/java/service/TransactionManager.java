package service;

import model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionManager {
    private final TransactionReader transactionReader;
    private final List<Transaction> transactions;

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

}
