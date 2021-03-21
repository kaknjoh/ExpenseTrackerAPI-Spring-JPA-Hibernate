package com.example.expensetracker.services;

import com.example.expensetracker.domain.Category;
import com.example.expensetracker.domain.Transaction;
import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtConvertFail;
import com.example.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    CategoryService categoryService;

    @Autowired

    UserService userService;


    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> findTransactionByCategory(Integer categoryId) {
        Category category=categoryService.fetchCategoryById(categoryId).orElse(null);
        return transactionRepository.findByCategory(category);
    }

    @Override
    public Transaction addTransaction(Integer transactionId, Double amount,String  note,  String date, Integer userId, Integer categoryId) {
        Category category= categoryService.fetchCategoryById(categoryId).orElse(null);
        User user= userService.findUserById(userId);

        try{
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            //Date transactionDate=simpleDateFormat.parse(date);
            Transaction transaction = new Transaction(transactionId,  amount, note,  user, category);
            return transaction;

        }catch (Exception e){
            throw new EtConvertFail("Error converting string to date");
        }


    }

    @Override
    public void removeTransaction(Integer transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
