package com.aakb.crypto.controller;

import com.aakb.crypto.domain.AlgoDetails;
import com.aakb.crypto.domain.AlgoResponse;
import com.aakb.crypto.domain.RandomRequest;
import com.aakb.crypto.domain.RandomResponse;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.*;

@RestController
/**
 * Controller to create a secure random
 *
 * @author Adi Bhargava
 */
class RandomController {
    /**
     * Creates a secure random based off of the content in the RandomRequest
     *
     * @return RandomResponse holding the Base64 encoded string of the secure random
     */
    @PostMapping("/rand")
    RandomResponse getSecureRandom(@RequestBody RandomRequest randomRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // declare SecureRandom object
        SecureRandom sr;
        // if algorithm is not specified in randomRequest, SecureRandom object will be initialized with default constructor
        if (randomRequest.getAlgorithm().isEmpty()) {
            sr = new SecureRandom();
        } else {
            // initialize object with user-specified algorithm
            sr = SecureRandom.getInstance(randomRequest.getAlgorithm());
        }
        // create byte[] off the SecureRandom object
        byte[] randBytes = new byte[randomRequest.getSize()];
        sr.nextBytes(randBytes);

        return RandomResponse.builder()
                .randomBase64(Base64.getEncoder().encodeToString(randBytes))
                .build();
    }
}