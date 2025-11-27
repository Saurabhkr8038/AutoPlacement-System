package com.placement.dao;

import com.placement.model.Company;
import com.placement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    private Connection conn;

    public CompanyDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public void addCompany(Company company) {
        String sql = "INSERT INTO companies (name, min_cgpa, required_skills) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, company.getName());
            pstmt.setDouble(2, company.getMinCgpa());
            pstmt.setString(3, company.getRequiredSkills());
            pstmt.executeUpdate();
            System.out.println("Company added: " + company.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM companies";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                companies.add(new Company(
                    rs.getInt("company_id"),
                    rs.getString("name"),
                    rs.getDouble("min_cgpa"),
                    rs.getString("required_skills")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
