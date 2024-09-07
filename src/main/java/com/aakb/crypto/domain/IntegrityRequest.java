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
 * Request for integrity controller
 *
 * @author Adi Bhargava
 */
public class IntegrityRequest {
    private String plainText;
    private String hashAlgoName;
}
