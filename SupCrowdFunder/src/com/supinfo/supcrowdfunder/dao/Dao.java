package com.supinfo.supcrowdfunder.dao;

import java.util.List;

public interface Dao<T> 
{
	T add(T newObject);
    void update(T object);
    void remove(long id);
    T findById(long id);
    List<T> findAll();
}