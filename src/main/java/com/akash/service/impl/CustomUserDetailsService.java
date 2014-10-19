package com.akash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akash.model.domain.User;
import com.akash.model.domain.UserInSession;
import com.akash.model.domain.dao.UserDao;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
	/*
	 * http://www.baeldung.com/spring-security-authentication-provider
	 * 
	 * This service provided by spring security fetches the UserDetails object for a username.
	 * This UserDetails object contains username, password and any other verification token.
	 * 
	 * When user logs in, it calls this service to find a user with given username,
	 * then spring security verifies its password on its own.
	 * 
	 */
	
	@Autowired
	@Qualifier(value="userDao")
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserDetails userDetails=null;
		User user=userDao.findByUsername(username);
		if(user!=null)
		{
			boolean enabled = user.isEnabled();
	        boolean accountNonExpired = user.isAccountNonExpired();
	        boolean credentialsNonExpired = user.isCredentialsNonExpired();
	        boolean accountNonLocked = user.isAccountNonLocked();
	        if(enabled && accountNonExpired && credentialsNonExpired && accountNonLocked) {
	        	userDetails=new UserInSession(user.getUsername(),user.getPassword(),user.getEmailId(),user.getAuthorities() );
	        	System.out.println("userDetails object after login :"+userDetails);
	        }
		}
		return userDetails;
	}
}