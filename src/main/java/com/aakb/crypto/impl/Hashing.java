package com.aakb.crypto.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class Hashing {
  public static byte[] hashText(String plaintext, String algorithm) throws IOException, NoSuchAlgorithmException {
    byte[] messageBytes = plaintext.getBytes();
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    byteStream.write(messageBytes);
    byte[] preHashedValues = byteStream.toByteArray();
    MessageDigest messagedigest = MessageDigest.getInstance(algorithm);
    
    return messagedigest.digest(preHashedValues);
  }

}
