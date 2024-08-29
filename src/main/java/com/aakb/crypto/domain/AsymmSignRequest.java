package com.aakb.crypto.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AsymmSignRequest {
    @NotBlank(message = "plaintext is required")
    private String plaintext;
    @NotBlank(message = "privKey is required")
    private String privKey;
    @NotBlank(message = "pubKey is required")
    private String pubKey;
    @NotBlank(message = "signAlgo is required")
    private String signAlgo;
    @NotBlank(message = "keyAlgo is required")
    private String keyAlgo;
}
