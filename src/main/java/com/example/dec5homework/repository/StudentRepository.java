package com.example.dec5homework.repository;

import com.example.dec5homework.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Student entity, provides basic CRUD operations
public interface StudentRepository extends JpaRepository<Student, Long> {
}