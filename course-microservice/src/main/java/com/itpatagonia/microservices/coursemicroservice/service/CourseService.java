package com.itpatagonia.microservices.coursemicroservice.service;

import com.itpatagonia.microservices.coursemicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.coursemicroservice.model.Course;
import com.itpatagonia.microservices.coursemicroservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) throws NoEntityException {
        return courseRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Course con " + id));
    }

    public List<Course> findByExamId( Long examId ){
        return courseRepository.findByExamId( examId );
    }

    public Course updateCourse(Course course) throws NoEntityException {
        Course courseOld = courseRepository.findById(course.getId()).orElseThrow(
                () -> new NoEntityException("No existe Course con " + course.getId()));
        courseOld.setName(course.getName());
        return courseRepository.save(courseOld);
    }

    public void deleteCourse(Long id) throws NoEntityException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Course con " + id));
        courseRepository.delete(course);
    }

}
