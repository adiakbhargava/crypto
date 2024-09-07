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
 * Response for deciphering in cipher controller
 *
 * @author Adi Bhargava
 */
public class DecipherResponse {
    private String decipherText;
}
