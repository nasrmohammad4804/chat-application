package com.nasr.chatapplication.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.constant.ConstantMessage.TEXT_MESSAGE_VALUE;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = TEXT_MESSAGE_VALUE)
public class TextMessage extends Message{

    private  String content;

    public TextMessage(User user, Receiver receiver, String content) {
        super(user, receiver);
        this.content = content;
    }
}
