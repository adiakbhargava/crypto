package com.aakb.crypto.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Provides content for a typical error response based off of exception thrown during runtime
 *
 * @author Adi Bhargava
 */
public class ErrorResponse {
    private int statusCode;
    private String message;

    /**
     * Constructor that creates ErrorResponse using inputted statusCode and message
     */
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}