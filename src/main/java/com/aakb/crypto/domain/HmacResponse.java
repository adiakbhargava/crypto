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
 * Response for HMAC controller
 *
 * @author Adi Bhargava
 */
public class HmacResponse {
    private String hmac;
    private String key;
    private String plaintext;
}
