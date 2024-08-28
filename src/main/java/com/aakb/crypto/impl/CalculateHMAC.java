package com.aakb.crypto.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
