package com.akash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent>
{
	@Autowired
	private LoadInitialDBService loadInitialDBService;
	
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event)
	{
        ApplicationContext applicationContext = event.getApplicationContext();
        if( applicationContext.getParent()==null)
        	bootApplicationData();
    }
	
	private void bootApplicationData()
	{
		loadInitialDBService.loadAdminUser();
	}
}