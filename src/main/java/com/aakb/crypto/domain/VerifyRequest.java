package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
/**
 * Request for verification
 *
 * @author Adi Bhargava
 */
public class VerifyRequest {
    private String plainText;
    private String cipherText;
    private String algoName;
    // Base64 encoded String of key
    private String key64;
    private String keyAlgo;
}
