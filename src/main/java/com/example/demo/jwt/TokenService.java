package com.example.demo.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.demo.paciente.vo.PacienteVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.JWT;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String fSecret;

    public String generateToken(PacienteVO mPacienteVO){
        try {
            Algorithm mAlgorithm = Algorithm.HMAC256(fSecret);
            String mToken = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(mPacienteVO.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(mAlgorithm);

            return mToken;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String validateToken(String mToken){
        try {
            Algorithm mAlgorithm = Algorithm.HMAC256(fSecret);
            return JWT.require(mAlgorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(mToken)
                    .getSubject();
        } catch (JWTCreationException exception){
            return "";
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
