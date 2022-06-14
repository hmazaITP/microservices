package com.itpatagonia.microservices.exammicroservice.controller;

import com.itpatagonia.microservices.exammicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.exammicroservice.model.Exam;
import com.itpatagonia.microservices.exammicroservice.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping(value ="/create")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam){
        Exam examNew = examService.createExam(exam);
        return new ResponseEntity<Exam>(examNew,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Exam>> getExams(){
        return ResponseEntity.ok(examService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable("id") Long id) {

        try {
            Exam exam = examService.findById(id);
            return ResponseEntity.ok(exam);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Exam No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Exam No encontrado ");
        }
    }

    @GetMapping("/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId){
        return ResponseEntity.ok().body(examService.finByStudentId(studentId));
    }

    @PutMapping("/update")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam){
        Exam examNew = new Exam();
        try {
            examNew = examService.updateExam(exam);
            return ResponseEntity.ok(examNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Exam No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.status(400).body(examNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable("id") Long id){
        try {
            examService.deleteExam(id);
            return ResponseEntity.ok("Exam Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Exam No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Exam No Eliminado");
        }
    }

}
