package com.nasr.chatapplication.util;

import com.nasr.chatapplication.enumeration.NotificationType;
import com.nasr.chatapplication.model.Notification;
import com.nasr.chatapplication.model.SmsNotification;

public class NotificationMessageGenerator {

    public static String generateNotificationMessage(NotificationType notificationType, String verificationCode) {

        return switch (notificationType) {
            case SMS -> generateSmsMessage(verificationCode);
            case EMAIL -> null;
        };
    }

    private static String generateSmsMessage(String verificationCode) {
        return "hi . use this verification code " + verificationCode + " for continue of processing in application. " +
                "this code valid until 3 minute later";

    }
}
