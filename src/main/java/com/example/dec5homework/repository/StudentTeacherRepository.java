package com.example.dec5homework.repository;

import com.example.dec5homework.entity.Student;
import com.example.dec5homework.entity.StudentTeacher;
import com.example.dec5homework.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository for the student_teacher join table
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {

    // Find all teacher relations for a given student
    List<StudentTeacher> findByStudent(Student student);

    // Find all student relations for a given teacher
    List<StudentTeacher> findByTeacher(Teacher teacher);

    // Check if a specific student-teacher relation already exists
    boolean existsByStudentAndTeacher(Student student, Teacher teacher);

    // Delete one specific student-teacher relation
    void deleteByStudentAndTeacher(Student student, Teacher teacher);
}