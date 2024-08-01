package com.example.bikeshared.services.user.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class TokenService {

    /*
     * Chave para encriptar o token
     */
    private static final String SECRET_KEY = "ef2ab415d79937d98efdda7c5b0199d32d8414c19f87579d500746da2d6c24a1810e2dd49a40cac172ece70c040e70ee660faf02b0c77ac0a4309b0af01e00012e32dade2ecd714694e27e749c20e4c0f60d991956798a37e27078a75e77574bf06c4bdea6e9318b23f06f2267217323b797f44e18967178de1aeae7fa9e338250d2a704e53f4d9ea5aa70dae7428e767a9a6f144782ee9811ea7de25c4f711183c284123f6a11c82747399229759c967fddd2726b88e6dcfd54b2f47bcbce7b59a62b91204d98fe39d6ef9439c7aa40ed6abbaa837e3c639941edec1e1d81bc9916fcb1ab33989d01cd8f19c601204251149aa63bc6b64139342a5ba2ddf41b";

    /*
     * Determinando o algoritmo para encriptar os dados, usando a chave
     */
    protected  static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    // private static final long EXPIRATION_TIME = 86400000; // 1 dia


    // FUNÇÃO PARA CALCULAR O TEMPO DE EXPIRAÇÃO
    private Instant generateExpirationTime (Integer tipo) {
        Integer hour = (tipo == 1)? 2 : 24;
        return LocalDateTime.now().plusHours(hour).toInstant(ZoneOffset.of("+1"));
    }

    // MÉTODO QUE GERA O TOKEN
    public String generateToken(String email, Integer tipo) {
        try {
            return JWT.create()
            .withIssuer("server")
            .withSubject(email)
            .withExpiresAt( generateExpirationTime(tipo) )
            .sign(algorithm);

        } catch ( JWTCreationException exc ) {
            System.out.println("EXCEPTION: "+ exc.getMessage());
            throw new RuntimeException(" Erro ao gerar Token: " + exc);
        }
    }

    // PEGAR DADOS DO TOKEN
    public String getData (String token) {
        try {
            return JWT.require(algorithm)
                    .withIssuer("server")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch ( JWTVerificationException exc ) {
            return " ";
        }
    }

    // VERIFICA O TEMPO DE EXPIRAÇÃO DO TOKEN
    public Date getExpirationTime (String token) {
        try {
            return JWT.require(algorithm)
                    .withIssuer("server")
                    .build()
                    .verify(token)
                    .getExpiresAt();
        } catch ( JWTVerificationException exc ) {
            return null;
        }
    }

    //VERIFICA SE O TOKEN EXPIROU
    public boolean isTokenExpired(String token) {
        System.out.println("Your Token: "+token+"\n, And Your Token Key: "+SECRET_KEY);
        return getExpirationTime(token).before(new Date());
    }

    // VERIFICA SE O TOKEN AINDA É VÁLIDO
    public boolean validateToken(String token, String email) {
        String tokenEmail = getData(token);
        return (email.equals(tokenEmail) && !isTokenExpired(token));
    }


}

