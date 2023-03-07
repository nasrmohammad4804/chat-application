package com.nasr.chatapplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
}
