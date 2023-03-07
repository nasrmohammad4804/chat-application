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
import static com.nasr.chatapplication.domain.Group.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = GROUP_RECEIVER_VALUE)
//@PrimaryKeyJoinColumn(name = GROUP_ID)
public class Group extends Receiver{

    public static final String GROUP_ID="group_id";
    private String name;

    public Group(Long id, String name) {
        super(id);
        this.name = name;
    }
}
