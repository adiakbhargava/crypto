package com.aakb.crypto.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Asymmetric {
  private static final String RSA = "RSA";
  
  // generate public, private key pair
  public static KeyPair generateKeyPair(int keySize, String algorithm) throws NoSuchAlgorithmException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
    kpg.initialize(keySize);
    
    return kpg.generateKeyPair();
  }
}
