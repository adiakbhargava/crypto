package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
/**
 * Response for Asymm key controller
 *
 * @author Adi Bhargava
 */
public class AsymmKeyResponse {
    private String privKey;
    private String pubKey;
}
