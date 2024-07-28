package com.example.cabproject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {

    @Test
    public void generateSecretKey() {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // Encode the key in hexadecimal format for easy reading
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());

        // Print the key
        System.out.printf("\nKey = [%s]\n", encodedKey);
    }
}
