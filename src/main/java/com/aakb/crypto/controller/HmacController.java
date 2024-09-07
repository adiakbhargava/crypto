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
/**
 * Controller that creates and verifies hash-based message authentication code
 *
 * @author Adi Bhargava
 */
class HmacController {
    /**
     * creates a hmac based off of the contents of HmacRequest
     *
     * @return HmacResponse holding the Base64 string encodings for the hmac, symm key, and plain text
     */
    @PostMapping("/hmac")
    HmacResponse calculateHmac(@RequestBody HmacRequest hmacRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        // get plaintext and algo
        String plaintext = hmacRequest.getPlainText();
        String algoName = hmacRequest.getAlgoName();
        // hydrate symmetric key from hmacRequest
        SecretKey key = Utils.hydrateSecretKeyFromBase64(hmacRequest.getKey(), hmacRequest.getKeyAlgoName());
        // calculate hmac
        String hmacStr64 = CalculateHMAC.findHMAC(plaintext, algoName, key);

        return HmacResponse.builder()
                .hmac(hmacStr64)
                .plaintext(plaintext)
                .key(hmacRequest.getKey())
                .build();
    }

    /**
     * Verifies given hmac with that of the plain text and other contents in the VerifyRequest
     *
     * @return VerifyResponse holding the confirmation if hmac is authentic to that of the original plain text
     */
    @PostMapping("/verify-hmac")
    VerifyResponse verifyHmac(@RequestBody VerifyRequest verifyRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        // get plaintext, algoName, and hmac
        String plaintext = verifyRequest.getPlainText();
        String algoName = verifyRequest.getAlgoName();
        String hmac = verifyRequest.getCipherText();
        // hydrate symmetric key
        SecretKey key = Utils.hydrateSecretKeyFromBase64(verifyRequest.getKey64(), verifyRequest.getKeyAlgo());
        // calculate hmac for plain text to compare with passed-in hmac
        String hmacOfPlaintext = CalculateHMAC.findHMAC(plaintext, algoName, key);
        // check if HMACs match
        boolean hmacVerified = hmacOfPlaintext.equals(hmac);

        return VerifyResponse.builder()
                .verified(hmacVerified ? "yes" : "no")
                .build();
    }
}