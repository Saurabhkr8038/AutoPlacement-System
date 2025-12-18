package com.placement.model;

public class Student extends User {
    private String name;
    private String email;
    private double cgpa;
    private String skills;
    private String branch;
    private int backlogs;
    private double testScore;

    public Student(int id, String username, String password, String name, String email, double cgpa, String skills, String branch, int backlogs, double testScore) {
        super(id, username, password, "STUDENT");
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.skills = skills;
        this.branch = branch;
        this.backlogs = backlogs;
        this.testScore = testScore;
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

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public int getBacklogs() { return backlogs; }
    public void setBacklogs(int backlogs) { this.backlogs = backlogs; }

    public double getTestScore() { return testScore; }
    public void setTestScore(double testScore) { this.testScore = testScore; }

    // Polymorphism: Overriding toString()
    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", CGPA=" + cgpa + ", Skills=" + skills + ", Branch=" + branch + ", Backlogs=" + backlogs + ", Test Score=" + testScore + "]";
    }
}
