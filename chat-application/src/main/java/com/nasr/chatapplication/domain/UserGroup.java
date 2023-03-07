package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.domain.Group.GROUP_ID;
import static com.nasr.chatapplication.domain.PV.USER_ID;
import static com.nasr.chatapplication.domain.Role.ROLE_ID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = GROUP_ID)
    private Group group;

    @ManyToOne
    @JoinColumn(name = ROLE_ID)
    private Role role;
}
