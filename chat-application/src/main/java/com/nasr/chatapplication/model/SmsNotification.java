package com.nasr.chatapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsNotification extends Notification{

    private final String phoneNumber;

    public SmsNotification(String content, String phoneNumber) {
        super(content);
        this.phoneNumber = phoneNumber;
    }
}
