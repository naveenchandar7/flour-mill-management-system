package org.example.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import org.example.DatabaseConnection;

import java.sql.*;

public class ReportUI {

    public void show() {

        Stage stage = new Stage();

        // =========================
        // TITLE
        // =========================

        Label title = new Label("▥ Reports");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        Label subtitle = new Label(
                "Flour Mill Management - Business Reports"
        );

        subtitle.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-text-fill: #666666;"
        );

        // =========================
        // SUMMARY CARDS
        // =========================

        Label customerValue = new Label();
        Label billValue = new Label();
        Label salesValue = new Label();

        customerValue.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        billValue.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        salesValue.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        VBox customerCard =
                createCard("Customers", customerValue);

        VBox billCard =
                createCard("Total Bills", billValue);

        VBox salesCard =
                createCard("Total Sales", salesValue);

        HBox cards = new HBox(20);

        cards.getChildren().addAll(
                customerCard,
                billCard,
                salesCard
        );

        // =========================
        // FILTER BUTTONS
        // =========================

        Button todayButton =
                new Button("Today");

        Button monthButton =
                new Button("This Month");

        Button allButton =
                new Button("All Records");

        Button refreshButton =
                new Button("Refresh");

        HBox filters = new HBox(10);

        filters.getChildren().addAll(
                todayButton,
                monthButton,
                allButton,
                refreshButton
        );

        // =========================
        // BILL TABLE
        // =========================

        TableView<BillReport> billTable =
                new TableView<>();

        TableColumn<BillReport, String> idColumn =
                new TableColumn<>("Bill ID");

        TableColumn<BillReport, String> dateColumn =
                new TableColumn<>("Date");

        TableColumn<BillReport, String> customerColumn =
                new TableColumn<>("Customer");

        TableColumn<BillReport, String> serviceColumn =
                new TableColumn<>("Service");

        TableColumn<BillReport, String> quantityColumn =
                new TableColumn<>("Quantity");

        TableColumn<BillReport, String> rateColumn =
                new TableColumn<>("Rate");

        TableColumn<BillReport, String> amountColumn =
                new TableColumn<>("Amount");

        idColumn.setCellValueFactory(
                data -> data.getValue().billIdProperty()
        );

        dateColumn.setCellValueFactory(
                data -> data.getValue().dateProperty()
        );

        customerColumn.setCellValueFactory(
                data -> data.getValue().customerProperty()
        );

        serviceColumn.setCellValueFactory(
                data -> data.getValue().serviceProperty()
        );

        quantityColumn.setCellValueFactory(
                data -> data.getValue().quantityProperty()
        );

        rateColumn.setCellValueFactory(
                data -> data.getValue().rateProperty()
        );

        amountColumn.setCellValueFactory(
                data -> data.getValue().amountProperty()
        );

        billTable.getColumns().addAll(
                idColumn,
                dateColumn,
                customerColumn,
                serviceColumn,
                quantityColumn,
                rateColumn,
                amountColumn
        );

        billTable.setPrefHeight(300);

        // =========================
        // ELECTRICITY SUMMARY
        // =========================

        Label electricityTitle =
                new Label("Electricity Summary");

        electricityTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Label unitsLabel =
                new Label("Total Units: 0");

        Label electricityCostLabel =
                new Label("Total Electricity Cost: ₹0.00");

        VBox electricityBox =
                new VBox(10);

        electricityBox.setPadding(
                new Insets(10)
        );

        electricityBox.getChildren().addAll(
                electricityTitle,
                unitsLabel,
                electricityCostLabel
        );

        // =========================
        // LOAD DATA
        // =========================

        Runnable loadAll =
                () -> loadReport(
                        "ALL",
                        customerValue,
                        billValue,
                        salesValue,
                        billTable,
                        unitsLabel,
                        electricityCostLabel
                );

        Runnable loadToday =
                () -> loadReport(
                        "TODAY",
                        customerValue,
                        billValue,
                        salesValue,
                        billTable,
                        unitsLabel,
                        electricityCostLabel
                );

        Runnable loadMonth =
                () -> loadReport(
                        "MONTH",
                        customerValue,
                        billValue,
                        salesValue,
                        billTable,
                        unitsLabel,
                        electricityCostLabel
                );

        todayButton.setOnAction(
                event -> loadToday.run()
        );

        monthButton.setOnAction(
                event -> loadMonth.run()
        );

        allButton.setOnAction(
                event -> loadAll.run()
        );

        refreshButton.setOnAction(
                event -> loadAll.run()
        );

        // =========================
        // MAIN LAYOUT
        // =========================

        VBox layout = new VBox(20);

        layout.setPadding(
                new Insets(30)
        );

        layout.getChildren().addAll(
                title,
                subtitle,
                cards,
                filters,
                billTable,
                electricityBox
        );

        Scene scene =
                new Scene(layout, 1000, 700);

        stage.setTitle(
                "Flour Mill Management - Reports"
        );

        stage.setScene(scene);

        stage.show();

        // Load initially
        loadAll.run();
    }


    // =====================================================
    // CARD
    // =====================================================

    private VBox createCard(
            String title,
            Label value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-text-fill: #666666;"
        );

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefWidth(250);
        card.setPrefHeight(100);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #dddddd;" +
                        "-fx-border-radius: 10;"
        );

        card.getChildren().addAll(
                titleLabel,
                value
        );

        return card;
    }


    // =====================================================
    // LOAD REPORT
    // =====================================================

    private void loadReport(
            String filter,
            Label customerValue,
            Label billValue,
            Label salesValue,
            TableView<BillReport> billTable,
            Label unitsLabel,
            Label electricityCostLabel
    ) {

        ObservableList<BillReport> data =
                FXCollections.observableArrayList();

        String dateCondition = "";

        if (filter.equals("TODAY")) {

            dateCondition =
                    " WHERE DATE(b.bill_date) = CURDATE() ";

        } else if (filter.equals("MONTH")) {

            dateCondition =
                    " WHERE YEAR(b.bill_date) = YEAR(CURDATE()) " +
                            "AND MONTH(b.bill_date) = MONTH(CURDATE()) ";

        }

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            // =========================
            // CUSTOMER COUNT
            // =========================

            String customerSql =
                    "SELECT COUNT(*) FROM customer";

            PreparedStatement customerStatement =
                    connection.prepareStatement(customerSql);

            ResultSet customerResult =
                    customerStatement.executeQuery();

            if (customerResult.next()) {

                customerValue.setText(
                        String.valueOf(
                                customerResult.getInt(1)
                        )
                );
            }

            // =========================
            // BILL + SALES
            // =========================

            String billSql =
                    "SELECT COUNT(*), COALESCE(SUM(total_amount),0) " +
                            "FROM bill b " +
                            dateCondition;

            PreparedStatement billStatement =
                    connection.prepareStatement(billSql);

            ResultSet billResult =
                    billStatement.executeQuery();

            if (billResult.next()) {

                billValue.setText(
                        String.valueOf(
                                billResult.getInt(1)
                        )
                );

                salesValue.setText(
                        String.format(
                                "₹%.2f",
                                billResult.getDouble(2)
                        )
                );
            }

            // =========================
            // BILL HISTORY
            // =========================

            String billHistorySql =
                    "SELECT " +
                            "b.bill_id, " +
                            "b.bill_date, " +
                            "c.customer_name, " +
                            "s.service_name, " +
                            "bi.quantity, " +
                            "bi.rate, " +
                            "bi.amount " +

                            "FROM bill b " +

                            "JOIN customer c " +
                            "ON b.customer_id = c.customer_id " +

                            "JOIN bill_item bi " +
                            "ON b.bill_id = bi.bill_id " +

                            "JOIN service s " +
                            "ON bi.service_id = s.service_id " +

                            dateCondition +

                            " ORDER BY b.bill_id DESC";

            PreparedStatement historyStatement =
                    connection.prepareStatement(
                            billHistorySql
                    );

            ResultSet historyResult =
                    historyStatement.executeQuery();

            while (historyResult.next()) {

                data.add(
                        new BillReport(
                                String.valueOf(
                                        historyResult.getInt(
                                                "bill_id"
                                        )
                                ),

                                historyResult.getString(
                                        "bill_date"
                                ),

                                historyResult.getString(
                                        "customer_name"
                                ),

                                historyResult.getString(
                                        "service_name"
                                ),

                                String.valueOf(
                                        historyResult.getDouble(
                                                "quantity"
                                        )
                                ),

                                String.format(
                                        "₹%.2f",
                                        historyResult.getDouble(
                                                "rate"
                                        )
                                ),

                                String.format(
                                        "₹%.2f",
                                        historyResult.getDouble(
                                                "amount"
                                        )
                                )
                        )
                );
            }

            billTable.setItems(data);

            // =========================
            // ELECTRICITY
            // =========================

            String electricitySql =
                    "SELECT " +
                            "COALESCE(SUM(units_used),0), " +
                            "COALESCE(SUM(electricity_cost),0) " +
                            "FROM electricity_usage";

            PreparedStatement electricityStatement =
                    connection.prepareStatement(
                            electricitySql
                    );

            ResultSet electricityResult =
                    electricityStatement.executeQuery();

            if (electricityResult.next()) {

                unitsLabel.setText(
                        String.format(
                                "Total Units: %.2f",
                                electricityResult.getDouble(1)
                        )
                );

                electricityCostLabel.setText(
                        String.format(
                                "Total Electricity Cost: ₹%.2f",
                                electricityResult.getDouble(2)
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // BILL REPORT MODEL
    // =====================================================

    public static class BillReport {

        private final javafx.beans.property.SimpleStringProperty billId;
        private final javafx.beans.property.SimpleStringProperty date;
        private final javafx.beans.property.SimpleStringProperty customer;
        private final javafx.beans.property.SimpleStringProperty service;
        private final javafx.beans.property.SimpleStringProperty quantity;
        private final javafx.beans.property.SimpleStringProperty rate;
        private final javafx.beans.property.SimpleStringProperty amount;

        public BillReport(
                String billId,
                String date,
                String customer,
                String service,
                String quantity,
                String rate,
                String amount
        ) {

            this.billId =
                    new javafx.beans.property.SimpleStringProperty(
                            billId
                    );

            this.date =
                    new javafx.beans.property.SimpleStringProperty(
                            date
                    );

            this.customer =
                    new javafx.beans.property.SimpleStringProperty(
                            customer
                    );

            this.service =
                    new javafx.beans.property.SimpleStringProperty(
                            service
                    );

            this.quantity =
                    new javafx.beans.property.SimpleStringProperty(
                            quantity
                    );

            this.rate =
                    new javafx.beans.property.SimpleStringProperty(
                            rate
                    );

            this.amount =
                    new javafx.beans.property.SimpleStringProperty(
                            amount
                    );
        }

        public javafx.beans.property.SimpleStringProperty billIdProperty() {
            return billId;
        }

        public javafx.beans.property.SimpleStringProperty dateProperty() {
            return date;
        }

        public javafx.beans.property.SimpleStringProperty customerProperty() {
            return customer;
        }

        public javafx.beans.property.SimpleStringProperty serviceProperty() {
            return service;
        }

        public javafx.beans.property.SimpleStringProperty quantityProperty() {
            return quantity;
        }

        public javafx.beans.property.SimpleStringProperty rateProperty() {
            return rate;
        }

        public javafx.beans.property.SimpleStringProperty amountProperty() {
            return amount;
        }
    }
}