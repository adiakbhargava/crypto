package com.aakb.crypto.impl;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

/**
 * Provides operations used among the REST APIs.
 *
 * @author Adi Bhargava
 */
public class Utils {
    /**
     * Creates private key using the base 64 string representation of the key is fed into the input.
     *
     * @return PrivateKey of String
     */
    public static PrivateKey hydratePrivKey(String base64Str, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBytes = decodeBase64(base64Str);
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        return keyFactory.generatePrivate(privKeySpec);
    }

    /**
     * Creates public key using the base 64 string representation of the key is fed into the input.
     *
     * @return PublicKey of String
     */
    public static PublicKey hydratePubKey(String base64Str, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBytes = decodeBase64(base64Str);
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        return keyFactory.generatePublic(pubKeySpec);
    }

    /**
     * Creates random salt value, random data fed into the input of a hash function.
     *
     * @return byte[] of salt value to be used as an input
     */
    public static byte[] createRandomSaltValue() {
        // salt value is 16 bytes
        byte[] salt = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        // provides random values for the salt[]
        securerandom.nextBytes(salt);

        return salt;
    }

    /**
     * Creates an initialization vector, random data is fed into the input of a symmetric encryption/decryption
     * function.
     *
     * @return byte[] of initialization vector to be used as an input
     */
    public static byte[] createDefaultIv() {
        byte[] initializationVector = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(initializationVector);

        return initializationVector;
    }

    /**
     * Creates a symmetric secret key from a base 64 string and given key algorithm
     *
     * @return SecretKey from String
     */
    public static SecretKey hydrateSecretKeyFromBase64(String base64Str, String keyAlgoName) {
        byte[] decodedBase64 = decodeBase64(base64Str);
        return new SecretKeySpec(decodedBase64, 0, decodedBase64.length, keyAlgoName);
    }
}
