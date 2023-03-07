package com.nasr.chatapplication.controller;

import com.nasr.chatapplication.model.request.RefreshTokenRequestDto;
import com.nasr.chatapplication.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.nasr.chatapplication.util.TokenConverter.convertTokensToCookie;

@RestController
public class TokenController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/refresh-token")
    public String refreshToken(@RequestBody @Valid RefreshTokenRequestDto refreshTokenRequest, HttpServletRequest request, HttpServletResponse response) {

        String[] tokens = jwtUtils.generateNewTokenByRefreshToken(refreshTokenRequest.getRefreshToken(), request.getRequestURI());
        convertTokensToCookie(tokens, response);

        return "token successfully was refreshed !";
    }

}
