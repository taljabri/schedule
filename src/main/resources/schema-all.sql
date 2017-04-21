DROP TABLE IF EXISTS schedule;

CREATE TABLE schedule  (
    CRN INT PRIMARY KEY,
    faculty_Name VARCHAR(60),
    student_count INT,
    course_number INT,
    semester VARCHAR (10),
    year INT,
    department VARCHAR (30)
);
