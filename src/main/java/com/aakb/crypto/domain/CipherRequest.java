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
 * Request for cipher controller
 *
 * @author Adi Bhargava
 */
public class CipherRequest {
    private String keyAlgoName;
    private String key;
    private String cipherAlgoName;
    private String plainText;
}
