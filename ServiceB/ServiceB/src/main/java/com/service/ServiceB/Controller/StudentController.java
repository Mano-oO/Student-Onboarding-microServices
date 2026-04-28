package com.service.ServiceB.Controller;

import com.service.ServiceB.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.service.ServiceB.DTO.StudentDTO;
import com.service.ServiceB.ServiceL.StudentService;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public String receiveData(@RequestBody List<StudentDTO> data) {
        service.saveAll(data);
        return "Batch Data Saved Successfully!";
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/hello")
    public String hello() {
        return "GET is working";
    }

    @PostMapping("/single")
    public String addStudent(@Valid @RequestBody StudentDTO dto) {
        service.saveOne(dto);
        return "Student Saved!";
    }




}
