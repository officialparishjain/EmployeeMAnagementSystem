package com.parishjain.EMS.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @NotBlank(message = "Employee first name cannot be null..")
    private String empFirstName;

    private String empLastName;

    @NotBlank(message = "Employee Email Cannot be null or Blank..")
    @Column(name = "Email",unique = true)
    @Email(message = "Kindly enter the correct email address..")
    private String employeeEmail;

    @Size(min = 10,max = 10)
    @Column(name = "Phone_No",unique = true)
    private String employeePhoneNumber;

    @NotBlank(message = "Employee address cannot be blank or null.")
    private String employeeAddress;

    private String employeeJobRole;
    private String employeeSalary;
    private LocalDate employeeHireDate;

}
