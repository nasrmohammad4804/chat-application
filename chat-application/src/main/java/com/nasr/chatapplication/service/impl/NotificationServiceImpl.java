package com.nasr.chatapplication.service.impl;

import com.nasr.chatapplication.model.Notification;
import com.nasr.chatapplication.model.SmsNotification;
import com.nasr.chatapplication.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void send(Notification notification) {

        if(notification instanceof SmsNotification){
            /*send message with sms service to client*/
        }
    }
}
