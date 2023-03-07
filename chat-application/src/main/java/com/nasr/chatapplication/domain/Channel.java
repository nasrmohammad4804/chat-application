package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.constant.ConstantMessage;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.constant.ConstantMessage.*;
import static com.nasr.chatapplication.domain.Channel.CHANNEL_ID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = CHANNEL_RECEIVER_VALUE)
//@PrimaryKeyJoinColumn(name = CHANNEL_ID)
public class Channel extends Receiver{

    public static final String CHANNEL_ID="channel_id";
    private String name;

    public Channel(Long id, String name) {
        super(id);
        this.name = name;
    }
}
