package org.example.dao;

import org.example.model.Customer;
import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer) {

        String sql = "INSERT INTO customer(customer_name, village, mobile) VALUES(?,?,?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getVillage());
            statement.setString(3, customer.getMobile());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Customer> getAllCustomers() {

        String sql = "SELECT * FROM customer";

        List<Customer> customers = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("village"),
                        resultSet.getString("mobile")
                );

                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }


    public void updateCustomer(Customer customer) {

        String sql = "UPDATE customer SET customer_name=?, village=?, mobile=? WHERE customer_id=?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getVillage());
            statement.setString(3, customer.getMobile());
            statement.setInt(4, customer.getCustomerId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(int customerID) {

        String sql = "DELETE FROM customer WHERE customer_id=?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, customerID);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getCustomerCount() {

        String sql = "SELECT COUNT(*) FROM customer";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}