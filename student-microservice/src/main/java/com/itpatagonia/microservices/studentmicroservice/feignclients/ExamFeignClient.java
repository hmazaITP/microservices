package com.itpatagonia.microservices.studentmicroservice.feignclients;

import com.itpatagonia.microservices.studentmicroservice.model.Exam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name="exam-microservice", url = "http://localhost:8002/exams")
@FeignClient(name="exam-microservice")
public interface ExamFeignClient {
    @PostMapping("/exams")
    public Exam createExam(@RequestBody Exam exam);

    @GetMapping("/exams/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId);

}
