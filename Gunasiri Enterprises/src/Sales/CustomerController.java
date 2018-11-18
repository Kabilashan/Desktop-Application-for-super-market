/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Sales.DBConnection;
import Sales.Customer;

/**
 *
 * @author student
 */
public class CustomerController {
    public static ArrayList<String>getAllCustomerIds() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select id from Customer");
        ArrayList <String>customerIdList=new ArrayList<>();
        while(rst.next()){
            customerIdList.add(rst.getString("id"));
        }
        return customerIdList;
    }
    public static ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Customer");
        return null;
    }
    public static Customer searchCustomer(String id ) throws SQLException, ClassNotFoundException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Customer where id='"+id+"'");
        if(rst.next()){
            return new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary"));
        }else{
            return null;
        }
    }
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Insert into Customer Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        return stm.executeUpdate() > 0;
    }


    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Delete From Customer where id='" + id + "'";
        return stm.executeUpdate(SQL) > 0;
    }

    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Update Customer set name=?, address=?, salary=? where id=?";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());
        return stm.executeUpdate() > 0;
    }

    public static ArrayList<Customer> getAllcustomer() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * from Customer");
        ArrayList<Customer> customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
            customerList.add(customer);
        }
        return customerList;
    }    
}
