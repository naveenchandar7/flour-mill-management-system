package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.dao.CustomerDAO;
import org.example.dao.BillDAO;
import org.example.dao.ElectricityUsageDAO;

public class Dashboard {

    public void show() {

        // =========================
        // GET REAL DATA FROM DATABASE
        // =========================

        CustomerDAO customerDAO =
                new CustomerDAO();

        BillDAO billDAO =
                new BillDAO();

        ElectricityUsageDAO electricityDAO =
                new ElectricityUsageDAO();


        int customerCount =
                customerDAO.getCustomerCount();

        int billCount =
                billDAO.getBillCount();

        double electricityCost =
                electricityDAO.getTotalElectricityCost();


        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("🌾 Flour Mill Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1f2937;"
        );


        Label subtitle =
                new Label("Management Dashboard");

        subtitle.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-text-fill: #64748b;"
        );


        // =========================
        // DASHBOARD CARDS
        // =========================

        VBox customerCard =
                createCard(
                        "Customers",
                        String.valueOf(customerCount)
                );


        VBox billCard =
                createCard(
                        "Total Bills",
                        String.valueOf(billCount)
                );


        VBox electricityCard =
                createCard(
                        "Electricity Cost",
                        String.format(
                                "₹%.2f",
                                electricityCost
                        )
                );


        HBox cards =
                new HBox(25);

        cards.getChildren().addAll(
                customerCard,
                billCard,
                electricityCard
        );


        // =========================
        // BUTTONS
        // =========================

        Button customerButton =
                createButton("Customers");

        Button billingButton =
                createButton("Billing");

        Button electricityButton =
                createButton("Electricity");

        Button billHistoryButton =
                createButton("Bill History");

        Button reportButton =
                createButton("Reports");

        Button logoutButton =
                createButton("Logout");


        // =========================
        // CUSTOMER
        // =========================

        customerButton.setOnAction(event -> {

            CustomerUI customerUI =
                    new CustomerUI();

            customerUI.show();
        });


        // =========================
        // BILLING
        // =========================

        billingButton.setOnAction(event -> {

            BillingUI billingUI =
                    new BillingUI();

            billingUI.show();
        });


        // =========================
        // ELECTRICITY
        // =========================

        electricityButton.setOnAction(event -> {

            ElectricityUI electricityUI =
                    new ElectricityUI();

            electricityUI.show();
        });


        // =========================
        // BILL HISTORY
        // =========================

        billHistoryButton.setOnAction(event -> {

            BillHistoryUI billHistoryUI =
                    new BillHistoryUI();

            billHistoryUI.show();
        });


        // =========================
        // REPORTS
        // =========================

        reportButton.setOnAction(event -> {

            ReportUI reportUI =
                    new ReportUI();

            reportUI.show();
        });


        // =========================
        // LOGOUT
        // =========================

        logoutButton.setOnAction(event -> {

            Stage currentStage =
                    (Stage) logoutButton
                            .getScene()
                            .getWindow();

            currentStage.close();

            Login login = new Login();

            try {
                Stage loginStage = new Stage();
                login.start(loginStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // =========================
        // MENU
        // =========================

        GridPane menu =
                new GridPane();

        menu.setHgap(20);
        menu.setVgap(20);


        menu.add(
                customerButton,
                0, 0
        );

        menu.add(
                billingButton,
                1, 0
        );

        menu.add(
                electricityButton,
                2, 0
        );


        menu.add(
                billHistoryButton,
                0, 1
        );

        menu.add(
                reportButton,
                1, 1
        );

        menu.add(
                logoutButton,
                2, 1
        );


        // =========================
        // MAIN LAYOUT
        // =========================

        VBox layout =
                new VBox(25);

        layout.setPadding(
                new Insets(40)
        );

        layout.setAlignment(
                Pos.TOP_LEFT
        );

        layout.getChildren().addAll(
                title,
                subtitle,
                cards,
                menu
        );


        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        layout,
                        1000,
                        650
                );


        // =========================
        // STAGE
        // =========================

        Stage stage =
                new Stage();

        stage.setTitle(
                "Flour Mill Management"
        );

        stage.setScene(scene);

        // Allow maximize


        stage.show();
    }


    // =========================
    // CREATE CARD
    // =========================

    private VBox createCard(
            String name,
            String value
    ) {

        Label nameLabel =
                new Label(name);

        nameLabel.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: #64748b;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1e293b;"
        );

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefWidth(250);
        card.setPrefHeight(120);

        card.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: #dbe3ee;" +
                        "-fx-border-radius: 12;"
        );

        card.getChildren().addAll(
                nameLabel,
                valueLabel
        );

        return card;
    }


    // =========================
    // CREATE BUTTON
    // =========================

    private Button createButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(200);
        button.setPrefHeight(50);

        button.setStyle(
                "-fx-background-color: #2563eb;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );

        return button;
    }
    }

//$env:JAVA_HOME="C:\Program Files\Java\jdk-21.0.12"
//echo $env:JAVA_HOME