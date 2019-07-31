package com.huynguyen.musiclibrary.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestService {
	
	private static ServerValidation svValidation;
	
	@BeforeClass
	public static void testSetup() {
		svValidation = new ServerValidation();
		
	}

	@AfterClass
	public static void testCleanup() {
	  
	}
	
	@Test
	public void testIsEmailRegistered() {
		assertTrue(svValidation.isThisEmailRegistered("alex@yahoo.com"));
	}
	
	@Test
	public void testGetSongByName(){
		assertFalse(svValidation.isThisSongExisted("Anh Nho"));
	}
	@Test
	public void testGetAlbumByName(){
		assertFalse(svValidation.isThisAlbumExisted("Xin Loi Tinh Yeu"));
	}

}
