package com.example.expensetracker.services;

import com.example.expensetracker.domain.Category;
import com.example.expensetracker.dto.UserSpentsByCategory;
import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> fetchAllCategories();

    Optional<Category> fetchCategoryById(Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(String title, String description) throws EtBadRequestException;

    List<UserSpentsByCategory> getUserSpentsByCategory(Integer userId);

    void updateCategory(Integer categoryId, String title, String description) throws EtBadRequestException;

    void removeCategoryWithAllTransactions(Integer categoryId) throws EtResourceNotFoundException;


}
