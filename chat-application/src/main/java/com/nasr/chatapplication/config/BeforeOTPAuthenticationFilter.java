package com.nasr.chatapplication.config;

import com.nasr.chatapplication.cache.CacheStore;
import com.nasr.chatapplication.model.SmsNotification;
import com.nasr.chatapplication.service.NotificationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.nasr.chatapplication.enumeration.NotificationType.SMS;
import static com.nasr.chatapplication.util.NotificationMessageGenerator.generateNotificationMessage;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@Component
public class BeforeOTPAuthenticationFilter extends OncePerRequestFilter {

    public static final String VERIFICATION_REQUEST = "/verification";
    public static final int VERIFICATION_CODE_LENGTH=6;
    @Autowired
    private CacheStore<String> cacheStore;
    @Autowired
    private NotificationService notificationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains(VERIFICATION_REQUEST)) {

            String phoneNumber = request.getParameter("username");

            String code = RandomStringUtils.randomAlphanumeric(VERIFICATION_CODE_LENGTH);
            cacheStore.add(phoneNumber,code);

            String content = generateNotificationMessage(SMS, code);
            notificationService.send(new SmsNotification(content,phoneNumber));

            String message="verification code successfully sent !";
            response.setStatus(SC_OK);
            response.getOutputStream().write(message.getBytes());

        } else filterChain.doFilter(request, response);
    }
}
