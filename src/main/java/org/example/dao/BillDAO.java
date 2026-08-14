package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.Bill;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class BillDAO {

    public int addBill(Bill bill) {

        String sql = "INSERT INTO bill(customer_id, total_amount) VALUES(?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS
                    );

            statement.setInt(1, bill.getCustomerId());
            statement.setDouble(2, bill.getTotalAmount());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public void getAllBills() {
        String sql = "SELECT * FROM bill";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                System.out.println("Bill ID: " +
                        resultSet.getInt("bill_id"));

                System.out.println("Customer ID: " +
                        resultSet.getInt("customer_id"));

                System.out.println("Total Amount: " +
                        resultSet.getDouble("total_amount"));

                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateBill(Bill bill) {

        String sql = "UPDATE bill SET customer_id = ?, total_amount = ? WHERE bill_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bill.getCustomerId());
            statement.setDouble(2, bill.getTotalAmount());
            statement.setInt(3, bill.getBillId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBill(int billId) {

        String sql = "DELETE FROM bill WHERE bill_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, billId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getBillCount() {

        String sql = "SELECT COUNT(*) FROM bill";

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
