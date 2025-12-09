package com.example.dec5homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "students")
// Entity class representing the student table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for student
    private Long id;

    // Student name
    private String name;

    // Student age
    private Integer age;

    // Default constructor required by JPA
    public Student() {
    }

    // Constructor with basic fields
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // Return student id
    public Long getId() {
        return id;
    }

    // Return student name
    public String getName() {
        return name;
    }

    // Update student name
    public void setName(String name) {
        this.name = name;
    }

    // Return student age
    public Integer getAge() {
        return age;
    }

    // Update student age
    public void setAge(Integer age) {
        this.age = age;
    }
}