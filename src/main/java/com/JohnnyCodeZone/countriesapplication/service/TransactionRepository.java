package com.JohnnyCodeZone.countriesapplication.service;

import com.JohnnyCodeZone.countriesapplication.model.Transaction;
import com.JohnnyCodeZone.countriesapplication.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
