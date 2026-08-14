package org.example.dao;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillHistoryDAO {

    public ResultSet getAllBillHistory() {

        String sql =
                "SELECT b.bill_id, b.bill_date, " +
                        "c.customer_name, " +
                        "s.service_name, " +
                        "bi.quantity, bi.rate, bi.amount " +
                        "FROM bill b " +
                        "JOIN customer c ON b.customer_id = c.customer_id " +
                        "JOIN bill_item bi ON b.bill_id = bi.bill_id " +
                        "JOIN service s ON bi.service_id = s.service_id " +
                        "ORDER BY b.bill_id DESC";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            return statement.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}