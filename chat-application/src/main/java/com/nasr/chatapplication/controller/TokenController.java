package com.nasr.chatapplication.controller;

import com.nasr.chatapplication.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nasr.chatapplication.util.TokenConverter.convertTokensToCookie;

@RestController
public class TokenController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/refresh-token")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        String[] tokens = jwtUtils.getTokens(userName, request.getRequestURI());
        convertTokensToCookie(tokens, response);

        return "token successfully was refreshed !";
    }

}
