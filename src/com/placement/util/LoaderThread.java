package com.placement.util;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;
import java.util.List;

public class LoaderThread extends Thread {
    private StudentDAO studentDAO;

    public LoaderThread() {
        this.studentDAO = new StudentDAO();
    }

    @Override
    public void run() {
        System.out.println("[LoaderThread] Background data loading started...");
        try {
            // Simulate heavy data fetching
            Thread.sleep(2000); 
            List<Student> students = studentDAO.getAllStudents();
            System.out.println("[LoaderThread] Data loaded successfully. Total students: " + students.size());
        } catch (InterruptedException e) {
            System.out.println("[LoaderThread] Interrupted.");
        }
    }
}
