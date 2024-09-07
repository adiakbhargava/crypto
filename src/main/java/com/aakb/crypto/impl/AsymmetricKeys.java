package com.aakb.crypto.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation for the generation of asymmetric key pairs
 *
 * @author Adi Bhargava
 */
public class AsymmetricKeys {
  /**
   * Creates an asymmetric key pair given the key size and algorithm
   *
   * @return KeyPair of public and private keys
   */
  public static KeyPair generateKeyPair(int keySize, String algorithm) throws NoSuchAlgorithmException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
    kpg.initialize(keySize);
    
    return kpg.generateKeyPair();
  }
}
