package com.akash.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent>
{
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event)
	{
		System.out.println("event object when context refreshed:"+event);
        ApplicationContext applicationContext = event.getApplicationContext();
        if( applicationContext.getParent()==null)
        {
        	System.out.println("event object for rootApplicationContext object : "+event);
        	bootApplicationData();
        }
    }
	
	private void bootApplicationData()
	{
		//operations you want to perform at loading time
	}
}