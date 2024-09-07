package com.aakb.crypto.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
 * Implementation for calculating the hash-based message authentication code
 *
 * @author Adi Bhargava
 */
public class CalculateHMAC {
  /**
   * calculates the HMAC for a plain text using a symmetric key and algorithm
   *
   * @return String of Base64 encoded HMAC
   */
  public static String findHMAC(String plainText, String algorithm, SecretKey key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    Mac mac = Mac.getInstance(algorithm);
    mac.init(key);
    mac.update(plainText.getBytes());

    return Base64.getEncoder().encodeToString(mac.doFinal());
  }
}
