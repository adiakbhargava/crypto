package com.aakb.crypto.impl;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

public class AsymmEncDec {
    /**
     * Encrypts plaintext and returns ciphertext based off of the secretkey and initialization vector using CBC block cipher
     */
    public static byte[] encrypt(String plaintext, PrivateKey privKey, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privKey);

        return cipher.doFinal(plaintext.getBytes());
    }

    /**
     * Decrypts ciphertext and returns original plaintext encrypted by secretkey and initialization
     * vector
     */
    public static String decrypt(String ciphertext, PublicKey pubKey, String algorithm)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] decodedCipherBytes = decodeBase64(ciphertext);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        byte[] result = cipher.doFinal(decodedCipherBytes);

        return new String(result);
    }
}
