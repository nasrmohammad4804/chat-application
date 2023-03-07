package com.nasr.chatapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;

@Configuration
@EnableWebSocketSecurity
public class SocketSecurityConfig {


    @Bean
    public AuthorizationManager<Message<?>> messageAuthorizationManager(MessageMatcherDelegatingAuthorizationManager.Builder  messages){

        messages.nullDestMatcher().authenticated();
        messages.simpDestMatchers("/chat/**").authenticated();
        /*add other role for feature like group in future */
        messages.simpSubscribeDestMatchers("/public/**","/queue/**").authenticated();
        messages.simpTypeMatchers(MESSAGE,SUBSCRIBE).denyAll();
        return messages.build();
    }
}
