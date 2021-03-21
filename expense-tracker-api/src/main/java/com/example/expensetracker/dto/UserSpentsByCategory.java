package com.example.expensetracker.dto;

public class UserSpentsByCategory {

    private String categoryName;
    private  float SpentByCategory;


    public UserSpentsByCategory(String categoryName, float spentByCategory) {
        this.categoryName = categoryName;
        this.SpentByCategory = spentByCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getSpentByCategory() {
        return SpentByCategory;
    }

    public void setSpentByCategory(float spentByCategory) {
        SpentByCategory = spentByCategory;
    }
}
