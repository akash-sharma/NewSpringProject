package com.akash.model.domain.dao;

import com.akash.model.domain.User;

public interface UserDao
{
	User findByUsername(String username);
	
}