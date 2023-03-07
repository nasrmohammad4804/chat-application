package com.nasr.chatapplication.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDto {

    @NotBlank(message = "firstName is required !")
    private String firstName;

    @NotBlank(message = "lastName is required !")
    private String lastName;

    @Pattern(regexp = "09[\\d]{8}")
    @NotBlank(message = "phoneNumber is required !")
    private String phoneNumber;

    private String avatar;
}
