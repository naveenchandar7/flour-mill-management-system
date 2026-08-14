package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.ElectricityUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ElectricityUsageDAO {

    // CREATE
    public void addUsage(ElectricityUsage usage) {

        String sql = "INSERT INTO electricity_usage " +
                "(usage_date, start_unit, end_unit, units_used, rate_per_unit, electricity_cost) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setDate(1, usage.getUsageDate());
            statement.setDouble(2, usage.getStartUnit());
            statement.setDouble(3, usage.getEndUnit());
            statement.setDouble(4, usage.getUnitsUsed());
            statement.setDouble(5, usage.getRatePerUnit());
            statement.setDouble(6, usage.getElectricityCost());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // READ
    public void getAllElectricityUsage() {

        String sql = "SELECT * FROM electricity_usage";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                System.out.println("Usage ID: " +
                        resultSet.getInt("usage_id"));

                System.out.println("Usage Date: " +
                        resultSet.getDate("usage_date"));

                System.out.println("Start Unit: " +
                        resultSet.getDouble("start_unit"));

                System.out.println("End Unit: " +
                        resultSet.getDouble("end_unit"));

                System.out.println("Units Used: " +
                        resultSet.getDouble("units_used"));

                System.out.println("Rate Per Unit: " +
                        resultSet.getDouble("rate_per_unit"));

                System.out.println("Electricity Cost: " +
                        resultSet.getDouble("electricity_cost"));

                System.out.println("Created At: " +
                        resultSet.getTimestamp("created_at"));

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // UPDATE
    public void updateElectricityUsage(ElectricityUsage usage) {

        String sql = "UPDATE electricity_usage " +
                "SET usage_date = ?, start_unit = ?, end_unit = ?, " +
                "units_used = ?, rate_per_unit = ?, electricity_cost = ? " +
                "WHERE usage_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setDate(1, usage.getUsageDate());
            statement.setDouble(2, usage.getStartUnit());
            statement.setDouble(3, usage.getEndUnit());
            statement.setDouble(4, usage.getUnitsUsed());
            statement.setDouble(5, usage.getRatePerUnit());
            statement.setDouble(6, usage.getElectricityCost());

            statement.setInt(7, usage.getUsageId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE
    public void deleteElectricityUsage(int usageId) {

        String sql =
                "DELETE FROM electricity_usage WHERE usage_id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, usageId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double getTotalElectricityCost() {

        String sql =
                "SELECT COALESCE(SUM(electricity_cost), 0) " +
                        "FROM electricity_usage";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}