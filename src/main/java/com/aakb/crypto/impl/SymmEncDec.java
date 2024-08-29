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

public class SymmEncDec {
    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    /**
     * Creates and returns an initialization vector, a random assortment of bytes, in order to
     * randomize the first block in encryption which will therefore randomize the next block as we use
     * Cipher Block Chaining (CBC)
     * <p>
     * This helps in preventing unauthorized decryption of the ciphertext
     *
     * @return byte[] initializationVector
     */
    public static byte[] createInitializationVector() {
        byte[] initializationVector = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(initializationVector);

        return initializationVector;
    }

    /**
     * Encrypts plaintext and returns ciphertext based off of the secretkey and initialization vector using CBC block cipher
     */
    public static byte[] encrypt(String plaintext, SecretKey secretKey, String algorithm, byte[] iV) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iV);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        return cipher.doFinal(plaintext.getBytes());
    }

    /**
     * Decrypts ciphertext and returns original plaintext encrypted by secretkey and initialization
     * vector
     */
    public static String decrypt(String ciphertext, SecretKey secretKey, String algorithm, byte[] iV)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] decodedCipherBytes = decodeBase64(ciphertext);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iV));
        byte[] result = cipher.doFinal(decodedCipherBytes);

        return new String(result);
    }
}
