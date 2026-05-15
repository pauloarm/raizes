package com.example.raizes_do_nordeste.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.raizes_do_nordeste.model.Usuario;



@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            //criptografia simétrica com chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("raizes-do-nordest-api") //emissor do token
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao()) //quando vence o token
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar TOKEN JWT", exception);
        }

    }

    //gera o vencimento do token em 2 horas
    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                        .withIssuer("raizes-do-nordeste-api")
                        .build()
                        .verify(token)
                        .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
