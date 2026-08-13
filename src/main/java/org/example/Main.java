package org.example;

import java.sql.Connection;

import org.example.dao.BillDAO;
import org.example.dao.BillItemDAO;
import org.example.dao.CustomerDAO;
import org.example.dao.MillServiceDAO;
import org.example.model.Bill;
import org.example.model.BillItem;
import org.example.model.Customer;
import org.example.model.MillService;


public class Main {
    public static void main(String[] args) {

//        Customer customer=new Customer(
//                1,
//                "Mathi",
//                "Thanipadi",
//                "1234567891"
//        );
//        CustomerDAO customerDAO=new CustomerDAO();

        //customerDAO.addCustomer(customer);
        //System.out.println("Customer added successfully");

        //customerDAO.updateCustomer(customer);
        //System.out.println("Customer updated successfully");

//        customerDAO.deleteCustomer(2);
//
//        customerDAO.getAllCustomers();


//        try {
//            Connection connection = DatabaseConnection.getConnection();
//
//            System.out.println("Database connected successfully!");
//
//            connection.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //MillService Execution

//        MillService service=new MillService(1,"Raggi Flour",8);
//
//        MillServiceDAO serviceDAO=new MillServiceDAO();
//
//        serviceDAO.addService(service);
//        System.out.println("Data Added Successfully");
//
//        serviceDAO.updateService(service);
//        //System.out.println("Service updated successfully");
//
//        serviceDAO.deleteService(1);
//
//        serviceDAO.getAllServices();

//        Bill bill = new Bill(
//                0,
//                1,
//                500
//        );
//
//        BillDAO billDAO = new BillDAO();
//
//        //billDAO.addBill(bill);
//
//        billDAO.getAllBills();
//
//    }
        BillItem billItem = new BillItem(
                0,
                1,
                2,
                5,
                10,
                50
        );

        BillItemDAO billItemDAO = new BillItemDAO();
        billItemDAO.addBillItem(billItem);

    }
}