package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class VerifyRequest {
    private String plainText;
    private String cipherText;
    private String algoName;
    private String key64;
    private String keyAlgo;
}
