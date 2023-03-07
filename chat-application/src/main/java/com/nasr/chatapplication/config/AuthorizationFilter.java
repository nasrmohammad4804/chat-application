package com.nasr.chatapplication.config;

import com.nasr.chatapplication.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.nasr.chatapplication.constant.ConstantMessage.ACCESS_TOKEN;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    private final List<String> shouldNotFilterPath = List.of(
            "/api/login", "/verification","/refresh-token"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;

        if (cookies != null) {

            for (Cookie cookie : cookies)
                if (cookie.getName().equals(ACCESS_TOKEN))
                    tokenCookie = cookie;
        }
        try {

            if (tokenCookie != null) {
                Authentication authentication = jwtUtils.validateToken(tokenCookie.getValue());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("error occurred : [{}] while decode token", e.getMessage());
            response.setStatus(SC_UNAUTHORIZED);
            response.getOutputStream().write("invalid token".getBytes());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return shouldNotFilterPath.contains(request.getServletPath());
    }
}
