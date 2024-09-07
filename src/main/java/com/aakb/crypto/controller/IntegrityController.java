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
/**
 * Controller that contains URIs for hashing and hash-verification
 *
 * @author Adi Bhargava
 */
class IntegrityController {
    /**
     * Hashes plain text based off of the content in the Integrity Request
     *
     * @return IntegrityResponse holding the Base64 encoded string hashed text
     */
    @PostMapping("/hash")
    IntegrityResponse hash(@RequestBody IntegrityRequest integrityRequest) throws IOException, NoSuchAlgorithmException {
        // get plain text and hash-algo
        String plaintext = integrityRequest.getPlainText();
        String algoName = integrityRequest.getHashAlgoName();
        // get byte[] of hashed text
        byte[] hashedText = Hashing.hashText(plaintext, algoName);

        return IntegrityResponse.builder()
                .hashedText(Base64.getEncoder().encodeToString(hashedText))
                .build();
    }

    /**
     * Verifies the hashed text is related to that of the plain text using the contents of the VerifyRequest
     *
     * @return VerifyResponse holding confirmation if hashed text is related to the plain text
     */
    @PostMapping("/verify-hash")
    VerifyResponse verify(@RequestBody VerifyRequest verifyRequest) throws IOException, NoSuchAlgorithmException {
        // get plaintext, hashedtext, and name of hashing algorithm
        String plaintext = verifyRequest.getPlainText();
        String hashedtext = verifyRequest.getCipherText();
        String algoName = verifyRequest.getAlgoName();
        // hash plaintext using algorithm
        String hashedPlainText = Base64.getEncoder().encodeToString(Hashing.hashText(plaintext, algoName));
        // compare whether hashed plain text matches that of the inputted hash
        boolean verified = hashedtext.equals(hashedPlainText);

        return VerifyResponse.builder()
                .verified(verified ? "yes" : "no")
                .build();
    }
}