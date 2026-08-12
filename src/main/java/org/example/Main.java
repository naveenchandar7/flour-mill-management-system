package org.example;

import java.sql.Connection;
import org.example.dao.CustomerDAO;
import org.example.model.Customer;


public class Main {
    public static void main(String[] args) {

        Customer customer=new Customer(
                1,
                "Mathi",
                "Thanipadi",
                "1234567891"
        );
        CustomerDAO customerDAO=new CustomerDAO();
        //customerDAO.addCustomer(customer);
        //System.out.println("Customer added successfully");
        //customerDAO.updateCustomer(customer);
        customerDAO.deleteCustomer(2);
        //System.out.println("Customer updated successfully");
        customerDAO.getAllCustomers();


        try {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Database connected successfully!");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}