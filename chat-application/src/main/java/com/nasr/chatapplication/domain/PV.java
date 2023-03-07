package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.constant.ConstantMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.constant.ConstantMessage.*;
import static com.nasr.chatapplication.domain.PV.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = PV_RECEIVER_VALUE)
//@PrimaryKeyJoinColumn(name = PV_ID)
public class PV extends Receiver{
    public static final String PV_ID="pv_id";
    public static final String USER_ID="user_id";
    public static final String CONTACT_ID="contact_id";
    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = CONTACT_ID)
    private User contact;

    public PV(Long id, User user, User contact) {
        super(id);
        this.user = user;
        this.contact = contact;
    }
}
