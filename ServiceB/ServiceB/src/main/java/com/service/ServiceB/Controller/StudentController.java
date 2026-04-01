package com.service.ServiceB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.service.ServiceB.DTO.StudentDTO;
import com.service.ServiceB.ServiceL.StudentService;

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
}