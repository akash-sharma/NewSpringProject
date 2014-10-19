package com.akash.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.akash.model.domain.Authority;
import com.akash.model.domain.User;
import com.akash.model.domain.dao.ApplicationSessionDao;

@Service
public class LoadInitialDBService
{
	@Autowired
	@Qualifier(value="applicationSessionDao")
	private ApplicationSessionDao applicationSessionDao;
	
	public void loadAdminUser()
	{
		Authority adminAuthority=new Authority();
		adminAuthority.setAuthority("ROLE_ADMIN");
		Authority userAuthority=new Authority();
		userAuthority.setAuthority("ROLE_USER");
		User user=new User();
		user.setUsername("superadmin");
		user.setPassword("superadmin");
		user.setEmailId("superadmin@gmail.com");
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.getAuthorities().add(adminAuthority);
		adminAuthority.getUsers().add(user);
		ArrayList listOfObjects=new ArrayList();
		listOfObjects.add(user);
		listOfObjects.add(adminAuthority);
		listOfObjects.add(userAuthority);
		applicationSessionDao.persistAll(listOfObjects);
	}
}