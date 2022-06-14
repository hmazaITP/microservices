package com.itpatagonia.microservices.studentmicroservice.feignclients;

import com.itpatagonia.microservices.studentmicroservice.model.Exam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="exam-microservice", url = "http://localhost:8002/exams")
public interface ExamFeignClient {
    @PostMapping("/create")
    public Exam createExam(@RequestBody Exam exam);

}
