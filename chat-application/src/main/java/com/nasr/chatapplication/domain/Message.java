package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import com.nasr.chatapplication.constant.ConstantMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.constant.ConstantMessage.*;
import static com.nasr.chatapplication.domain.PV.USER_ID;

@Inheritance
@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorColumn(name = MESSAGE_TYPE)
public abstract class Message extends BaseEntity<Long> {
    public static final String RECEIVER_ID="receiver_id";
    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = RECEIVER_ID)
    private Receiver receiver;

    public Message(User user, Receiver receiver) {
        this.user = user;
        this.receiver = receiver;
    }
}
