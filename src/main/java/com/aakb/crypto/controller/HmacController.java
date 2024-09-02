package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.CalculateHMAC;
import com.aakb.crypto.impl.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
class HmacController {
    @PostMapping("/hmac")
    HmacResponse hash(@RequestBody HmacRequest hmacRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String plaintext = hmacRequest.getPlainText();
        String algoName = hmacRequest.getAlgoName();
        SecretKey key = Utils.hydrateKeyFromBase64(hmacRequest.getKey(), hmacRequest.getKeyAlgoName());
        String hmacStr64 = CalculateHMAC.findHMAC(plaintext, algoName, key);

        return HmacResponse.builder()
                .hmac(hmacStr64)
                .plaintext(plaintext)
                .key(hmacRequest.getKey())
                .build();
    }

    @PostMapping("/verify-hmac")
    VerifyResponse hash(@RequestBody VerifyRequest verifyRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String plaintext = verifyRequest.getPlainText();
        String algoName = verifyRequest.getAlgoName();
        String hmac = verifyRequest.getCipherText();
        SecretKey key = Utils.hydrateKeyFromBase64(verifyRequest.getKey64(), verifyRequest.getKeyAlgo());
        String hmacOfPlaintext = CalculateHMAC.findHMAC(plaintext, algoName, key);
        boolean hmacVerified = hmacOfPlaintext.equals(hmac);

        return VerifyResponse.builder()
                .verified(hmacVerified ? "yes" : "no")
                .build();
    }
}