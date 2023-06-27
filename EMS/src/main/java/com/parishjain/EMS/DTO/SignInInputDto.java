package com.parishjain.EMS.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInputDto {

    @Email
    private String email;

    @NotBlank
    private String password;
}
