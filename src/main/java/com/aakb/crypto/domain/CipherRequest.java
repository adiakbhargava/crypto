package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CipherRequest {
    private String keyAlgoName;
    private String key;
    private String cipherAlgoName;
    private String plainText;
}
