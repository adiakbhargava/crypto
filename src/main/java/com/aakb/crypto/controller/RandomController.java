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
class RandomController {
    @PostMapping("/rand")
    RandomResponse getSecureRandom(@RequestBody RandomRequest randomRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecureRandom sr;
        if(randomRequest.getAlgorithm().isEmpty()){
            sr = new SecureRandom();
        } else{
            sr = SecureRandom.getInstance(randomRequest.getAlgorithm());
        }
        byte[] randBytes = new byte[randomRequest.getSize()];
        sr.nextBytes(randBytes);

        return RandomResponse.builder()
                .randomBase64(Base64.getEncoder().encodeToString(randBytes))
                .build();
    }

}