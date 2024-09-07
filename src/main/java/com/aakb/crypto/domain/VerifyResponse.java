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
 * Response that returns a string "yes" or "no" based on the verification status for what
 * it is being used for.
 *
 * @author Adi Bhargava
 */
public class VerifyResponse {
    // "yes" or "no" if verified to be true or not
    private String verified;
}
