package com.nasr.chatapplication.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasr.chatapplication.cache.CacheStore;
import com.nasr.chatapplication.exception.InvalidVerificationCodeException;
import com.nasr.chatapplication.exception.ResponseTemplate;
import com.nasr.chatapplication.model.response.UserResponseDto;
import com.nasr.chatapplication.service.UserService;
import com.nasr.chatapplication.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.nasr.chatapplication.constant.ConstantMessage.*;
import static com.nasr.chatapplication.util.TokenConverter.convertTokensToCookie;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class OTPAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CacheStore<String> cacheStore;
    @Autowired
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String phoneNumber = request.getParameter("username");
        String verificationCode = request.getParameter("verificationCode");

        try {
            if (phoneNumber == null || verificationCode == null)
                throw new AuthenticationServiceException("username or verificationCode not exists !");


            String cacheValue = cacheStore.getValue(phoneNumber)
                    .orElseThrow(() -> new InvalidVerificationCodeException(VERIFICATION_CODE_NOT_VALID));

            if (!cacheValue.equals(verificationCode))
                throw new InvalidVerificationCodeException(VERIFICATION_CODE_NOT_VALID);

            userService.getUserByPhoneNumber(phoneNumber)
                    .ifPresentOrElse(userResponseDto -> {
                        //generate token & cookie
                        Map<String, Object> details = getUserAuthenticatedDetails(userResponseDto);
                        try {
                            response.getOutputStream().write(mapper.writeValueAsBytes(details));
                            response.setStatus(SC_OK);
                            String[] tokens = jwtUtils.getTokens(phoneNumber, request.getRequestURI());
                            convertTokensToCookie(tokens, response);

                        } catch (IOException e) {
                            throw new IllegalStateException(e.getMessage());
                        }
                    }, () -> {
                        putNewUserAccountMessageToResponse(response);
                        String[] tokens = jwtUtils.getTokens(phoneNumber, request.getRequestURI());
                        convertTokensToCookie(tokens, response);
                    });


        } catch (InvalidVerificationCodeException e) {
            putVerificationExpireErrorMessageToResponse(response);
        }
    }


    private void putVerificationExpireErrorMessageToResponse(HttpServletResponse response) {
        try {

            ResponseTemplate<String> template =
                    new ResponseTemplate<>(VERIFICATION_CODE_NOT_VALID, UNAUTHORIZED);

            String output = mapper.writeValueAsString(template);
            response.getOutputStream().write(output.getBytes());
            response.setStatus(SC_UNAUTHORIZED);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void putNewUserAccountMessageToResponse(HttpServletResponse response) {
        try {

            ResponseTemplate<String> template =
                    new ResponseTemplate<>(NEW_ACCOUNT, OK);

            String output = mapper.writeValueAsString(template);
            response.getOutputStream().write(output.getBytes());
            response.setStatus(SC_OK);

        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

    }

    private Map<String, Object> getUserAuthenticatedDetails(UserResponseDto userResponseDto) {
        Map<String, Object> map = new HashMap<>();

        map.put("response", new ResponseTemplate<>(ALREADY_SIGNED_IN, OK));
        map.put("userInfo", userResponseDto);

        return map;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().contains("/login");
    }
}
