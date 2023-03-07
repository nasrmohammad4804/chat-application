package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.domain.User.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends BaseEntity<Long> {

    public static final String FROM_USER="from_user";
    public static final String TO_USER="to_user";


    @ManyToOne
    @JoinColumn(name = FROM_USER)
    private User from;

    @ManyToOne
    @JoinColumn(name = TO_USER)
    private User to;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;
}
