package com.example.expensetracker.services;


import com.example.expensetracker.domain.Category;
import com.example.expensetracker.dto.UserSpentsByCategory;
import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.repositories.CategoryRepository;
import com.example.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements  CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TransactionRepository transactionRepository;





    @Override
    public List<Category> fetchAllCategories() throws EtResourceNotFoundException {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new EtResourceNotFoundException("No categories available");
        }
    }

    @Override
    public Optional<Category> fetchCategoryById(Integer categoryId) throws EtResourceNotFoundException {
        try {
            return categoryRepository.findById(categoryId);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Category not found");
        }
    }

    @Override
    public Category addCategory(String title, String description) throws EtBadRequestException {
        try {
            Category category = new Category(title, description);
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }

    }

    @Override
    public void updateCategory(Integer categoryId, String title, String description) throws EtBadRequestException {
        try{
            Category category=new Category(categoryId, title, description);
            categoryRepository.save(category);
        }catch (Exception e){
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeCategoryWithAllTransactions(Integer categoryId) throws EtResourceNotFoundException {
        try{
            categoryRepository.deleteById(categoryId);
        }catch (Exception e){
           throw new EtResourceNotFoundException("Category not found");
        }
    }



    @Override
    public List<UserSpentsByCategory> getUserSpentsByCategory(Integer userId) {
        List<UserSpentsByCategory> userSpentsByCategoryList = new ArrayList<>();


            List<Object[]> list = categoryRepository.GetUserSpentsByCategory(userId);



            list.forEach(item -> {
                UserSpentsByCategory userSpentsByCategory = new UserSpentsByCategory(item[0].toString(), Float.parseFloat(item[1].toString()));
                userSpentsByCategoryList.add(userSpentsByCategory);
            });


        return userSpentsByCategoryList;
    }


}
