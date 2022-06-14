package com.itpatagonia.microservices.coursemicroservice.repository;

import com.itpatagonia.microservices.coursemicroservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByExamId( Long examenId );
}
