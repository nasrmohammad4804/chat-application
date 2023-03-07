package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import com.nasr.chatapplication.constant.ConstantMessage;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.constant.ConstantMessage.*;

@Setter
@Getter
@Entity
@Inheritance
@NoArgsConstructor
@DiscriminatorColumn(name = RECEIVER_TYPE)
public abstract class Receiver extends BaseEntity<Long> {
    public Receiver(Long id) {
        super(id);
    }
}
