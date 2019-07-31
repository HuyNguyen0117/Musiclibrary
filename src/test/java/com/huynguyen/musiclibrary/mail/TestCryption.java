package com.huynguyen.musiclibrary.mail;




public class TestCryption {
	
	private static EncryptionAndDecryption ed = new EncryptionAndDecryption();
	
	public static void main(String[] args){

		String str = ed.encrypt("huymusiclibrary@gmail.com");
		
		String str1 = str;
		
		String destr = ed.decrypt("ThisIsASecretKey", str);
		
		String test = "SDFsdf+bSdfre";
		System.out.println(test);
	
		System.out.println(str);
		
		System.out.println(destr);
		
		
	}

}
