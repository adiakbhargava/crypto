package com.aakb.crypto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
public class AsymmKeyResponse {
    @Getter @Setter private String privKey;
    @Getter @Setter private String pubKey;
}
