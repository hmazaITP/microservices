package com.itpatagonia.microservices.exammicroservice.repository;

import com.itpatagonia.microservices.exammicroservice.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findByStudentId(Long studentId);
}
