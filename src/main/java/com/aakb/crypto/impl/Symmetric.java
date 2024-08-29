package com.aakb.crypto.impl;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import javax.crypto.SecretKey;


/**
 * AES implementation of Symmetric key generator
 * 
 * @author Aditya Bhargava
 *
 */
public class Symmetric {

  public static void getAllAlgos() {
    for (Provider provider : Security.getProviders()) {
      System.out.println(provider);
      System.out.println(
          "===============================================================================");

      for (Provider.Service service : provider.getServices()) {

        if (service.getType().equals("MessageDigest")) {
          System.out.println(service.getType() + "--------------" + service);

        }
        service.getAlgorithm();
        service.getType();
        // String algorithm = service.getAlgorithm();
        // ...
        // Signature, KeyGenerator, Mac, Cipher, SecretKeyFactory, MessageDigest
      }
    }
  }

  /**
   * creates secret key using AES
   */
  public static SecretKey createAESKey(int keySize, String algorithm) throws NoSuchAlgorithmException {
    // create a key with AES algorithm
    KeyGenerator keygenerator = KeyGenerator.getInstance(algorithm);
    keygenerator.init(keySize);
    return keygenerator.generateKey();


  }

  /**
   * creates secret key using AES
   */
  public static String convertSecretKeyToBase64(SecretKey secretKey) {
    byte[] rawData = secretKey.getEncoded();
    return Base64.getEncoder().encodeToString(rawData);

  }

  public static String convertSecretKeyToBits(SecretKey secretKey) {
    StringBuilder sb = new StringBuilder();

    for (byte b : secretKey.getEncoded()) {
      sb.append(String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(' ', '0'));
    }

    return sb.toString();
  }

  public static void main(String[] args) throws NoSuchAlgorithmException {

    getAllAlgos();

    SecretKey symmetrickey = createAESKey(256, "AES");
    String keyString = convertSecretKeyToBase64(symmetrickey);
    System.out
        .println("key: " + keyString + "\nlength: " + symmetrickey.getEncoded().length + " bytes");
    String bitKeyString = convertSecretKeyToBits(symmetrickey);
    System.out.println(
        "bit representation: " + bitKeyString + "\nlength: " + bitKeyString.length() + " bits\n");


    SecretKey symmetrickey1 = createAESKey(192, "AES");
    System.out.println("key: " + convertSecretKeyToBase64(symmetrickey1) + "\nlength: "
        + symmetrickey1.getEncoded().length + " bytes");
    String bitKeyString1 = convertSecretKeyToBits(symmetrickey1);
    System.out.println(
        "bit representation: " + bitKeyString1 + "\nlength: " + bitKeyString1.length() + " bits\n");

    SecretKey symmetrickey2 = createAESKey(256, "AES");
    System.out.println("key: " + convertSecretKeyToBase64(symmetrickey2) + "\nlength: "
        + symmetrickey2.getEncoded().length + " bytes");
    String bitKeyString2 = convertSecretKeyToBits(symmetrickey2);
    System.out.println(
        "bit representation: " + bitKeyString2 + "\nlength: " + bitKeyString2.length() + " bits\n");

  }
}
