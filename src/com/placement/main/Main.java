package com.placement.main;

import com.placement.model.Company;
import com.placement.model.Student;
import com.placement.service.PlacementService;
import com.placement.util.LoaderThread;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" University Placement Automation System");
        System.out.println("==========================================");

        // Multithreading: Start background data loader
        LoaderThread loader = new LoaderThread();
        loader.start();

        PlacementService service = new PlacementService();
        Scanner scanner = new Scanner(System.in);

        // Wait for loader to finish (optional, just for demo flow)
        try {
            loader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Register Student");
            System.out.println("2. Register Company");
            System.out.println("3. Generate & View Shortlists");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter CGPA: ");
                    double cgpa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Skills: ");
                    String skills = scanner.nextLine();
                    
                    // Auto-generate username for demo
                    String username = name.toLowerCase().replaceAll("\\s+", "") + (int)(Math.random()*1000);
                    
                    Student s = new Student(0, username, "pass", name, email, cgpa, skills);
                    service.registerStudent(s);
                    break;
                case 2:
                    System.out.print("Enter Company Name: ");
                    String cName = scanner.nextLine();
                    System.out.print("Enter Min CGPA: ");
                    double minCgpa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Required Skills: ");
                    String reqSkills = scanner.nextLine();
                    
                    Company c = new Company(0, cName, minCgpa, reqSkills);
                    service.registerCompany(c);
                    break;
                case 3:
                    service.generateShortlists();
                    service.displayShortlists();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
