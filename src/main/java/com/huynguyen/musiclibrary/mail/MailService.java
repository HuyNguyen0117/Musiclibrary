package com.huynguyen.musiclibrary.mail;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.sun.mail.smtp.SMTPTransport;


public class MailService {
	
	final String HOSTNAME = "smtp.gmail.com";
	final String USERNAME = "huymusiclibrary@gmail.com";
	final String PASSWORD = "HuYTnguyen0117";
	
	public void sendMail(String message, String subject, String from, String to) throws EmailException{
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME,PASSWORD));
		email.setTLS(true);
		email.setSSL(true);
		email.setSubject(subject);
		
		email.setTextMsg(message);
		
		email.setFrom(from);
		email.addTo(to);
		email.send();

		 
	}
	
	
	public void mailAPI(String from, String to, String subject, String message){
		try{
			  Session mailSession = Session.getInstance(System.getProperties());
			  Transport transport = new SMTPTransport(mailSession,new URLName("localhost"));
			  transport.connect("localhost",25,null,null);
			  MimeMessage m = new MimeMessage(mailSession);
			  m.setFrom(new InternetAddress(from));
			  Address[] toAddr = new InternetAddress[] {
					  new InternetAddress(to)
			  };
			  m.setRecipients(javax.mail.Message.RecipientType.TO, toAddr);
			  m.setSubject(subject);
			  m.setSentDate(new java.util.Date());
			  m.setContent(message, "text/plain");
			  transport.sendMessage(m,m.getAllRecipients());
			  transport.close();
			  System.out.println("Thanks for sending mail!");
			}
			catch(Exception e){
			  System.out.println(e.getMessage());
			  e.printStackTrace();
			}

	}
	
}
