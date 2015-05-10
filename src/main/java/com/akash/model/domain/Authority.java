package com.akash.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority extends AbstractBaseDomain implements GrantedAuthority
{
	private static final long serialVersionUID = 1234567L;

	private String authority;
	
	@ManyToMany(mappedBy="authorities")
	Set<User> users=new HashSet<User>();
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setAuthority(String authority){
		this.authority=authority;
	}
	
	public String getAuthority() {
		return authority;
	}
}