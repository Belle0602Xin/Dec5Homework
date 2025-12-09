-- Table for storing student information
CREATE TABLE students (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          age INT
);

-- Table for storing teacher information
CREATE TABLE teachers (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          subject VARCHAR(100)
);

-- Join table storing the relationship between students and teachers
CREATE TABLE student_teacher (
                                 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                 student_id BIGINT NOT NULL,
                                 teacher_id BIGINT NOT NULL,
                                 CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id),
                                 CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);