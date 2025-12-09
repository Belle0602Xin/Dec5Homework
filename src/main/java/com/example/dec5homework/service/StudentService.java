package com.example.dec5homework.service;

import com.example.dec5homework.dto.StudentsTeachersDto;
import com.example.dec5homework.entity.Student;
import com.example.dec5homework.entity.StudentTeacher;
import com.example.dec5homework.entity.Teacher;
import com.example.dec5homework.repository.StudentRepository;
import com.example.dec5homework.repository.StudentTeacherRepository;
import com.example.dec5homework.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// Service layer that handles business logic for students and teacher relationships
public class StudentService {

    // Inject repositories
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    // Constructor injection
    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }

    @Transactional
    // Create a new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get one student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Get list of all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    // Update student info by ID
    public Optional<Student> updateStudent(Long id, Student newData) {
        return studentRepository.findById(id).map(s -> {
            s.setName(newData.getName());
            s.setAge(newData.getAge());
            return studentRepository.save(s);
        });
    }

    @Transactional
    // Delete a student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    // Assign a teacher to a student (add relationship, prevent duplicates)
    public String assignTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        boolean alreadyExists = studentTeacherRepository.existsByStudentAndTeacher(student, teacher);
        if (alreadyExists) {
            return "Teacher " + teacher.getName() + " is already assigned to student " + student.getName();
        }

        StudentTeacher studentTeacher = new StudentTeacher(student, teacher);
        studentTeacherRepository.save(studentTeacher);

        return "Assigned teacher " + teacher.getName() + " to student " + student.getName();
    }

    // Get all teacher relationships for one student
    public List<StudentTeacher> getRelationsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return studentTeacherRepository.findByStudent(student);
    }

    // Get all students together with their teachers (for DTO response)
    public List<StudentsTeachersDto> getTeachersByAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentsTeachersDto> result = new ArrayList<>();

        for (Student s : students) {
            List<StudentTeacher> relations = studentTeacherRepository.findByStudent(s);

            List<Teacher> teachers = relations.stream()
                    .map(StudentTeacher::getTeacher)
                    .collect(Collectors.toList());

            StudentsTeachersDto dto = new StudentsTeachersDto(
                    s.getId(),
                    s.getName(),
                    teachers
            );

            result.add(dto);
        }

        return result;
    }

    @Transactional
    // Replace all teacher relationships with a new teacher for one student
    public String updateTeacherForStudent(Long studentId, Long newTeacherId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Teacher newTeacher = teacherRepository.findById(newTeacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        List<StudentTeacher> relations = studentTeacherRepository.findByStudent(student);

        if (relations.isEmpty()) {
            return "Student has no assigned teacher. Use assignTeacher API instead.";
        }

        studentTeacherRepository.deleteAll(relations);

        StudentTeacher newRelation = new StudentTeacher(student, newTeacher);
        studentTeacherRepository.save(newRelation);

        return "Updated teacher of student " + student.getName() + " to " + newTeacher.getName();
    }

    @Transactional
    // Remove one teacher from a specific student
    public String removeTeacherFromStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        boolean alreadyExists = studentTeacherRepository.existsByStudentAndTeacher(student, teacher);
        if (!alreadyExists) {
            return "No relation between student " + student.getName() + " and teacher " + teacher.getName();
        }

        studentTeacherRepository.deleteByStudentAndTeacher(student, teacher);

        return "Removed teacher " + teacher.getName() + " from student " + student.getName();
    }
}