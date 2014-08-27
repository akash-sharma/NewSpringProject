package com.akash.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.akash.constant.Gender;

@Entity
public class Person
{
	@Id @GeneratedValue
	private long id;
	@Version
	private int version;
		
	@NotNull
	@NotEmpty
	@Size(min=2, max=30) 
	private String name;
	
	@NotNull @Min(18) @Max(100)
	private int age;
	private boolean isNabalik;
	
	@NotNull
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