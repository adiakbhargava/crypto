package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
/**
 * Response for sign-verify controller
 *
 * @author Adi Bhargava
 */
public class AsymmSignResponse {
    private String signature;
    private String pubKey;
}
