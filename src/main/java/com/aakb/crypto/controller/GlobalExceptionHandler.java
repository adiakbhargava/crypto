package com.aakb.crypto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
/**
 * Handles all exceptions that may occur while application is in use
 *
 * @author Adi Bhargava
 */
public class GlobalExceptionHandler {

    /**
     * Handles common exceptions that occur while running cryptographic operations
     *
     * @return error response for specified exceptions
     */
    @ExceptionHandler({InvalidKeySpecException.class, NoSuchAlgorithmException.class, SignatureException.class, InvalidKeyException.class,
            IOException.class, InvalidAlgorithmParameterException.class, NoSuchPaddingException.class, IllegalBlockSizeException.class,
            BadPaddingException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleCommonCryptoExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles exceptions that have to do with user input
     *
     * @return error response for specified exceptions
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInputValidationException(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        // add error messages to details list
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        // append error messages to a StringBuilder
        StringBuilder errorMsg = new StringBuilder();
        for(int i = 0; i < details.size(); i++){
            errorMsg.append(details.get(i));
            if(i != details.size()-1){
                errorMsg.append(", ");
            }
        }

        // create error response using custom error message
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMsg.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}