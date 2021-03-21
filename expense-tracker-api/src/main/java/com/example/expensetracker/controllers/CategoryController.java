package com.example.expensetracker.controllers;


import com.example.expensetracker.domain.Category;
import com.example.expensetracker.dto.UserSpentsByCategory;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;




    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories( ){
        List<Category> categories=categoryService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/addcategory")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody Map<String, Object> categoryMap){
        String title= (String) categoryMap.get("title");
        String description=(String) categoryMap.get("description");
        Category category=categoryService.addCategory(title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);


    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId){

        Optional<Category> category=categoryService.fetchCategoryById(categoryId);
        if(category.isPresent()){
            Category categoryById=category.get();
            return new ResponseEntity<>(categoryById, HttpStatus.OK);
        }else {
            throw new EtResourceNotFoundException("Category not found");

        }


    }



    @GetMapping("/spents/{userId}")
    public ResponseEntity<List<UserSpentsByCategory>> getUserSpents(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(categoryService.getUserSpentsByCategory(userId), HttpStatus.OK);
    }


    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") Integer categoryId, @RequestBody Map<String, Object> categoryMap){
        Integer category_id=categoryId;
        String title= (String) categoryMap.get("title");
        String description=(String) categoryMap.get("description");
        categoryService.updateCategory(category_id,title, description);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.removeCategoryWithAllTransactions(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





/*
    @GetMapping("/spents")
    public List<UserSpentsByCategory> userSpentsByCategoryResponseEntity(HttpServletRequest request){
        int userId=(Integer) request.getAttribute("userId");

        return categoryService.getAllSpentsPerUserByCategory(userId, 1);





    }*/










}
