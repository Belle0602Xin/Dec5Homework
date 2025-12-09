package com.example.dec5homework.dto;

import com.example.dec5homework.entity.Teacher;
import java.util.List;

// DTO class used to return each student together with all their teachers
public class StudentsTeachersDto {

    // ID of the student
    private Long studentId;

    // Name of the student
    private String studentName;

    // List of teachers assigned to this student
    private List<Teacher> teachers;

    // Constructor to build the DTO with student info and teacher list
    public StudentsTeachersDto(Long studentId, String studentName, List<Teacher> teachers) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.teachers = teachers;
    }

    // Getter for student ID
    public Long getStudentId() {
        return studentId;
    }

    // Getter for student name
    public String getStudentName() {
        return studentName;
    }

    // Getter for the list of teachers
    public List<Teacher> getTeachers() {
        return teachers;
    }
}