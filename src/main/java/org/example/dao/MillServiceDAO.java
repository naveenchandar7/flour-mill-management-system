package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.MillService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MillServiceDAO {

    public void addService(MillService service) {

        String sql = "INSERT INTO service(service_name, rate) VALUES(?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, service.getServiceName());
            statement.setDouble(2, service.getRatePerKg());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // GET ALL SERVICES FOR BILLING DROPDOWN
    public List<MillService> getAllServices() {

        String sql = "SELECT * FROM service";

        List<MillService> services = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                MillService service = new MillService(
                        resultSet.getInt("service_id"),
                        resultSet.getString("service_name"),
                        resultSet.getDouble("rate")
                );

                services.add(service);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }


    public void updateService(MillService service) {

        String sql =
                "UPDATE service SET service_name=?, rate=? WHERE service_id=?";

        try {
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, service.getServiceName());
            statement.setDouble(2, service.getRatePerKg());
            statement.setInt(3, service.getServiceId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteService(int serviceId) {

        String sql =
                "DELETE FROM service WHERE service_id=?";

        try {
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, serviceId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}