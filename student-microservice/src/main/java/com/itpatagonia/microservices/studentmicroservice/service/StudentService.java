package com.itpatagonia.microservices.studentmicroservice.service;

import com.itpatagonia.microservices.studentmicroservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.studentmicroservice.feignclients.ExamFeignClient;
import com.itpatagonia.microservices.studentmicroservice.model.Exam;
import com.itpatagonia.microservices.studentmicroservice.model.Student;
import com.itpatagonia.microservices.studentmicroservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExamFeignClient examFeignClient;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Exam createExam(@RequestBody Exam exam){
        return examFeignClient.createExam(exam);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws NoEntityException {
        return studentRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Student con " + id));
    }

    public List<Exam> getExams(Long studentId){
        //return restTemplate.getForObject("http://localhost:8002/exams/bystudent/"+studentId,List.class);
        return (List<Exam>) examFeignClient.finByStudentId(studentId).getBody();
    }

    public Student updateStudent(Student student) throws NoEntityException {
        Student studentNew = studentRepository.findById(student.getId()).orElseThrow(
                () -> new NoEntityException("No existe Student con " + student.getId()));
        studentNew.setSurname(student.getSurname());
        studentNew.setName(student.getName());
        studentNew.setBirthday(student.getBirthday());
        return studentRepository.save(studentNew);
    }

    public void deleteStudent(Long id) throws NoEntityException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Student con " + id));
        studentRepository.delete(student);
    }

    public int calcularEdad(Long id) throws NoEntityException {
        Optional<Student> student = this.studentRepository.findById(id);
        return Period.between(student.orElseThrow(() -> new NoEntityException("No existe Student con " + id)).getBirthday(),
                    LocalDate.now()).getYears();

    }

}
