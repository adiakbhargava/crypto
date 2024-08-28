package com.aakb.crypto.impl;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SignVerify {
  public static byte[] sign(byte[] plainByteArr, PrivateKey privKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signCipher = Signature.getInstance(algorithm);
    signCipher.initSign(privKey);
    signCipher.update(plainByteArr);
    
    byte[] signByteArr = signCipher.sign();
    // System.out.println(new String(signByteArr));
    // System.out.println("BASE64 - " + Base64.getEncoder().encodeToString(signByteArr));
    
    return signByteArr;
  }
  
  public static boolean verify(byte[] hash, PublicKey pubKey, byte[] plainByteArr, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signCipher = Signature.getInstance(algorithm);
    signCipher.initVerify(pubKey);
    signCipher.update(plainByteArr);
    
    boolean signedVerify = signCipher.verify(hash);
    
    return signedVerify;
  }
}
