package com.nasr.chatapplication.base.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity <ID extends Serializable>{

    public static final String IS_DELETED="is_deleted";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

/*    @Column(name = IS_DELETED,columnDefinition = "bool(1)")
    protected boolean isDeleted;*/

    public BaseEntity(ID id) {
        this.id = id;
    }
}
