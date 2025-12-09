package com.example.dec5homework.repository;

import com.example.dec5homework.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Teacher entity, provides basic CRUD operations
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}