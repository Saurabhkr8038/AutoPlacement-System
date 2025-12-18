package com.placement.service;

import com.placement.dao.CompanyDAO;
import com.placement.dao.StudentDAO;
import com.placement.model.Company;
import com.placement.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacementService {
    private StudentDAO studentDAO;
    private CompanyDAO companyDAO;
    private Map<String, List<Student>> companyShortlists; // HashMap usage

    public PlacementService() {
        this.studentDAO = new StudentDAO();
        this.companyDAO = new CompanyDAO();
        this.companyShortlists = new HashMap<>();
    }

    public void registerStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void registerCompany(Company company) {
        companyDAO.addCompany(company);
    }

    public void generateShortlists() {
        List<Company> companies = companyDAO.getAllCompanies();
        for (Company company : companies) {
            List<Student> eligibleStudents = studentDAO.shortlistCandidates(company.getMinCgpa());
            companyShortlists.put(company.getName(), eligibleStudents);
            System.out.println("Shortlisted " + eligibleStudents.size() + " students for " + company.getName());
        }
    }

    /**
     * Advanced Shortlisting Logic with Weighted Scoring
     * Score = (CGPA * cgpaWeight) + (TestScore * testWeight) - (Backlogs * backlogPenalty)
     */
    public void generateSmartShortlists(double minScore, double cgpaWeight, double testWeight, double backlogPenalty, String requiredBranch) {
        List<Company> companies = companyDAO.getAllCompanies();
        List<Student> allStudents = studentDAO.getAllStudents();

        for (Company company : companies) {
            List<Student> eligibleStudents = new ArrayList<>();
            for (Student student : allStudents) {
                // Branch check (if requiredBranch is specified)
                if (requiredBranch != null && !requiredBranch.isEmpty() && !requiredBranch.equalsIgnoreCase("All") && !student.getBranch().equalsIgnoreCase(requiredBranch)) {
                    continue;
                }

                // Weighted Score Calculation
                double score = (student.getCgpa() * cgpaWeight) + (student.getTestScore() * testWeight) - (student.getBacklogs() * backlogPenalty);
                
                // Check against minScore and Company's basic CGPA requirement
                if (score >= minScore && student.getCgpa() >= company.getMinCgpa()) {
                    eligibleStudents.add(student);
                }
            }
            companyShortlists.put(company.getName(), eligibleStudents);
            System.out.println("Smart Shortlisted " + eligibleStudents.size() + " students for " + company.getName());
        }
    }

    public void displayShortlists() {
        for (Map.Entry<String, List<Student>> entry : companyShortlists.entrySet()) {
            System.out.println("\nCompany: " + entry.getKey());
            for (Student s : entry.getValue()) {
                System.out.println(" - " + s.getName() + " (CGPA: " + s.getCgpa() + ", Branch: " + s.getBranch() + ", Score: " + s.getTestScore() + ")");
            }
        }
    }
}
