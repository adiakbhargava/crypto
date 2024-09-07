package com.aakb.crypto.impl;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

/**
 * Implementation for the asymmetric encryption-decryption of a plain text
 *
 * @author Adi Bhargava
 */
public class AsymmEncDec {
    /**
     * Performs asymmetric encryption of a plain text given a private key and algorithm
     *
     * @return byte[] of cipher text
     */
    public static byte[] encrypt(String plaintext, PrivateKey privKey, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privKey);

        return cipher.doFinal(plaintext.getBytes());
    }

    /**
     * Decrypts cipher text and returns plain text given the public key
     *
     * @return String of plain text
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
