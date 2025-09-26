package com.senac.games.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "euecascadebala2321";
    private static final String ISSUER = "gamesolimpiadas";

    public String generateToken(UsuarioDetailsImpl usuario){
        try {
            Algorithm algorithm =Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER).
                    withIssuedAt(creationDate())
                    .withExpiresAt((expirationDate()))
                    .withSubject(usuario.getUsername())
                    .sign(algorithm);

        }catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar o token", exception);
        }
    }
    private Instant creationDate(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }
}
