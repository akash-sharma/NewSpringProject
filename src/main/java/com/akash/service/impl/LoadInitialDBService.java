package com.akash.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.model.domain.Authority;
import com.akash.model.domain.User;
import com.akash.model.domain.dao.BaseDao;

@Service
public class LoadInitialDBService {

	@Inject
	@Named("baseDao")
	private BaseDao baseDao;

	@Transactional
	public void loadAdminUser() {
		Authority adminAuthority = new Authority();
		adminAuthority.setAuthority("ROLE_ADMIN");
		Authority userAuthority = new Authority();
		userAuthority.setAuthority("ROLE_USER");
		User user = new User();
		user.setUsername("superadmin");
		user.setPassword("superadmin");
		user.setEmailId("superadmin@gmail.com");
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.getAuthorities().add(adminAuthority);
		adminAuthority.getUsers().add(user);
		ArrayList listOfObjects = new ArrayList();
		baseDao.persist(user);
		baseDao.persist(adminAuthority);
		baseDao.persist(userAuthority);
	}
}