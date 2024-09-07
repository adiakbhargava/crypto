package com.aakb.crypto.controller;

import com.aakb.crypto.domain.AsymmKeyResponse;
import com.aakb.crypto.domain.KeyRequest;
import com.aakb.crypto.domain.KeyResponse;
import com.aakb.crypto.impl.AsymmetricKeys;
import com.aakb.crypto.impl.Symmetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
/**
 * Controller that generates symmetric and asymmetric keys
 *
 * @author Adi Bhargava
 */
class KeyController {
    Logger logger = LoggerFactory.getLogger(KeyController.class);

    /**
     * Generates a symmetric key based off of the content of the KeyRequest
     *
     * @return KeyResponse holding Base64 encoded String of symmetric key
     */
    @PostMapping("/symm-keys")
    KeyResponse newSymmKey(@RequestBody KeyRequest keyRequest) throws NoSuchAlgorithmException {
        logger.debug("KeyRequest- {}", keyRequest);
        // create symmetric key using size and key algorithm from keyRequest
        SecretKey symmetricKey = Symmetric.createSymmKey(keyRequest.getKeySize(), keyRequest.getAlgorithm());

        return KeyResponse.builder()
                .key(Base64.getEncoder().encodeToString(symmetricKey.getEncoded()))
                .build();
    }

    /**
     * Generates a private-public key pair based off of the content of the KeyRequest
     *
     * @return AsymmKeyResponse holding the Base64 encoded string of the private and public keys
     */
    @PostMapping("/asymm-keys")
    AsymmKeyResponse newAsymmKey(@RequestBody KeyRequest keyRequest) throws NoSuchAlgorithmException {
        // create key pair
        KeyPair keyPair = AsymmetricKeys.generateKeyPair(keyRequest.getKeySize(), keyRequest.getAlgorithm());

        return AsymmKeyResponse.builder()
                .privKey(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()))
                .pubKey(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()))
                .build();
    }
}