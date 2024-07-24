package com.example.cabproject;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.security.Key;

public class GenerateSecretKey {
    public static void main(String[] args) {
        // Generate a new HS512 secret key
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
        // Encode the key to base64
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        // Print the base64-encoded key
        System.out.println("Base64 Secret Key: " + base64Key);
    }
}
