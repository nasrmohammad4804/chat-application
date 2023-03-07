package com.nasr.chatapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Autowired
    private BeforeOTPAuthenticationFilter beforeOTPFilter;
    @Autowired
    private OTPAuthenticationFilter OTPFilter;

    @Autowired
    private AuthorizationFilter authorizationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().requestMatchers(OPTIONS).permitAll();
        http.authorizeHttpRequests()
                        .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.csrf().disable();


        http.headers(header -> header.frameOptions().sameOrigin());

        http.addFilterAt(beforeOTPFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(OTPFilter, BeforeOTPAuthenticationFilter.class);
        http.addFilterBefore(authorizationFilter,OTPAuthenticationFilter.class);
        return http.build();
    }
}
