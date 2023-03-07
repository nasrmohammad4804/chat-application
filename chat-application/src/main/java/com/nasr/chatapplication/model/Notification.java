package com.nasr.chatapplication.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {

    protected String content;

    public Notification(String content) {
        this.content = content;
    }
}
