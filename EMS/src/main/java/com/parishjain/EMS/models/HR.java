package com.parishjain.EMS.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class HR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Email
    @NotBlank(message = "Email cannot be blank.")
    @Column(name = "email",unique = true)
    private String adminEmail;

    @Column(name = "password")
    @Size(min = 7,max = 25,message = "Password should be between 7-25 characters.")
    private String adminPassword;



}
