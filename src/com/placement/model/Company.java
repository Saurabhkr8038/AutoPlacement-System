package com.placement.model;

public class Company {
    private int id;
    private String name;
    private double minCgpa;
    private String requiredSkills;

    public Company(int id, String name, double minCgpa, String requiredSkills) {
        this.id = id;
        this.name = name;
        this.minCgpa = minCgpa;
        this.requiredSkills = requiredSkills;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMinCgpa() { return minCgpa; }
    public String getRequiredSkills() { return requiredSkills; }

    @Override
    public String toString() {
        return "Company: " + name + " (Min CGPA: " + minCgpa + ")";
    }
}
