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
 * Request for HMAC controller
 *
 * @author Adi Bhargava
 */
public class HmacRequest {
    private String plainText;
    private String key;
    private String algoName;
    private String keyAlgoName;
}
