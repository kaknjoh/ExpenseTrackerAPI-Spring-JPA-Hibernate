package com.example.expensetracker.controllers;


import com.example.expensetracker.domain.Transaction;
import com.example.expensetracker.repositories.TransactionRepository;
import com.example.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.findAllTransactions(), HttpStatus.OK);
    }


    @PostMapping("/addtransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Map<String, Object> transactionMap){
        Integer transactionId=(Integer) transactionMap.get("transaction_id");
        Integer userId=(Integer) transactionMap.get("user_id");
        Integer categoryId= (Integer) transactionMap.get("category_id");
        Double amount= (Double) transactionMap.get("amount");
        String note=(String) transactionMap.get("note");
        String date= (String) transactionMap.get("transaction_date");
        Transaction transaction=transactionService.addTransaction(transactionId, amount, note, date, userId, categoryId);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);

    }


    @GetMapping("/percategory/{categoryid}")

    public ResponseEntity<List<Transaction>> getTransactionsPerCategory(@PathVariable("categoryid") Integer category_id){

        List<Transaction> transactionsPerCategory=transactionService.findTransactionByCategory(category_id);
        return new  ResponseEntity<>(transactionsPerCategory, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{transaction_id}")

    public ResponseEntity<?> deleteTransaction(@PathVariable("transaction_id") Integer transactionId){
        transactionService.removeTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
