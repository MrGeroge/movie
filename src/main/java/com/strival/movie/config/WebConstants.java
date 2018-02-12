package com.strival.movie.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebConstants {
	private static final Logger logger=LoggerFactory.getLogger(WebConstants.class);
	
	public static String HOST_NAME;
	
	public static class Email{
		public static String HOST;
		public static String USERNAME;
		public static String PASSWORD;
		public static int PORT;
		public static boolean SSL;
	}

	static{
		String fileName="/mail-system.properties";
		
		Properties p = new Properties();  
	    
		try {
			InputStream in = WebConstants.class.getResourceAsStream(fileName);
			p.load(in);
			
			HOST_NAME=p.getProperty("hostName");
			
			logger.info("HOST_NAME set value:"+HOST_NAME);
			
			Email.HOST=p.getProperty("email.host");
			Email.USERNAME=p.getProperty("email.username");
			Email.PASSWORD=p.getProperty("email.password");
			Email.PORT=Integer.parseInt(p.getProperty("email.port"));
			Email.SSL=Boolean.parseBoolean(p.getProperty("email.isSSL","false"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	            
	}
}
