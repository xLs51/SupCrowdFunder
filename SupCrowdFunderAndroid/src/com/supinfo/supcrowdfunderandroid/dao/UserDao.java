package com.supinfo.supcrowdfunderandroid.dao;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.User;

public interface UserDao {
    List<User> getAllUsers();
    
    User getUserById(int id);
    
    void insertUser(User user);
    
    void updateUser(User user);
}