package com.service.ServiceB.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NotBlank(message = "First name required")
    private String first_name;

    private String last_name;

    @NotBlank(message = "DOB required")
    private String date_of_birth;

    @Pattern(regexp = "Male|Female|Other", message = "Invalid gender")
    private String gender;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone_number;

    @Email(message = "Invalid email")
    private String email;

    private String course_name;

    @Pattern(regexp = "A\\+|A-|B\\+|B-|O\\+|O-|AB\\+|AB-", message = "Invalid blood group")
    private String blood_group;

    @DecimalMin(value = "5.0", message = "CGPA >= 5")
    @DecimalMax(value = "10.0", message = "CGPA <= 10")
    private String cgpa;
}