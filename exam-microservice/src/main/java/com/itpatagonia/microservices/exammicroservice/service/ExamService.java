package com.itpatagonia.microservices.exammicroservice.service;

import com.itpatagonia.microservices.exammicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.exammicroservice.model.Exam;
import com.itpatagonia.microservices.exammicroservice.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public Exam findById(Long id) throws NoEntityException {
        return examRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Exam con " + id));
    }

    public List<Exam> finByStudentId(Long studentId){
        return examRepository.findByStudentId(studentId);
    }

    public Exam updateExam(Exam exam) throws NoEntityException {
        Exam examOld = examRepository.findById(exam.getId()).orElseThrow(
                () -> new NoEntityException("No existe Exam con " + exam.getId()));
        //examOld.setName(exam.getName());
        return examRepository.save(examOld);
    }

    public void deleteExam(Long id) throws NoEntityException {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Exam con " + id));
        examRepository.delete(exam);
    }

}
