package com.supinfo.supcrowdfunder.dao;

import com.supinfo.supcrowdfunder.entity.User;

public interface UserDao extends Dao<User>
{
	User getUserByLogin(String mail, String password);
	User getUserByMail(String mail);
	boolean isMailAlreadyUsed(String mail);
	boolean isMailAlreadyUsedByUser(String mail, User user);
}