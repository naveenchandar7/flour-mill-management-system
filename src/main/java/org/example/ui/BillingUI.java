package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import org.example.dao.BillDAO;
import org.example.dao.BillItemDAO;
import org.example.dao.CustomerDAO;
import org.example.dao.MillServiceDAO;

import org.example.model.Bill;
import org.example.model.BillItem;
import org.example.model.Customer;
import org.example.model.MillService;

public class BillingUI {

    public void show() {

        Stage stage = new Stage();

        // =========================
        // TITLE
        // =========================

        Label title = new Label("🧾 Create Bill");

        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================
        // CUSTOMER DROPDOWN
        // =========================

        ComboBox<Customer> customerComboBox =
                new ComboBox<>();

        customerComboBox.setPromptText(
                "Select Customer"
        );

        CustomerDAO customerDAO =
                new CustomerDAO();

        customerComboBox
                .getItems()
                .addAll(
                        customerDAO.getAllCustomers()
                );

        customerComboBox.setPrefWidth(300);

        customerComboBox.setConverter(
                new StringConverter<Customer>() {

                    @Override
                    public String toString(Customer customer) {

                        if (customer == null) {
                            return "";
                        }

                        return customer.getCustomerName()
                                + " - "
                                + customer.getMobile();
                    }

                    @Override
                    public Customer fromString(String string) {
                        return null;
                    }
                }
        );


        // =========================
        // SERVICE DROPDOWN
        // =========================

        ComboBox<MillService> serviceComboBox =
                new ComboBox<>();

        serviceComboBox.setPromptText(
                "Select Service"
        );

        MillServiceDAO serviceDAO =
                new MillServiceDAO();

        serviceComboBox
                .getItems()
                .addAll(
                        serviceDAO.getAllServices()
                );

        serviceComboBox.setPrefWidth(300);

        serviceComboBox.setConverter(
                new StringConverter<MillService>() {

                    @Override
                    public String toString(MillService service) {

                        if (service == null) {
                            return "";
                        }

                        return service.getServiceName();
                    }

                    @Override
                    public MillService fromString(String string) {
                        return null;
                    }
                }
        );


        // =========================
        // RATE
        // =========================

        TextField rateField =
                new TextField();

        rateField.setPromptText(
                "Rate per Kg"
        );

        rateField.setEditable(false);


        // =========================
        // QUANTITY
        // =========================

        TextField quantityField =
                new TextField();

        quantityField.setPromptText(
                "Quantity in Kg"
        );


        // =========================
        // AMOUNT
        // =========================

        Label amountLabel =
                new Label("Amount: ₹0.00");

        amountLabel.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================
        // SERVICE SELECTION
        // =========================

        serviceComboBox.setOnAction(event -> {

            MillService service =
                    serviceComboBox.getValue();

            if (service != null) {

                rateField.setText(
                        String.valueOf(
                                service.getRatePerKg()
                        )
                );
            }
        });


        // =========================
        // CALCULATE
        // =========================

        Button calculateButton =
                new Button("Calculate");

        calculateButton.setOnAction(event -> {

            try {

                if (serviceComboBox.getValue() == null) {

                    showError(
                            "Please select a service."
                    );

                    return;
                }

                double quantity =
                        Double.parseDouble(
                                quantityField.getText()
                        );

                double rate =
                        serviceComboBox
                                .getValue()
                                .getRatePerKg();

                double amount =
                        quantity * rate;

                amountLabel.setText(
                        String.format(
                                "Amount: ₹%.2f",
                                amount
                        )
                );

            } catch (NumberFormatException e) {

                showError(
                        "Please enter a valid quantity."
                );
            }
        });


        // =========================
        // CREATE BILL
        // =========================

        Button createBillButton =
                new Button("Create Bill");

        createBillButton.setOnAction(event -> {

            try {

                // CUSTOMER CHECK

                Customer customer =
                        customerComboBox.getValue();

                if (customer == null) {

                    showError(
                            "Please select a customer."
                    );

                    return;
                }


                // SERVICE CHECK

                MillService service =
                        serviceComboBox.getValue();

                if (service == null) {

                    showError(
                            "Please select a service."
                    );

                    return;
                }


                // QUANTITY

                double quantity =
                        Double.parseDouble(
                                quantityField.getText()
                        );


                // RATE

                double rate =
                        service.getRatePerKg();


                // AMOUNT

                double amount =
                        quantity * rate;


                // =========================
                // CREATE BILL
                // =========================

                Bill bill =
                        new Bill(
                                0,
                                customer.getCustomerId(),
                                amount
                        );

                BillDAO billDAO =
                        new BillDAO();

                int billId =
                        billDAO.addBill(bill);


                if (billId == -1) {

                    showError(
                            "Bill creation failed."
                    );

                    return;
                }


                // =========================
                // CREATE BILL ITEM
                // =========================

                BillItem billItem =
                        new BillItem(
                                0,
                                billId,
                                service.getServiceId(),
                                quantity,
                                rate,
                                amount
                        );

                BillItemDAO billItemDAO =
                        new BillItemDAO();

                billItemDAO.addBillItem(
                        billItem
                );


                // =========================
                // SUCCESS
                // =========================

                showInfo(
                        "Bill created successfully!\n\n" +
                                "Bill ID: " + billId +
                                "\nCustomer: " +
                                customer.getCustomerName() +
                                "\nService: " +
                                service.getServiceName() +
                                "\nQuantity: " +
                                quantity + " Kg" +
                                "\nRate: ₹" +
                                rate +
                                "\nTotal: ₹" +
                                String.format("%.2f", amount)
                );


                // CLEAR FORM

                customerComboBox.setValue(null);
                serviceComboBox.setValue(null);
                quantityField.clear();
                rateField.clear();

                amountLabel.setText(
                        "Amount: ₹0.00"
                );

            } catch (NumberFormatException e) {

                showError(
                        "Please enter a valid quantity."
                );

            } catch (Exception e) {

                e.printStackTrace();

                showError(
                        "Something went wrong."
                );
            }
        });


        // =========================
        // FORM
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);


        form.add(
                new Label("Customer"),
                0, 0
        );

        form.add(
                customerComboBox,
                1, 0
        );


        form.add(
                new Label("Service"),
                0, 1
        );

        form.add(
                serviceComboBox,
                1, 1
        );


        form.add(
                new Label("Rate / Kg"),
                0, 2
        );

        form.add(
                rateField,
                1, 2
        );


        form.add(
                new Label("Quantity / Kg"),
                0, 3
        );

        form.add(
                quantityField,
                1, 3
        );


        form.add(
                calculateButton,
                0, 4
        );

        form.add(
                createBillButton,
                1, 4
        );


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
                form,
                amountLabel
        );


        Scene scene =
                new Scene(
                        layout,
                        600,
                        500
                );


        stage.setTitle(
                "Flour Mill Management - Billing"
        );

        stage.setScene(scene);

        stage.show();
    }


    // =========================
    // ERROR MESSAGE
    // =========================

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


    // =========================
    // SUCCESS MESSAGE
    // =========================

    private void showInfo(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Bill Created");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}