package com.aakb.crypto.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class AsymmetricKeys {
  // generate public, private key pair
  public static KeyPair generateKeyPair(int keySize, String algorithm) throws NoSuchAlgorithmException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
    kpg.initialize(keySize);
    
    return kpg.generateKeyPair();
  }
}
