-- Insert sample students for initial data
INSERT INTO students (name, age) VALUES ('Yuxin', 30);
INSERT INTO students (name, age) VALUES ('Hou', 22);

-- Insert sample teachers with their subjects
INSERT INTO teachers (name, subject) VALUES ('Mr. Yang', 'Java');
INSERT INTO teachers (name, subject) VALUES ('Mr. Lee', 'Python');

-- Create initial student-teacher relationships
INSERT INTO student_teacher (student_id, teacher_id) VALUES (1, 1);
INSERT INTO student_teacher (student_id, teacher_id) VALUES (2, 2);