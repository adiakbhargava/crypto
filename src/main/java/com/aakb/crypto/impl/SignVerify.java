package com.aakb.crypto.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class SignVerify {
  public static byte[] sign(byte[] plainByteArr, PrivateKey privKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signCipher = Signature.getInstance(algorithm);
    signCipher.initSign(privKey);
    signCipher.update(plainByteArr);
    
    return signCipher.sign();

  }
  
  public static boolean verify(byte[] hash, PublicKey pubKey, byte[] plainByteArr, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signCipher = Signature.getInstance(algorithm);
    signCipher.initVerify(pubKey);
    signCipher.update(plainByteArr);
    
    return signCipher.verify(hash);

  }
}
