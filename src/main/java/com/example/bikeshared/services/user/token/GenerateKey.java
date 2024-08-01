package com.example.bikeshared.services.user.token;

import java.security.SecureRandom;

public class GenerateKey {
    // METODO PRA GERAR A KEY PARA O TOKEN
    public static String generateSecretKey(int keyLength) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[keyLength];
        random.nextBytes(bytes);

        StringBuilder key = new StringBuilder();
        for (byte b : bytes) {
            key.append(Character.forDigit((b & 0xff) / 16, 16));
            key.append(Character.forDigit(b & 0xff % 16, 16));
        }

        return key.toString();
    }

}
