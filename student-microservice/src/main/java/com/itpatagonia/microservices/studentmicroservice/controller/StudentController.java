package com.itpatagonia.microservices.studentmicroservice.controller;

import com.itpatagonia.microservices.studentmicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.studentmicroservice.model.Exam;
import com.itpatagonia.microservices.studentmicroservice.model.Student;
import com.itpatagonia.microservices.studentmicroservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentNew = studentService.createStudent(student);
        return new ResponseEntity<Student>(studentNew,HttpStatus.CREATED);
    }

    @PostMapping(value ="/createexam/{studentId}")
    public ResponseEntity<Exam> createExam(@PathVariable("studentId") Long studentId , @RequestBody Exam exam){
        exam.setStudentId(studentId);
        return ResponseEntity.ok().body(studentService.createExam(exam));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {

        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Student No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No encontrado ");
        }
    }

    @GetMapping("/examsbystudent/{id}")
    public ResponseEntity<Map<String,Object>> getExams(@PathVariable("id") Long studentId){
        List<Exam> exams = studentService.getExams(studentId);
        Map<String,Object> studentMap = new HashMap<>();
        if ( exams.isEmpty()){
            studentMap.put("El estudiante " + studentId , " No tiene Examenes " );
        }
        studentMap.put("El estudiante " + studentId , exams);
        return ResponseEntity.ok().body(studentMap);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student studentNew = new Student();
        try {
            studentNew = studentService.updateStudent(student);
            return ResponseEntity.ok(studentNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Student No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.status(400).body(studentNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Student No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No Eliminado");
        }
    }

}
