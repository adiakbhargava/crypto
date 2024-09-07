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
/**
 * Controller to create and verify digital signatures
 *
 * @author Adi Bhargava
 */
class SignVerifyController {
    /**
     * Creates a digital signature based off of the content in the AsymmSignRequest
     *
     * @return AsymmSignResponse holding the Base64 encoded string of the signature and public key used for verification
     */
    @PostMapping("/sign")
    AsymmSignResponse asymmetricSigning(@Valid @RequestBody AsymmSignRequest asymmSignRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        // hydrate private key
        PrivateKey privKey = Utils.hydratePrivKey(asymmSignRequest.getPrivKey(), asymmSignRequest.getKeyAlgo());
        // create signature
        byte[] signature = SignVerify.sign(asymmSignRequest.getPlaintext().getBytes(), privKey, asymmSignRequest.getSignAlgo());

        return AsymmSignResponse.builder()
                .signature(Base64.getEncoder().encodeToString(signature))
                .pubKey(asymmSignRequest.getPubKey())
                .build();
    }

    /**
     * Verifies the passed in signature with that of the contents in the AsymmSignRequest
     *
     * @return VerifyResponse that provides confirmation if the signature is that of the original plain text
     */
    @PostMapping("/verify-sign")
    VerifyResponse symmetricDecryption(@RequestBody VerifyRequest verifyRequest) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // hydrate public key
        PublicKey pubKey = Utils.hydratePubKey(verifyRequest.getKey64(), verifyRequest.getKeyAlgo());
        // break up plain text into byte[]
        byte[] plainTextBytes = verifyRequest.getPlainText().getBytes();
        // decode Base64 string of signature itno byte[]
        byte[] signatureBytes = Base64.getDecoder().decode(verifyRequest.getCipherText().getBytes());

        return VerifyResponse.builder()
                .verified(SignVerify.verify(signatureBytes, pubKey, plainTextBytes, verifyRequest.getAlgoName()) ? "yes" : "no")
                .build();
    }
}