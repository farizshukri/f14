CREATE DATABASE exam_seating;

USE exam_seating;

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    roll_no VARCHAR(20) UNIQUE,
    section VARCHAR(10),
    branch VARCHAR(50),
    year INT
);

CREATE TABLE seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    seat_number VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255)
);
