package com.service.ServiceB.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String gender;
    private String phone_number;
    private String email;
    private String course_name;
    private String blood_group;
    private String cgpa;
}