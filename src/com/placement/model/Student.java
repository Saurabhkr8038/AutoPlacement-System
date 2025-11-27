package com.placement.model;

public class Student extends User {
    private String name;
    private String email;
    private double cgpa;
    private String skills;

    public Student(int id, String username, String password, String name, String email, double cgpa, String skills) {
        super(id, username, password, "STUDENT");
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.skills = skills;
    }

    // Encapsulation: Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    // Polymorphism: Overriding toString()
    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", CGPA=" + cgpa + ", Skills=" + skills + "]";
    }
}
