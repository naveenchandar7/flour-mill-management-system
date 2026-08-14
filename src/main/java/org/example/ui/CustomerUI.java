package org.example.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;

public class CustomerUI {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public void show() {

        Label title = new Label("Customer Management");

        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");

        TextField villageField = new TextField();
        villageField.setPromptText("Village");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile");

        // Buttons
        Button addButton = new Button("Add Customer");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button refreshButton = new Button("Refresh");

        // Table
        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, Integer> idColumn =
                new TableColumn<>("ID");

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Name");

        TableColumn<Customer, String> villageColumn =
                new TableColumn<>("Village");

        TableColumn<Customer, String> mobileColumn =
                new TableColumn<>("Mobile");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerId")
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerName")
        );

        villageColumn.setCellValueFactory(
                new PropertyValueFactory<>("village")
        );

        mobileColumn.setCellValueFactory(
                new PropertyValueFactory<>("mobile")
        );

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                villageColumn,
                mobileColumn
        );

        table.setPrefHeight(300);

        // Add Customer
        addButton.setOnAction(event -> {

            Customer customer = new Customer(
                    0,
                    nameField.getText(),
                    villageField.getText(),
                    mobileField.getText()
            );

            customerDAO.addCustomer(customer);

            nameField.clear();
            villageField.clear();
            mobileField.clear();

            loadCustomers(table);
        });

        // Select table row
        table.setOnMouseClicked(event -> {

            Customer selected =
                    table.getSelectionModel().getSelectedItem();

            if (selected != null) {

                nameField.setText(selected.getCustomerName());
                villageField.setText(selected.getVillage());
                mobileField.setText(selected.getMobile());
            }
        });

        // Update
        updateButton.setOnAction(event -> {

            Customer selected =
                    table.getSelectionModel().getSelectedItem();

            if (selected != null) {

                selected.setCustomerName(nameField.getText());
                selected.setVillage(villageField.getText());
                selected.setMobile(mobileField.getText());

                customerDAO.updateCustomer(selected);

                loadCustomers(table);
            }
        });

        // Delete
        deleteButton.setOnAction(event -> {

            Customer selected =
                    table.getSelectionModel().getSelectedItem();

            if (selected != null) {

                customerDAO.deleteCustomer(
                        selected.getCustomerId()
                );

                loadCustomers(table);
            }
        });

        // Refresh
        refreshButton.setOnAction(event -> {

            loadCustomers(table);

        });

        HBox buttons = new HBox(
                10,
                addButton,
                updateButton,
                deleteButton,
                refreshButton
        );

        VBox layout = new VBox(
                15,
                title,
                nameField,
                villageField,
                mobileField,
                buttons,
                table
        );

        layout.setPadding(new Insets(30));

        Scene scene = new Scene(
                layout,
                800,
                600
        );

        Stage stage = new Stage();

        stage.setTitle("Customer Management");

        stage.setScene(scene);

        stage.show();

        loadCustomers(table);
    }

    private void loadCustomers(TableView<Customer> table) {

        ObservableList<Customer> customerList =
                FXCollections.observableArrayList(
                        customerDAO.getAllCustomers()
                );

        table.setItems(customerList);
    }
}