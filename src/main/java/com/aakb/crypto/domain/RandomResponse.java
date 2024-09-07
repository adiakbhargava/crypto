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
 * Response for random controller
 *
 * @author Adi Bhargava
 */
public class RandomResponse {
    // Base64 encoded string
    private String randomBase64;
}
