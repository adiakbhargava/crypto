package com.aakb.crypto.impl;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static org.apache.tomcat.util.codec.binary.Base64.*;

/**
 * Symmetric encryption and decryption
 *
 * @author Adi Bhargava
 */
public class SymmEncDec {
    /**
     * Creates a cipher text based on the plain text, secret symm key, algorithm, and an initialization vector
     *
     * @return byte[] representation of the cipher text
     */
    public static byte[] encrypt(String plaintext, SecretKey secretKey, String algorithm, byte[] iV) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iV);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        return cipher.doFinal(plaintext.getBytes());
    }

    /**
     * Decrypts the cipher text for the plain text based off of the secret key and initialization vector
     * used for encryption.
     *
     * @return String of original plain text (decrypted cipher text)
     */
    public static String decrypt(String ciphertext, SecretKey secretKey, String algorithm, byte[] iV)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        // decode Base64 cipher text to cipher bytes
        byte[] decodedCipherBytes = decodeBase64(ciphertext);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iV));
        byte[] result = cipher.doFinal(decodedCipherBytes);

        return new String(result);
    }
}
