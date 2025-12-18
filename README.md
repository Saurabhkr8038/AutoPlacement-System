# University Placement Automation System

## Project Overview
The **University Placement Automation System** is a Java-based desktop application designed to streamline the recruitment process. It replaces manual data handling with an automated system for managing student profiles, company requirements, and shortlisting candidates based on eligibility criteria.

## Team Members
*   **Saurabh Kumar** (saurabh.24scse1180099@galgotiasuniversity.ac.in)
*   **Yashdeep Shukla** (Yashdeep.24scse1180255@galgotiasuniversity.ac.in)

## Features
*   **Student Management:** Add, Update, and Delete student profiles.
*   **Smart Filtering & Advanced Shortlisting:** Automatically shortlist students based on Company CGPA and advanced, weighted criteria (branch, backlogs, test scores, etc.).
*   **Database Integration:** Persistent storage using MySQL.
*   **Multithreading:** Background data loading for improved performance.
*   **Role-Based Access:** Admin and Student modules.
*   **Modern User Interface:** JavaFX-based, professional, and responsive UI for all modules.

## Technologies Used
*   **Language:** Java (Core, OOP, Collections, Multithreading)
*   **Database:** MySQL
*   **Connectivity:** JDBC
*   **Tools:** VS Code, Git, JavaFX

## Project Structure
*   `src/com/placement/model`: Entity classes (Student, Company, User).
*   `src/com/placement/dao`: Data Access Objects for database operations.
*   `src/com/placement/service`: Business logic for filtering and management.
*   `src/com/placement/util`: Utility classes (DB Connection, Threading).
*   `src/com/placement/main`: Legacy CLI entry point.
*   `src/com/placement/ui`: JavaFX UI classes (MainApp, LoginView, DashboardView).

## Setup Instructions
1.  **Database Setup:**
    *   Install MySQL Server.
    *   Run the script located in `resources/database.sql` to create the database and tables.
    *   Update `src/com/placement/util/DBConnection.java` with your MySQL username and password.

2.  **Compile and Run:**
    *   Open the project in VS Code or Eclipse.
    *   Ensure the MySQL JDBC driver and JavaFX SDK are added to the classpath/module-path.
    *   To launch the modern UI, run `src/com/placement/ui/MainApp.java`.
    *   The legacy CLI is still available via `src/com/placement/main/Main.java`.

## Requirements Fulfilled
*   **OOP Implementation:** Polymorphism, Inheritance, Encapsulation, Interfaces.
*   **Collections & Generics:** Used `ArrayList` and `HashMap`.
*   **Multithreading:** Implemented `LoaderThread`.
*   **Database Connectivity:** JDBC with `PreparedStatement`.
*   **Modern UI:** JavaFX-based, cross-platform, and visually appealing interface.
*   **Advanced Shortlisting:** Weighted scoring algorithm for smart candidate filtering.
