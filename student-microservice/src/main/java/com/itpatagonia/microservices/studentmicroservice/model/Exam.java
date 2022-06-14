package com.itpatagonia.microservices.studentmicroservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

public class Exam {
    private Long id;
    private LocalDate examenDate;
    private Float score;
    private Long studentId;

    public Exam() {
    }

    public Exam(Long id, LocalDate examenDate, Float score, Long studentId) {
        this.id = id;
        this.examenDate = examenDate;
        this.score = score;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExamenDate() {
        return examenDate;
    }

    public void setExamenDate(LocalDate examenDate) {
        this.examenDate = examenDate;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examenDate=" + examenDate +
                ", score=" + score +
                ", studentId=" + studentId +
                '}';
    }
}