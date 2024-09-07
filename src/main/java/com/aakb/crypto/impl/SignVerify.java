package com.aakb.crypto.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Implementation for creating and verifying digital signatures
 *
 * @author Adi Bhargava
 */
public class SignVerify {
    /**
     * Creates a digital signature based off of the bytes of a plain text and private key along with signature algorithm
     *
     * @return byte[] of digital signature
     */
    public static byte[] sign(byte[] plainByteArr, PrivateKey privKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signCipher = Signature.getInstance(algorithm);
        signCipher.initSign(privKey);
        signCipher.update(plainByteArr);

        return signCipher.sign();

    }

    /**
     * Takes a byte[] of the signature hash and verifies if the signature is that of the original plain text
     *
     * @return boolean if signature is verified
     */
    public static boolean verify(byte[] hash, PublicKey pubKey, byte[] plainByteArr, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signCipher = Signature.getInstance(algorithm);
        signCipher.initVerify(pubKey);
        signCipher.update(plainByteArr);

        return signCipher.verify(hash);

    }
}
