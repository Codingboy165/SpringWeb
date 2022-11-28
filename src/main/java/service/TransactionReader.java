package service;

import com.fasterxml.jackson.databind.util.EnumValues;
import lombok.Value;
import model.Transaction;
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

    public List<Transaction> getTransactions(){
        try{
            return Files.lines(Path.of(fileTransactionsPath)).
                    map(this::lineToTransaction).
                    collect(Collectors.toList());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private Transaction lineToTransaction(String line){
        String[] transactionParts = line.split("\\|");
        return new Transaction(Integer.parseInt(transactionParts[0]), transactionParts[1], transactionParts[2]), Double.parseDouble(transactionParts[3]));
    }
}
