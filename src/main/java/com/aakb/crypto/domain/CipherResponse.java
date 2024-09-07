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
 * Response for cipher controller
 *
 * @author Adi Bhargava
 */
public class CipherResponse {
    private String cipherText;
    private String iV;
}
