package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.dao.BillHistoryDAO;

import java.sql.ResultSet;

public class BillHistoryUI {

    public void show() {

        Stage stage = new Stage();

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("🧾 Bill History");

        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================
        // TABLE
        // =========================

        TableView<BillRecord> table =
                new TableView<>();


        // Bill ID

        TableColumn<BillRecord, Integer> billIdColumn =
                new TableColumn<>("Bill ID");

        billIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("billId")
        );


        // Date

        TableColumn<BillRecord, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );


        // Customer

        TableColumn<BillRecord, String> customerColumn =
                new TableColumn<>("Customer");

        customerColumn.setCellValueFactory(
                new PropertyValueFactory<>("customer")
        );


        // Service

        TableColumn<BillRecord, String> serviceColumn =
                new TableColumn<>("Service");

        serviceColumn.setCellValueFactory(
                new PropertyValueFactory<>("service")
        );


        // Quantity

        TableColumn<BillRecord, Double> quantityColumn =
                new TableColumn<>("Quantity");

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );


        // Rate

        TableColumn<BillRecord, Double> rateColumn =
                new TableColumn<>("Rate");

        rateColumn.setCellValueFactory(
                new PropertyValueFactory<>("rate")
        );


        // Amount

        TableColumn<BillRecord, Double> amountColumn =
                new TableColumn<>("Amount");

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );


        table.getColumns().addAll(
                billIdColumn,
                dateColumn,
                customerColumn,
                serviceColumn,
                quantityColumn,
                rateColumn,
                amountColumn
        );


        // =========================
        // LOAD DATA
        // =========================

        loadBills(table);


        // =========================
        // REFRESH BUTTON
        // =========================

        Button refreshButton =
                new Button("Refresh");

        refreshButton.setOnAction(event -> {

            loadBills(table);

        });


        // =========================
        // LAYOUT
        // =========================

        VBox layout =
                new VBox(20);

        layout.setPadding(
                new Insets(30)
        );

        layout.getChildren().addAll(
                title,
                refreshButton,
                table
        );


        Scene scene =
                new Scene(
                        layout,
                        950,
                        600
                );


        stage.setTitle(
                "Flour Mill Management - Bill History"
        );

        stage.setScene(scene);

        stage.show();
    }


    // =========================
    // LOAD BILLS
    // =========================

    private void loadBills(
            TableView<BillRecord> table
    ) {

        table.getItems().clear();

        BillHistoryDAO dao =
                new BillHistoryDAO();

        try {

            ResultSet resultSet =
                    dao.getAllBillHistory();

            if (resultSet == null) {
                return;
            }


            while (resultSet.next()) {

                BillRecord record =
                        new BillRecord(

                                resultSet.getInt(
                                        "bill_id"
                                ),

                                resultSet.getString(
                                        "bill_date"
                                ),

                                resultSet.getString(
                                        "customer_name"
                                ),

                                resultSet.getString(
                                        "service_name"
                                ),

                                resultSet.getDouble(
                                        "quantity"
                                ),

                                resultSet.getDouble(
                                        "rate"
                                ),

                                resultSet.getDouble(
                                        "amount"
                                )
                        );


                table.getItems().add(
                        record
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    // =========================
    // BILL RECORD
    // =========================

    public static class BillRecord {

        private int billId;

        private String date;

        private String customer;

        private String service;

        private double quantity;

        private double rate;

        private double amount;


        public BillRecord(
                int billId,
                String date,
                String customer,
                String service,
                double quantity,
                double rate,
                double amount
        ) {

            this.billId = billId;
            this.date = date;
            this.customer = customer;
            this.service = service;
            this.quantity = quantity;
            this.rate = rate;
            this.amount = amount;
        }


        public int getBillId() {
            return billId;
        }

        public String getDate() {
            return date;
        }

        public String getCustomer() {
            return customer;
        }

        public String getService() {
            return service;
        }

        public double getQuantity() {
            return quantity;
        }

        public double getRate() {
            return rate;
        }

        public double getAmount() {
            return amount;
        }
    }
}