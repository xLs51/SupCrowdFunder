package com.supinfo.supcrowdfunderandroid.services;

import com.supinfo.supcrowdfunderandroid.dao.DaoFactory;
import com.supinfo.supcrowdfunderandroid.dao.CategoryDao;
import com.supinfo.supcrowdfunderandroid.model.Category;

import java.util.List;

public class CategoryService {

    public CategoryDao categoryDao;

    public CategoryService() {
    	categoryDao = DaoFactory.getCategoryDao();
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
    
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }
}