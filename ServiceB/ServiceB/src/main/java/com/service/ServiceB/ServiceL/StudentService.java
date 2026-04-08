package com.service.ServiceB.ServiceL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.service.ServiceB.DTO.StudentDTO;
import com.service.ServiceB.entity.Student;
import com.service.ServiceB.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public void saveAll(List<StudentDTO> dtos) {

        List<Student> students = new ArrayList<>();

        for (StudentDTO dto : dtos) {

            Student s = new Student();
            s.setFirstName(dto.getFirst_name());
            s.setLastName(dto.getLast_name());

            if (dto.getDate_of_birth() != null && !dto.getDate_of_birth().isEmpty()) {
                s.setDateOfBirth(LocalDate.parse(dto.getDate_of_birth()));
            }

            s.setGender(dto.getGender());
            s.setPhoneNumber(dto.getPhone_number());
            s.setEmail(dto.getEmail());
            s.setCourseName(dto.getCourse_name());
            s.setBloodGroup(dto.getBlood_group());

            if (dto.getCgpa() != null && !dto.getCgpa().isEmpty()) {
                try {
                    s.setCgpa(Double.parseDouble(dto.getCgpa()));
                } catch (Exception e) {
                    s.setCgpa(null);
                }
            }
            students.add(s);
        }
        repository.saveAll(students);
    }

}
