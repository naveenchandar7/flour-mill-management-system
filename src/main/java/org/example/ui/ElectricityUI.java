package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.dao.ElectricityUsageDAO;
import org.example.model.ElectricityUsage;

import java.time.LocalDate;

public class ElectricityUI {

    public void show() {

        Stage stage = new Stage();

        // -----------------------------
        // Title
        // -----------------------------

        Label title = new Label("Electricity Usage");

        // -----------------------------
        // Date
        // -----------------------------

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        // -----------------------------
        // Input Fields
        // -----------------------------

        TextField startUnitField = new TextField();
        startUnitField.setPromptText("Start Unit");

        TextField endUnitField = new TextField();
        endUnitField.setPromptText("End Unit");

        TextField rateField = new TextField();
        rateField.setPromptText("Rate per Unit");

        // -----------------------------
        // Result Labels
        // -----------------------------

        Label unitsLabel =
                new Label("Units Used: 0");

        Label costLabel =
                new Label("Electricity Cost: ₹0.00");

        // -----------------------------
        // Buttons
        // -----------------------------

        Button calculateButton =
                new Button("Calculate");

        Button saveButton =
                new Button("Save");

        // -----------------------------
        // Calculate Button
        // -----------------------------

        calculateButton.setOnAction(event -> {

            try {

                double startUnit =
                        Double.parseDouble(
                                startUnitField.getText()
                        );

                double endUnit =
                        Double.parseDouble(
                                endUnitField.getText()
                        );

                double rate =
                        Double.parseDouble(
                                rateField.getText()
                        );

                double unitsUsed =
                        endUnit - startUnit;

                double electricityCost =
                        unitsUsed * rate;

                unitsLabel.setText(
                        "Units Used: " + unitsUsed
                );

                costLabel.setText(
                        String.format(
                                "Electricity Cost: ₹%.2f",
                                electricityCost
                        )
                );

            } catch (NumberFormatException e) {

                showError(
                        "Please enter valid numbers."
                );
            }
        });

        // -----------------------------
        // Save Button
        // -----------------------------

        saveButton.setOnAction(event -> {

            try {

                double startUnit =
                        Double.parseDouble(
                                startUnitField.getText()
                        );

                double endUnit =
                        Double.parseDouble(
                                endUnitField.getText()
                        );

                double rate =
                        Double.parseDouble(
                                rateField.getText()
                        );

                double unitsUsed =
                        endUnit - startUnit;

                double electricityCost =
                        unitsUsed * rate;

                // DatePicker gives LocalDate.
                // ElectricityUsage needs java.sql.Date.
                java.sql.Date usageDate =
                        java.sql.Date.valueOf(
                                datePicker.getValue()
                        );

                ElectricityUsage usage =
                        new ElectricityUsage(
                                0,
                                usageDate,
                                startUnit,
                                endUnit,
                                unitsUsed,
                                rate,
                                electricityCost,
                                null
                        );

                ElectricityUsageDAO dao =
                        new ElectricityUsageDAO();

                dao.addUsage(usage);

                showInfo(
                        "Electricity usage saved successfully."
                );

            } catch (NumberFormatException e) {

                showError(
                        "Please enter valid numbers."
                );

            } catch (Exception e) {

                e.printStackTrace();

                showError(
                        "Something went wrong."
                );
            }
        });

        // -----------------------------
        // Form
        // -----------------------------

        GridPane form =
                new GridPane();

        form.setHgap(10);
        form.setVgap(10);

        form.add(
                new Label("Date"),
                0,
                0
        );

        form.add(
                datePicker,
                1,
                0
        );

        form.add(
                new Label("Start Unit"),
                0,
                1
        );

        form.add(
                startUnitField,
                1,
                1
        );

        form.add(
                new Label("End Unit"),
                0,
                2
        );

        form.add(
                endUnitField,
                1,
                2
        );

        form.add(
                new Label("Rate / Unit"),
                0,
                3
        );

        form.add(
                rateField,
                1,
                3
        );

        form.add(
                calculateButton,
                0,
                4
        );

        form.add(
                saveButton,
                1,
                4
        );

        // -----------------------------
        // Main Layout
        // -----------------------------

        VBox layout =
                new VBox(20);

        layout.setPadding(
                new Insets(30)
        );

        layout.getChildren().addAll(
                title,
                form,
                unitsLabel,
                costLabel
        );

        // -----------------------------
        // Scene
        // -----------------------------

        Scene scene =
                new Scene(
                        layout,
                        500,
                        450
                );

        stage.setTitle(
                "Electricity Usage"
        );

        stage.setScene(scene);

        stage.show();
    }

    // -----------------------------
    // Error Alert
    // -----------------------------

    private void showError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle("Error");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // -----------------------------
    // Information Alert
    // -----------------------------

    private void showInfo(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Information");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}