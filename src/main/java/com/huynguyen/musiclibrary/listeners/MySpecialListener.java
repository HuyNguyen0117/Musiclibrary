package com.huynguyen.musiclibrary.listeners;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

public class MySpecialListener extends ContextLoaderListener {

	private static Log log = LogFactory.getLog(MySpecialListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.debug("My web app Loaded");
	  }
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		log.debug("My web app Destroyed");
		
        try {
            java.sql.Driver mySqlDriver = DriverManager.getDriver("jdbc:mysql://localhost:3306/");
            DriverManager.deregisterDriver(mySqlDriver);
            log.info("Deregistered driver");
        } catch (SQLException ex) {
            log.info("Could not deregister driver:".concat(ex.getMessage()));
        } 
       

   }
	
	
}
