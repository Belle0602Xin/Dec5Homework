package com.example.dec5homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "teachers")
// Entity class representing the teacher table
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the teacher
    private Long id;

    // Teacher's name
    private String name;

    // Subject taught by the teacher
    private String subject;

    // Default constructor required by JPA
    public Teacher() {
    }

    // Constructor for creating a new teacher
    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    // Return teacher ID
    public Long getId() {
        return id;
    }

    // Return teacher name
    public String getName() {
        return name;
    }

    // Update teacher name
    public void setName(String name) {
        this.name = name;
    }

    // Return teacher subject
    public String getSubject() {
        return subject;
    }

    // Update teacher subject
    public void setSubject(String subject) {
        this.subject = subject;
    }
}