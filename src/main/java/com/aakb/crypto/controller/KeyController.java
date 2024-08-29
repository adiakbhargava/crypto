package com.aakb.crypto.controller;

import com.aakb.crypto.domain.AsymmKeyResponse;
import com.aakb.crypto.domain.KeyRequest;
import com.aakb.crypto.domain.KeyResponse;
import com.aakb.crypto.impl.Asymmetric;
import com.aakb.crypto.impl.Symmetric;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
class KeyController {
    KeyController() {
    }

    @PostMapping("/symm-keys")
    KeyResponse newSymmKey(@RequestBody KeyRequest keyRequest) throws NoSuchAlgorithmException {
        SecretKey symmetricKey = Symmetric.createAESKey(keyRequest.getKeySize(), keyRequest.getAlgorithm());
        KeyResponse keyResponse = new KeyResponse();
        keyResponse.setKey(Base64.getEncoder().encodeToString(symmetricKey.getEncoded()));

        return keyResponse;
    }

    @PostMapping("/asymm-keys")
    AsymmKeyResponse newAsymmKey(@RequestBody KeyRequest keyRequest) throws NoSuchAlgorithmException {
        KeyPair keyPair = Asymmetric.generateKeyPair(keyRequest.getKeySize(), keyRequest.getAlgorithm());

        System.out.println("Public key : " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println("Private key : " + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));

        return AsymmKeyResponse.builder()
                .privKey(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()))
                .pubKey(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()))
                .build();
    }
}