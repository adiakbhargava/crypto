package com.aakb.crypto.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class CalculateHMAC {
  // Signature, KeyGenerator, Mac, Cipher, SecretKeyFactory, MessageDigest
  public static String findHMAC(String plain, String algo, SecretKey key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    Mac mac = Mac.getInstance(algo);
    mac.init(key);
    mac.update(plain.getBytes());

    return Base64.getEncoder().encodeToString(mac.doFinal());
  }
}
