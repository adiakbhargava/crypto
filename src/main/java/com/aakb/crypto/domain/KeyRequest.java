package com.aakb.crypto.domain;

public class KeyRequest {
    private int keySize;
    private String algorithm;

    public int getKeySize() {
        return keySize;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
