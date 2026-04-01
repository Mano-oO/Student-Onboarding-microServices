package com.service.ServiceB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.ServiceB.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}