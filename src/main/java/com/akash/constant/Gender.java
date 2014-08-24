package com.akash.constant;

public enum Gender
{
	Male("M"),
	Female("F"),
	Others("others");
	
	private String value;
	Gender(String value){
		this.value=value;
	}
	public String toString(){
		return value;
	}
}