package com.supinfo.supcrowdfunderandroid.dao;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Category;

public interface CategoryDao {
    List<Category> getAllCategories();
    
    Category getCategoryById(int id);
}