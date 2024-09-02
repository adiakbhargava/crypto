package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AlgoDetails {
    private String algorithm;
    private String className;
    private String provider;
    private String service;
}
