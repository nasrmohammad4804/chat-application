package com.nasr.chatapplication.domain;

import com.nasr.chatapplication.base.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nasr.chatapplication.domain.User.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME,uniqueConstraints = @UniqueConstraint(columnNames = {PHONE_NUMBER}))
@Entity
public class User extends BaseEntity<Long> {

    public static final String FIRST_NAME="first_name";
    public static final String LAST_NAME="last_name";
    public static final String PHONE_NUMBER="phone_number";
    public static final String TABLE_NAME="user_table";

    @Column(name = FIRST_NAME)
    private String firstName;
    @Column(name = LAST_NAME)
    private String lastName;
    @Column(name =PHONE_NUMBER )
    private String phoneNumber;
    private String avatar;

}
