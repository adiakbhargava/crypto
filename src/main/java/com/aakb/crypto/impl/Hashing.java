package com.aakb.crypto.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation for hashing plain text
 *
 * @author Adi Bhargava
 */
public class Hashing {
  /**
   * Hashes plain text based off of the hashing algorithm
   *
   * @return byte[] representing the hashed text
   */
  public static byte[] hashText(String plaintext, String algorithm) throws IOException, NoSuchAlgorithmException {
    // convert plain text to bytes
    byte[] messageBytes = plaintext.getBytes();
    // writes out message bytes into a ByteArrayOutputStream
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    byteStream.write(messageBytes);
    byte[] preHashedValues = byteStream.toByteArray();
    // create MessageDigest
    MessageDigest messagedigest = MessageDigest.getInstance(algorithm);

    return messagedigest.digest(preHashedValues);
  }
}
