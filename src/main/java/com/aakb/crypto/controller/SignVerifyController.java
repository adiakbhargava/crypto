package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.SignVerify;
import com.aakb.crypto.impl.Utils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RestController
class SignVerifyController {
    @PostMapping("/sign")
    AsymmSignResponse asymmetricSigning(@Valid @RequestBody AsymmSignRequest asymmSignRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        PrivateKey privKey = Utils.hydratePrivKey(asymmSignRequest.getPrivKey(), asymmSignRequest.getKeyAlgo());
        byte[] signature = SignVerify.sign(asymmSignRequest.getPlaintext().getBytes(), privKey, asymmSignRequest.getSignAlgo());

        return AsymmSignResponse.builder()
                .signature(Base64.getEncoder().encodeToString(signature))
                .pubKey(asymmSignRequest.getPubKey())
                .build();
    }

    @PostMapping("/verify-sign")
    VerifyResponse symmetricDecryption(@RequestBody VerifyRequest verifyRequest) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        PublicKey pubKey =  Utils.hydratePubKey(verifyRequest.getKey64(), verifyRequest.getKeyAlgo());
        byte[] plainTextBytes = verifyRequest.getPlainText().getBytes();
        byte[] signatureBytes = Base64.getDecoder().decode(verifyRequest.getCipherText().getBytes());
        
        return VerifyResponse.builder()
                .verified(SignVerify.verify(signatureBytes, pubKey, plainTextBytes, verifyRequest.getAlgoName()) ? "yes" : "no")
                .build();
    }
}