package com.JohnnyCodeZone.countriesapplication.service;

import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.model.TransactionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class TransactionReader {
    @Value("${file.transactions}")
    private String fileTransactionsPath;
    public static int transactionId=0;
    public List<Transaction> getTransactions(){

        try{
            return Files.lines(Path.of(fileTransactionsPath))
                    .map(this::lineToTransaction)
                    .collect(Collectors.toList());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private Transaction lineToTransaction(String line){
        String[] transactionParts = line.split("\\|");
        TransactionType type= TransactionType.valueOf(transactionParts[1]);
        return new Transaction(transactionParts[0], type, Double.parseDouble(transactionParts[2]));
    }
}
