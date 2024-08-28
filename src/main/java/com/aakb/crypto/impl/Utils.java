package com.aakb.crypto.impl;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Utils {
    public static PrivateKey createPrivKey(String base64Str, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBytes = Base64.decodeBase64(base64Str);
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        return keyFactory.generatePrivate(privKeySpec);
    }

    public static PublicKey createPubKey(String base64Str, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedBytes = Base64.decodeBase64(base64Str);
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
        byte[] salt = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(salt);

        return salt;
    }

    public static IvParameterSpec createDefaultedIvParameterSpecr() {
        return new IvParameterSpec(createDefaultIv());
    }

    public static byte[] createDefaultIv() {
        byte[] initializationVector = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(initializationVector);

        return initializationVector;
    }

    public static String convertSecretKeyToBits(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(' ', '0'));
        }

        return sb.toString();
    }

    public static String convertByteToHexString(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            int decimal = (int) b & 0xff;
            String hex = Integer.toHexString(decimal);

            if (hex.length() % 2 == 1) {
                hex = "0" + hex;
            }
            sb.append(hex);
        }

        return sb.toString();
    }

    public static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] =
                    (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static SecretKey hydrateKeyFromBase64(String base64Str, String keyAlgoName) {
        byte[] decodedBase64 = Base64.decodeBase64(base64Str);
        SecretKey originalKey = new SecretKeySpec(decodedBase64, 0, decodedBase64.length, keyAlgoName);
        return originalKey;
    }

    public static void getAllAlgos(String cryptoPrimitive) {
        for (Provider provider : Security.getProviders()) {
            for (Provider.Service service : provider.getServices()) {
                if (service.getType().equals(cryptoPrimitive)) {
                    System.out.println(provider);
                    System.out.println(
                            "===============================================================================");
                    System.out.println(service.getType() + "--------------" + service);
                }
            }
        }
    }
}
