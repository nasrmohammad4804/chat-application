package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.domain.Channel.CHANNEL_ID;
import static com.nasr.chatapplication.domain.PV.USER_ID;
import static com.nasr.chatapplication.domain.Role.ROLE_ID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserChannel extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = CHANNEL_ID)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = ROLE_ID)
    private Role role;
}
