package com.placement.ui;

import com.placement.model.Student;
import com.placement.service.PlacementService;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DashboardView {
    private PlacementService service;

    public DashboardView() {
        this.service = new PlacementService();
    }

    public Parent getView() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(createStudentTab());
        tabPane.getTabs().add(createShortlistTab());

        borderPane.setCenter(tabPane);
        return borderPane;
    }

    private Tab createStudentTab() {
        Tab tab = new Tab("Register Student");
        tab.setClosable(false);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField cgpaField = new TextField();
        TextField skillsField = new TextField();
        TextField branchField = new TextField();
        TextField backlogsField = new TextField();
        TextField testScoreField = new TextField();

        grid.add(new Label("Name:"), 0, 0); grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1); grid.add(emailField, 1, 1);
        grid.add(new Label("CGPA:"), 0, 2); grid.add(cgpaField, 1, 2);
        grid.add(new Label("Skills:"), 0, 3); grid.add(skillsField, 1, 3);
        grid.add(new Label("Branch:"), 0, 4); grid.add(branchField, 1, 4);
        grid.add(new Label("Backlogs:"), 0, 5); grid.add(backlogsField, 1, 5);
        grid.add(new Label("Test Score:"), 0, 6); grid.add(testScoreField, 1, 6);

        Button btn = new Button("Register");
        btn.setOnAction(e -> {
            try {
                Student s = new Student(0, nameField.getText(), "pass", nameField.getText(), 
                    emailField.getText(), Double.parseDouble(cgpaField.getText()), skillsField.getText(),
                    branchField.getText(), Integer.parseInt(backlogsField.getText()), Double.parseDouble(testScoreField.getText()));
                service.registerStudent(s);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student Registered!");
                alert.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage());
                alert.show();
            }
        });
        grid.add(btn, 1, 7);

        tab.setContent(grid);
        return tab;
    }

    private Tab createShortlistTab() {
        Tab tab = new Tab("Smart Shortlist");
        tab.setClosable(false);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField minScoreField = new TextField("50");
        TextField cgpaWeightField = new TextField("10");
        TextField testWeightField = new TextField("0.5");
        TextField backlogPenaltyField = new TextField("5");
        TextField branchField = new TextField("CSE");

        grid.add(new Label("Min Score:"), 0, 0); grid.add(minScoreField, 1, 0);
        grid.add(new Label("CGPA Weight:"), 0, 1); grid.add(cgpaWeightField, 1, 1);
        grid.add(new Label("Test Weight:"), 0, 2); grid.add(testWeightField, 1, 2);
        grid.add(new Label("Backlog Penalty:"), 0, 3); grid.add(backlogPenaltyField, 1, 3);
        grid.add(new Label("Required Branch:"), 0, 4); grid.add(branchField, 1, 4);

        Button btn = new Button("Generate Shortlist");
        TextArea outputArea = new TextArea();
        
        btn.setOnAction(e -> {
            try {
                service.generateSmartShortlists(
                    Double.parseDouble(minScoreField.getText()),
                    Double.parseDouble(cgpaWeightField.getText()),
                    Double.parseDouble(testWeightField.getText()),
                    Double.parseDouble(backlogPenaltyField.getText()),
                    branchField.getText()
                );
                outputArea.setText("Shortlist generated! Check console for details (or implement table view).");
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        vbox.getChildren().addAll(grid, btn, outputArea);
        tab.setContent(vbox);
        return tab;
    }
}
