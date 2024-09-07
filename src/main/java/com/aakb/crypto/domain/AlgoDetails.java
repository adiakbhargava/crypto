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
 * Provides all details for an algorithm that can be displayed by algos URI
 *
 * @author Adi Bhargava
 */
public class AlgoDetails {
    private String algorithm;
    private String className;
    private String provider;
    private String service;
}
