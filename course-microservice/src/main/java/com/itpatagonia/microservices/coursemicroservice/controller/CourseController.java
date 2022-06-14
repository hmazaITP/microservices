package com.itpatagonia.microservices.coursemicroservice.controller;

import com.itpatagonia.microservices.coursemicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.coursemicroservice.model.Course;
import com.itpatagonia.microservices.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value ="/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course courseNew = courseService.createCourse(course);
        return new ResponseEntity<Course>(courseNew,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id) {

        try {
            Course course = courseService.findById(id);
            return ResponseEntity.ok(course);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Course No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Course No encontrado ");
        }
    }

    @GetMapping("/byexamen/{id}")
    public ResponseEntity<?> getCourseByExamenId(@PathVariable("id") Long id) {

        List<Course> courses = courseService.findByExamId(id);
        Map<String,Object> courseMap = new HashMap<>();

        if (courses.isEmpty()){
            return ResponseEntity.ok().body(courseMap.put("El examen con id "+ id , "No tiene cursos"));
        }
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        Course courseNew = new Course();
        try {
            courseNew = courseService.updateCourse(course);
            return ResponseEntity.ok(courseNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Course No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.status(400).body(courseNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id){
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Course Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Course No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Course No Eliminado");
        }
    }

}
