package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import com.aakb.crypto.impl.Asymmetric;
import com.aakb.crypto.impl.SymmEncDec;
import com.aakb.crypto.impl.Symmetric;
import com.aakb.crypto.impl.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
class CipherController {
    CipherController() {
    }

    @PostMapping("/symm-enc")
    CipherResponse symmetricEncryption(@RequestBody CipherRequest cipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey hydratedKey = Utils.hydrateKeyFromBase64(cipherRequest.getSecretKey(), cipherRequest.getSecretKeyAlgoName());
        byte[] defaultIV = Utils.createDefaultIv();
        byte[] cipherBytes = SymmEncDec.encrypt(cipherRequest.getPlainText(), hydratedKey, cipherRequest.getCipherAlgoName(), defaultIV);
        return CipherResponse.builder()
                .cipherText(Base64.getEncoder().encodeToString(cipherBytes))
                .iV(Base64.getEncoder().encodeToString(defaultIV))
                .build();
    }

    @PostMapping("/symm-dec")
    DecipherResponse symmetricDecryption(@RequestBody DecipherRequest decipherRequest) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey hydratedKey = Utils.hydrateKeyFromBase64(decipherRequest.getSecretKey(), decipherRequest.getSecretKeyAlgoName());
        byte[] decodedIV = Base64.getDecoder().decode(decipherRequest.getIV());
        String decipherStr = SymmEncDec.decrypt(decipherRequest.getCipherText(), hydratedKey, decipherRequest.getCipherAlgoName(), decodedIV);
        return DecipherResponse.builder()
                .decipherText(decipherStr)
                .build();
    }
}