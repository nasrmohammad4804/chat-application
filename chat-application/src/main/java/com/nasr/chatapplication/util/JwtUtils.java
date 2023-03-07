package com.nasr.chatapplication.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nasr.chatapplication.config.OTPAuthenticationToken;
import com.nasr.chatapplication.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nasr.chatapplication.constant.ConstantMessage.ACCESS_TOKEN_EXPIRATION_IN_MINUTE;
import static com.nasr.chatapplication.constant.ConstantMessage.REFRESH_TOKEN_EXPIRATION_IN_MINUTE;

@Component
public class JwtUtils {


    @Value("${private-sign-key}")
    private String privateKey;

    public String[] getTokens(String phoneNumber, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey.getBytes());
        String accessToken = JWT.create()
                .withSubject(phoneNumber)
                .withExpiresAt(new Date(System.currentTimeMillis() + (ACCESS_TOKEN_EXPIRATION_IN_MINUTE * 1000)))
                .withIssuer(issuer)
                .sign(algorithm);


        String refreshToken = JWT.create()
                .withSubject(phoneNumber)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_IN_MINUTE * 1000))
                .withIssuer(issuer)
                .sign(algorithm);

        return new String[]{accessToken, refreshToken};
    }

    public Authentication validateToken(String token) {

        Pattern pattern = Pattern.compile("\\b(\\S+)\\.(\\S+)\\.(\\S+)");
        Matcher matcher = pattern.matcher(token);

        if (matcher.find())
            token = matcher.group();

        else throw new IllegalStateException("token format not valid");


        Algorithm algorithm = Algorithm.HMAC256(privateKey.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt = verifier.verify(token);
        String phoneNumber = decodedJwt.getSubject();

        return new OTPAuthenticationToken(phoneNumber, Collections.emptyList());

    }

    public String[] generateNewTokenByRefreshToken(String refreshToken, String issuer) {

        String username;

        try {
            Authentication authentication = validateToken(refreshToken);
            username = authentication.getName();

        } catch (Exception e) {
            throw new TokenExpiredException("refresh token not valid !");
        }
        return getTokens(username, issuer);
    }
}
