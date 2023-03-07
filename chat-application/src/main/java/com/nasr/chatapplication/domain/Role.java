package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {

    public static final String ROLE_ID="role_id";

    private String name;

    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }
}
