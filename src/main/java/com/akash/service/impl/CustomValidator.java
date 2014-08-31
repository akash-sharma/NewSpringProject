package com.akash.service.impl;

public class CustomValidator
{
	public static long parseStrTolong(String str, long defaultLongValue)
	{
		Long strAsLong=0l;
		try{
			strAsLong=Long.parseLong(str);
		}
		catch(NumberFormatException e){
			strAsLong=defaultLongValue;
		}
		return strAsLong;
	}
}