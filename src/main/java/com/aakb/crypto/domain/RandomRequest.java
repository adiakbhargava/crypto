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
 * Request for random controller
 *
 * @author Adi Bhargava
 */
public class RandomRequest {
    private int size;
    private String algorithm;
}
