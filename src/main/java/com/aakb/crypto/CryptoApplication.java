package com.aakb.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runs the full application that can now be connected to by a http tool
 */
@SpringBootApplication
public class CryptoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CryptoApplication.class, args);
	}
}
