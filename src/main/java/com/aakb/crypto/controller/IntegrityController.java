package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.Hashing;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
class IntegrityController {
    IntegrityController() {
    }

    @PostMapping("/hash")
    IntegrityResponse hash(@RequestBody IntegrityRequest integrityRequest) throws IOException, NoSuchAlgorithmException {
        String plaintext = integrityRequest.getPlainText();
        String algoName = integrityRequest.getHashAlgoName();
        // byte[] defaultSalt = Utils.createRandomSaltValue();

        byte[] hashedText = Hashing.hashText(plaintext, algoName);

        return IntegrityResponse.builder()
                .hashedText(Base64.getEncoder().encodeToString(hashedText))
                .build();
    }

    @PostMapping("/verify-hash")
    VerifyResponse verify(@RequestBody VerifyRequest verifyRequest) throws IOException, NoSuchAlgorithmException {
        String plaintext = verifyRequest.getPlainText();
        String hashedtext = verifyRequest.getCipherText();
        String algoName = verifyRequest.getAlgoName();

        String hashedPlainText = Base64.getEncoder().encodeToString(Hashing.hashText(plaintext, algoName));
        boolean verified = hashedtext.equals(hashedPlainText);

        return VerifyResponse.builder()
                .verified(verified ? "yes" : "no")
                .build();
    }
}