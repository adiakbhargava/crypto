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
 * Request for key controller
 *
 * @author Adi Bhargava
 */
public class KeyRequest {
    private int keySize;
    private String algorithm;
}
