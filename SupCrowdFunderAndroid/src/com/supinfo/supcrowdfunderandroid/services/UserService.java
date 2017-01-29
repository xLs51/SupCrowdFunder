package com.supinfo.supcrowdfunderandroid.services;

import com.supinfo.supcrowdfunderandroid.dao.DaoFactory;
import com.supinfo.supcrowdfunderandroid.dao.UserDao;
import com.supinfo.supcrowdfunderandroid.model.User;

import java.util.List;

public class UserService {

    public UserDao userDao;

    public UserService() {
    	userDao = DaoFactory.getUserDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    public void insertUser(User user) {
    	userDao.insertUser(user);
    }
    
    public void updateUser(User user) {
    	userDao.updateUser(user);
    }
}