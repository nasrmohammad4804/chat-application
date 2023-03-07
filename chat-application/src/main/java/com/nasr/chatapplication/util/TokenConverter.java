package com.nasr.chatapplication.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.net.HttpCookie;

import static com.nasr.chatapplication.constant.ConstantMessage.*;

public class TokenConverter {

    public static void convertTokensToCookie(String[] tokens, HttpServletResponse response){
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

        Cookie accessTokenCookie = new Cookie(ACCESS_TOKEN, accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(ACCESS_TOKEN_EXPIRATION_IN_MINUTE.intValue());

        Cookie refreshTokenCookie = new Cookie(REFRESH_TOKEN, refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(REFRESH_TOKEN_EXPIRATION_IN_MINUTE.intValue());

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }
}
