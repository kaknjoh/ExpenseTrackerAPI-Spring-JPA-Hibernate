package com.example.expensetracker.services;

import com.example.expensetracker.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public List<Transaction> findAllTransactions();

    public Optional<Transaction> findTransactionById(Integer id);

    public void removeTransaction(Integer transactionId);
    public Transaction addTransaction(Integer transactionId,  Double amount, String note, String date,  Integer userId, Integer categoryId);

    public List<Transaction> findTransactionByCategory(Integer categoryId);
}
