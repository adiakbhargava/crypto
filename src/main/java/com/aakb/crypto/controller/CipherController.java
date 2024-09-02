package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.AsymmEncDec;
import com.aakb.crypto.impl.SymmEncDec;
import com.aakb.crypto.impl.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RestController
class CipherController {
    @PostMapping("/symm-enc")
    CipherResponse symmetricEncryption(@RequestBody CipherRequest cipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey hydratedKey = Utils.hydrateKeyFromBase64(cipherRequest.getKey(), cipherRequest.getKeyAlgoName());
        byte[] defaultIV = Utils.createDefaultIv();
        byte[] cipherBytes = SymmEncDec.encrypt(cipherRequest.getPlainText(), hydratedKey, cipherRequest.getCipherAlgoName(), defaultIV);
        return CipherResponse.builder()
                .cipherText(Base64.getEncoder().encodeToString(cipherBytes))
                .iV(Base64.getEncoder().encodeToString(defaultIV))
                .build();
    }

    @PostMapping("/symm-dec")
    DecipherResponse symmetricDecryption(@RequestBody DecipherRequest decipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey hydratedKey = Utils.hydrateKeyFromBase64(decipherRequest.getKey(), decipherRequest.getKeyAlgoName());
        byte[] decodedIV = Base64.getDecoder().decode(decipherRequest.getIV());
        String decipherStr = SymmEncDec.decrypt(decipherRequest.getCipherText(), hydratedKey, decipherRequest.getCipherAlgoName(), decodedIV);
        return DecipherResponse.builder()
                .decipherText(decipherStr)
                .build();
    }

    @PostMapping("/asymm-enc")
    AsymmCipherResponse asymmetricEncryption(@RequestBody CipherRequest cipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        PrivateKey privKey = Utils.hydratePrivKey(cipherRequest.getKey(), cipherRequest.getKeyAlgoName());
        byte[] cipherBytes = AsymmEncDec.encrypt(cipherRequest.getPlainText(), privKey, cipherRequest.getCipherAlgoName());

        return AsymmCipherResponse.builder()
                .cipherText(Base64.getEncoder().encodeToString(cipherBytes))
                .build();
    }

    @PostMapping("/asymm-dec")
    DecipherResponse asymmetricDecryption(@RequestBody DecipherRequest decipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        PublicKey pubKey = Utils.hydratePubKey(decipherRequest.getKey(), decipherRequest.getKeyAlgoName());
        String decipheredStr = AsymmEncDec.decrypt(decipherRequest.getCipherText(), pubKey, decipherRequest.getCipherAlgoName());

        return DecipherResponse.builder()
                .decipherText(decipheredStr)
                .build();
    }
}