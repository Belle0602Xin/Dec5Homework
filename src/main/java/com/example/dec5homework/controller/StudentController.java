package com.example.dec5homework.controller;

import com.example.dec5homework.dto.StudentsTeachersDto;
import com.example.dec5homework.entity.Student;
import com.example.dec5homework.entity.StudentTeacher;
import com.example.dec5homework.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
// Controller for all student-related endpoints
public class StudentController {

    private final StudentService studentService;

    // Inject StudentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    // Create a new student
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    // Get all students
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    // Get one student by ID
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    // Update a student by ID
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> updated = studentService.updateStudent(id, student);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    // Delete a student by ID
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studentId}/teachers/{teacherId}")
    // Assign a teacher to a student (add relationship)
    public ResponseEntity<String> assignTeacher(@PathVariable Long studentId, @PathVariable Long teacherId) {
        return ResponseEntity.ok(studentService.assignTeacher(studentId, teacherId));
    }

    @GetMapping("/{id}/teachers")
    // Get all teachers for a specific student
    public ResponseEntity<List<StudentTeacher>> getTeachers(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getRelationsByStudent(id));
    }

    @GetMapping("/teachers/all")
    // Get all students and their teachers
    public ResponseEntity<List<StudentsTeachersDto>> getTeachersByAllStudents() {
        return ResponseEntity.ok(studentService.getTeachersByAllStudents());
    }

    @PutMapping("/{studentId}/teachers/{newTeacherId}")
    // Replace a student's teacher with a new one
    public ResponseEntity<String> updateTeacher(@PathVariable Long studentId, @PathVariable Long newTeacherId) {
        String result = studentService.updateTeacherForStudent(studentId, newTeacherId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{studentId}/teachers/{teacherId}")
    // Remove one specific teacher from a student
    public ResponseEntity<String> removeTeacherFromStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        String msg = studentService.removeTeacherFromStudent(studentId, teacherId);
        return ResponseEntity.ok(msg);
    }
}