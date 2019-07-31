package com.huynguyen.musiclibrary.mail;

import org.apache.commons.mail.EmailException;


public class TestMail {

	public static void main(String[] args) throws EmailException{
		
		MailService mailService = new MailService();
	
		String name = "Huy Nguyen";
		String message = "Welcome " + name + " Thanks for registering" +
				" \n Please click on this link to active your account: "
				+ "http://localhost:8080/musiclibrary/confirmemail?email=huynguyen&password=HuYTnguyen01117";
		
	
		mailService.sendMail(message, "Test", "huymusiclibrary@gmail.com", "huyng1@yahoo.com");
		
	}
}
		