package com.thiagoh.poker.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("StartupDate: " + context.getStartupDate());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
