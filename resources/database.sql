CREATE DATABASE IF NOT EXISTS placement_db;
USE placement_db;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL -- 'ADMIN' or 'STUDENT'
);

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    cgpa DOUBLE,
    skills VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS companies (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    min_cgpa DOUBLE,
    required_skills VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS jobs (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    company_id INT,
    role VARCHAR(100),
    package_lpa DOUBLE,
    FOREIGN KEY (company_id) REFERENCES companies(company_id)
);
