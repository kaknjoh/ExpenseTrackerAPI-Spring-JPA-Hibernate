package com.example.expensetracker.repositories;

import com.example.expensetracker.domain.Category;
import com.example.expensetracker.domain.Transaction;
import com.example.expensetracker.dto.UserSpentsByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findById(Integer Id);

    List<Transaction> findAll();

    List<Transaction> findByCategory(Category category);



}
