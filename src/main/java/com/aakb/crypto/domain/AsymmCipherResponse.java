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
 * Response for asymmetric hashing in cipher controller
 *
 * @author Adi Bhargava
 */
public class AsymmCipherResponse {
    private String cipherText;
}
