package com.placement.service;

import com.placement.model.Student;
import java.util.List;

public interface PlacementOperations {
    void addStudent(Student student);
    List<Student> getAllStudents();
    List<Student> shortlistCandidates(double minCgpa);
}
