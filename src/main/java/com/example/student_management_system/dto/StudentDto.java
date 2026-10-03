package com.example.student_management_system.dto;
//    this will act as a modal in between view and controller for transffering databetn one layer to another,
//    while entity.stdent will used for the databse this is a jpa entitny for the datavase

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    @NotEmpty(message = "Student first name should not be empty")
    private String firstName;
    @NotEmpty(message = "Student last name should not be empty")
    private String lastName;
    @NotEmpty(message = "Student email should not be empty")
    @Email
    private String email;
}
