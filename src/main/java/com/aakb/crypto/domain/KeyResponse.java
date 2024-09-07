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
 * Response to key controller
 *
 * @author Adi Bhargava
 */
public class KeyResponse {
    private String key;
}
