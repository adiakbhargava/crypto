package com.aakb.crypto.impl;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.SecretKey;

/**
 * Symmetric key generator
 * 
 * @author Adi Bhargava
 */
public class Symmetric {
  /**
   * Creates a symmetric secret key based on the keySize and algorithm given as input
   *
   * @return SecretKey
   */
  public static SecretKey createSymmKey(int keySize, String algorithm) throws NoSuchAlgorithmException {
    KeyGenerator keygenerator = KeyGenerator.getInstance(algorithm);
    keygenerator.init(keySize);

    return keygenerator.generateKey();
  }
}
