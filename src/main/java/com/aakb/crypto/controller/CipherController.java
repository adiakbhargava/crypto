package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.AsymmEncDec;
import com.aakb.crypto.impl.SymmEncDec;
import com.aakb.crypto.impl.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RestController
/**
 * Controller that provides URIs for symmetric and asymmetric encryption and decryption
 *
 * @author Adi Bhargava
 */
class CipherController {
    /**
     * Encrypts plain text with symmetric key and other content contained in CipherRequest
     *
     * @return CipherResponse holding the Base64 encoded string of cipher text along with initialization vector
     */
    @PostMapping("/symm-enc")
    CipherResponse symmetricEncryption(@RequestBody CipherRequest cipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // hydrate symmetric key
        SecretKey hydratedKey = Utils.hydrateSecretKeyFromBase64(cipherRequest.getKey(), cipherRequest.getKeyAlgoName());
        // create a default initialization vector
        byte[] defaultIV = Utils.createDefaultIv();
        // encrypt plaintext with key, cipher algo, and defaultIV
        byte[] cipherBytes = SymmEncDec.encrypt(cipherRequest.getPlainText(), hydratedKey, cipherRequest.getCipherAlgoName(), defaultIV);

        return CipherResponse.builder()
                .cipherText(Base64.getEncoder().encodeToString(cipherBytes))
                .iV(Base64.getEncoder().encodeToString(defaultIV))
                .build();
    }

    /**
     * Decrypts cipher text using symmetric key and other content in DecipherRequest
     *
     * @return DecipherResponse holding the deciphered text
     */
    @PostMapping("/symm-dec")
    DecipherResponse symmetricDecryption(@RequestBody DecipherRequest decipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // hydrate symmetric key
        SecretKey hydratedKey = Utils.hydrateSecretKeyFromBase64(decipherRequest.getKey(), decipherRequest.getKeyAlgoName());
        // decode initialization vector
        byte[] decodedIV = Base64.getDecoder().decode(decipherRequest.getIV());
        // decrypt cipher text
        String decipherStr = SymmEncDec.decrypt(decipherRequest.getCipherText(), hydratedKey, decipherRequest.getCipherAlgoName(), decodedIV);

        return DecipherResponse.builder()
                .decipherText(decipherStr)
                .build();
    }

    /**
     * Encrypts plain text with private key and other content contained in CipherRequest
     *
     * @return AsymmCipherResponse holding string representation of cipher text
     */
    @PostMapping("/asymm-enc")
    AsymmCipherResponse asymmetricEncryption(@RequestBody CipherRequest cipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        // hydrate private key
        PrivateKey privKey = Utils.hydratePrivKey(cipherRequest.getKey(), cipherRequest.getKeyAlgoName());
        // encrypt
        byte[] cipherBytes = AsymmEncDec.encrypt(cipherRequest.getPlainText(), privKey, cipherRequest.getCipherAlgoName());

        return AsymmCipherResponse.builder()
                .cipherText(Base64.getEncoder().encodeToString(cipherBytes))
                .build();
    }

    /**
     * Decrypts cipher text using public key and other content in the DecipherRequest
     *
     * @return DecipherRespone holding string representation of deciphered text
     */
    @PostMapping("/asymm-dec")
    DecipherResponse asymmetricDecryption(@RequestBody DecipherRequest decipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        // hydrate public key
        PublicKey pubKey = Utils.hydratePubKey(decipherRequest.getKey(), decipherRequest.getKeyAlgoName());
        // decrypt
        String decipheredStr = AsymmEncDec.decrypt(decipherRequest.getCipherText(), pubKey, decipherRequest.getCipherAlgoName());

        return DecipherResponse.builder()
                .decipherText(decipheredStr)
                .build();
    }
}