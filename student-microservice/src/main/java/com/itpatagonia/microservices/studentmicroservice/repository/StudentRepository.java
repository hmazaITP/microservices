package com.itpatagonia.microservices.studentmicroservice.repository;

import com.itpatagonia.microservices.studentmicroservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
