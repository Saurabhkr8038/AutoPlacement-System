package com.placement.dao;

import com.placement.model.Student;
import com.placement.service.PlacementOperations;
import com.placement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements PlacementOperations {
    private Connection conn;

    public StudentDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public void addStudent(Student student) {
        String userSql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        String studentSql = "INSERT INTO students (student_id, name, email, cgpa, skills, branch, backlogs, test_score) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn.setAutoCommit(false); // Transaction management
            
            PreparedStatement userStmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, student.getUsername());
            userStmt.setString(2, "password123"); // Default password
            userStmt.setString(3, "STUDENT");
            userStmt.executeUpdate();
            
            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            PreparedStatement studentStmt = conn.prepareStatement(studentSql);
            studentStmt.setInt(1, userId);
            studentStmt.setString(2, student.getName());
            studentStmt.setString(3, student.getEmail());
            studentStmt.setDouble(4, student.getCgpa());
            studentStmt.setString(5, student.getSkills());
            studentStmt.setString(6, student.getBranch());
            studentStmt.setInt(7, student.getBacklogs());
            studentStmt.setDouble(8, student.getTestScore());
            studentStmt.executeUpdate();

            conn.commit();
            System.out.println("Student added successfully: " + student.getName());

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT u.id, u.username, s.name, s.email, s.cgpa, s.skills, s.branch, s.backlogs, s.test_score FROM users u JOIN students s ON u.id = s.student_id";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("username"),
                    "", // Password hidden
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("cgpa"),
                    rs.getString("skills"),
                    rs.getString("branch"),
                    rs.getInt("backlogs"),
                    rs.getDouble("test_score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> shortlistCandidates(double minCgpa) {
        List<Student> shortlisted = new ArrayList<>();
        String sql = "SELECT u.id, u.username, s.name, s.email, s.cgpa, s.skills, s.branch, s.backlogs, s.test_score FROM users u JOIN students s ON u.id = s.student_id WHERE s.cgpa >= ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minCgpa);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                shortlisted.add(new Student(
                    rs.getInt("id"),
                    rs.getString("username"),
                    "",
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("cgpa"),
                    rs.getString("skills"),
                    rs.getString("branch"),
                    rs.getInt("backlogs"),
                    rs.getDouble("test_score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shortlisted;
    }
}
