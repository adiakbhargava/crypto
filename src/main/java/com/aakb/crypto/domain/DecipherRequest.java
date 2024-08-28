package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class DecipherRequest {
    private String secretKeyAlgoName;
    private String secretKey;
    private String cipherAlgoName;
    private String cipherText;
    private String iV;
}
