package com.example.dec5homework.entity;

import jakarta.persistence.*;

// Entity representing the join table between students and teachers
@Entity
@Table(name = "student_teacher")
public class StudentTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key of the relation
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    // Reference to the student in the relationship
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    // Reference to the teacher in the relationship
    private Teacher teacher;

    // Default constructor required by JPA
    public StudentTeacher() {
    }

    // Constructor to create a student-teacher relationship
    public StudentTeacher(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    // Return relationship ID
    public Long getId() {
        return id;
    }

    // Return the student
    public Student getStudent() {
        return student;
    }

    // Update the student field
    public void setStudent(Student student) {
        this.student = student;
    }

    // Return the teacher
    public Teacher getTeacher() {
        return teacher;
    }

    // Update the teacher field
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}