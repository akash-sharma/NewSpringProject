package com.akash.model.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import com.akash.constant.Gender;

@Entity
@NamedQuery(
	    name="findPersonById",
	    query="SELECT p FROM Person p where p.id=:id"
	)

public class Person extends AbstractBaseDomain
{
	
	private String name;
	private int age;
	private boolean isNabalik;
	private Gender gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isNabalik() {
		return isNabalik;
	}

	public void setNabalik(boolean isNabalik) {
		this.isNabalik = isNabalik;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}