
# Crypto

A set of REST APIs that implements the essential cryptographic primitives corresponding to the four core pillars of cryptography:
- Confidentiality: Ensure data is properly hidden from all except the intended recipients through encryption and decryption.
- Integrity: Verify that data has not been modified or deleted in an unauthorized or undetected manner.
- Authentication: Verifies the user's identity through digital signatures.
- Non-repudiation: Provide mechanisms to prevent denial of actions, ensuring accountability.
## APIs Implemented

| SR # | URI          | Description                                                              | Pillar          |
|------|--------------|--------------------------------------------------------------------------|-----------------|
| 1    | /symm-keys   | Generates a symmetric key for encrypting and decrypting data.            | Confidentiality |
| 2    | /symm-enc    | Encrypts plaintext using a symmetric key and a default IV.               | Confidentiality |
| 3    | /symm-dec    | Decrypts ciphertext using a symmetric key.                               | Confidentiality |
| 4    | /asymm-enc   | Encrypts plaintext using a private key.                                  | Confidentiality |
| 5    | /asymm-dec   | Decrypts ciphertext using a public key.                                  | Confidentiality |
| 6    | /asymm-keys  | Generates a private-public key pair for asymmetric encryption.           | Confidentiality |
| 7    | /rand        | Generates a Base64 random assortment using secure random.                | Confidentiality |
| 8    | /hash        | Generates a Base64 encoded message digest (hash) for a plaintext.        | Integrity       |
| 9    | /verify-hash | Verifies if the provided hash matches the given plaintext.               | Integrity       |
| 10   | /hmac        | Calculates a MAC tag for the plaintext using a specified key.            | Authentication  |
| 11   | /verify-hmac | Verifies if the provided MAC tag is valid for the given plaintext.       | Authentication  |
| 12   | /sign        | Creates a digital signature from plaintext using a private key.          | Non-repudiation |
| 13   | /verify-sign | Verifies the digital signature against the plaintext using a public key. | Non-repudiation |
| 14   | /algos       | Gets all algorithms that can be used for the REST APIs                   | -               |

## Usage
### Prerequisites
- JDK 17+
- Apache Maven 3.x
- Postman, curl, or any of your favorite http tool
```
git clone https://github.com/adiakbhargava/crypto.git
```
```
cd crypto
```
```
mvn clean install
```
```
mvn spring-boot:run
```

## TODOs
### Features
cf1- Add support to store keys

cf3- Benchmarking

cf4- Containerization support

cf5- Add more test cases
