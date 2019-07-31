package com.huynguyen.musiclibrary.mail;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AnotherTest {

     public static void main(String[] args) {

          String plainText = "This is a plaintext message. You should be able to read this.";

          try {
               // Encrypt ze plaintext..
               SecretKey mySecretKey = KeyGenerator.getInstance("AES").generateKey();
               Cipher myCypherOut = Cipher.getInstance("AES");
               myCypherOut.init(Cipher.ENCRYPT_MODE, mySecretKey);
               byte[] cipherText = myCypherOut.doFinal(plainText.getBytes());

              // And Now Decrypt ze plaintext
              Cipher myCypherIn = Cipher.getInstance("AES");
              myCypherIn.init(Cipher.DECRYPT_MODE, mySecretKey);
              String decryptText = new String(myCypherIn.doFinal(cipherText));

              // Display the plaintext, cipher text et al.
              System.out.println("PlainText: " + plainText);
              System.out.println("Encrypted Text: " + new String(cipherText, "UTF-8"));
              System.out.println("Decrypted Text: " + decryptText);
          } catch (Exception ex1)
          {
              System.out.print(ex1.toString());
          }
     }
}