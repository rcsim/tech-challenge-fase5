package com.postech30.msusermanager.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.postech30.msusermanager.security.userdetail.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIiLCJpYXQiOm51bGwsImV4cCI6bnVsbCwiYXVkIjoiIiwic3ViIjoiIiwiRW1haWwiOiJtc3VzZXJAZW1haWwuY29tIn0.pYagxggdd699Om82TXhBk3LT9g1-O_V92bsfarBz0-8";
    private static final String ISSUER = "ms-user";
    public String generateToken(UserDetailsImpl userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(userDetails.getUsername())
                    .sign(algorithm);

        }catch (JWTCreationException exception){
            throw new JWTCreationException("Não foi possível gerar o token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return  JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token não é válido ou está expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }
}
