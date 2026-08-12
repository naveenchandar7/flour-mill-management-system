package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.MillService;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MillServiceDAO {
    public void addService(MillService service){
        String sql="INSERT INTO service(service_name,rate) VALUES(?,?)";

        try {
            Connection connection= DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            statement.setString(1, service.getServiceName());
            statement.setDouble(2, service.getRatePerKg());

            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getAllServices() {
        String sql = "SELECT * FROM service";
        try {

            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {

                System.out.println("ID: " +
                        resultSet.getInt("service_id"));

                System.out.println("Service: " +
                        resultSet.getString("service_name"));

                System.out.println("Rate: " +
                        resultSet.getDouble("rate"));

                System.out.println("--------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    public void updateService(MillService service){
        String sql="UPDATE service SET service_name=?,rate=? WHERE service_id=? ";

        try {
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            statement.setString(1,service.getServiceName());
            statement.setDouble(2,service.getRatePerKg());
            statement.setInt(3,service.getServiceId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteService(int service_id){
        String sql="Delete from service where service_id=?";

        try{
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            statement.setInt(1,service_id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
