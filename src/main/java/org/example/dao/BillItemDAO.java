package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.BillItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillItemDAO {

    public void addBillItem(BillItem billItem) {

        String sql = "INSERT INTO bill_item(bill_id, service_id, quantity, rate, amount) VALUES(?,?,?,?,?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, billItem.getBillId());
            statement.setInt(2, billItem.getServiceId());
            statement.setDouble(3, billItem.getQuantity());
            statement.setDouble(4, billItem.getRate());
            statement.setDouble(5, billItem.getAmount());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAllBillItems() {

        String sql = "SELECT * FROM bill_item";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                System.out.println("Bill Item ID: " +
                        resultSet.getInt("bill_item_id"));

                System.out.println("Bill ID: " +
                        resultSet.getInt("bill_id"));

                System.out.println("Service ID: " +
                        resultSet.getInt("service_id"));

                System.out.println("Quantity: " +
                        resultSet.getDouble("quantity"));

                System.out.println("Rate: " +
                        resultSet.getDouble("rate"));

                System.out.println("Amount: " +
                        resultSet.getDouble("amount"));

                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateBillItem(BillItem billItem) {

        String sql = "UPDATE bill_item SET bill_id = ?, service_id = ?, quantity = ?, rate = ?, amount = ? WHERE bill_item_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, billItem.getBillId());
            statement.setInt(2, billItem.getServiceId());
            statement.setDouble(3, billItem.getQuantity());
            statement.setDouble(4, billItem.getRate());
            statement.setDouble(5, billItem.getAmount());
            statement.setInt(6, billItem.getBillItemId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBillItem(int billItemId) {

        String sql = "DELETE FROM bill_item WHERE bill_item_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, billItemId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}