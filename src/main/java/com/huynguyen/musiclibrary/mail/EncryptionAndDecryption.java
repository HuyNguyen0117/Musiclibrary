package com.huynguyen.musiclibrary.mail;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


	 
public class EncryptionAndDecryption {

	private static byte[] raw = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
	
	private static SecureRandom rnd = new SecureRandom();
	
	private static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(16));

	
	public String encrypt(String value) {
	    try {
	
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	        byte[] encrypted = cipher.doFinal(value.getBytes());
	        return Base64.encodeBase64String(encrypted);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	public String decrypt(String key, String encrypted) {
	    try {
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	      
	        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
	  
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
}