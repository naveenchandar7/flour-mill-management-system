package org.example.dao;

import org.example.model.Customer;
import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CustomerDAO {
    public void addCustomer(Customer customer){
        String sql="INSERT INTO customer(customer_name, village, mobile)VALUES(?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,customer.getCustomerName());
            statement.setString(2,customer.getVillage());
            statement.setString(3,customer.getMobile());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAllCustomers(){
        String sql="SELECT * FROM customer";

        try{
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                System.out.println("ID "+resultSet.getInt("customer_id"));
                System.out.println("Customer Name "+resultSet.getString("customer_name"));
                System.out.println("Mobile "+resultSet.getString("mobile"));
                System.out.println("Village "+resultSet.getString("village"));
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCustomer(Customer customer){
        String sql="Update customer SET customer_name=?,village=?,mobile=? WHERE customer_id=?";

        try {
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            statement.setString(1, customer.getCustomerName());
            statement.setString(2,customer.getVillage());
            statement.setString(3,customer.getMobile());
            statement.setInt(4,customer.getCustomerId());

            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public void deleteCustomer(int customerID){
        String sql="DELETE FROM customer WHERE customer_id=?";

        try{
            Connection connection=DatabaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);

            statement.setInt(1,customerID);

            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
