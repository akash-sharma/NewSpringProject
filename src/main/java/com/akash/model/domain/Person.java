package com.akash.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.akash.constant.Gender;

@Entity
public class Person implements BaseDomain
{
	@Id @GeneratedValue
	private long id;
	@Version
	private int version;
	 
	private String name;
	private int age;
	private boolean isNabalik;
	private Gender gender;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

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