/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Sales.DBConnection;
import Sales.Bill;
import Sales.Customer;
import Sales.Item;
import Sales.Order;
import java.sql.PreparedStatement;

/**
 *
 * @author student
 */
public class ItemController {
    public static ArrayList<String> getAllItemCodes() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select code From Item");
        ArrayList<String>itemCodeList=new ArrayList<>();
        while(rst.next()){
            itemCodeList.add(rst.getString("code"));
        }
        return itemCodeList;
    }
    
    
    public static ArrayList<String> getAllOrderCodes() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select orderID From orderdetail");
        ArrayList<String>orderCodeList=new ArrayList<>();
        while(rst.next()){
            orderCodeList.add(rst.getString("orderID"));
        }
        return orderCodeList;
    }
    
    
    public static Item searchItem(String code) throws ClassNotFoundException, SQLException{
      Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Item where code='"+code+"'");        
        if(rst.next()){
            return new Item(code,rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("qtyOnHand"));
        }else{
            return null;
        }
    }
    
    
    public static Item searchOrder(String code) throws ClassNotFoundException, SQLException{
      Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From orderdetail where orderId='"+code+"'");        
        if(rst.next()){
            return new Item(code,rst.getString("itemCode"),rst.getInt("qty"),rst.getDouble("unitPrice"));
        }else{
            return null;
        }
    }
    
    
    
     public static boolean deleteItem(String id, String code) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Delete From orderdetail where orderId='" + id + "' AND itemCode='"+code+"'";
        return stm.executeUpdate(SQL) > 0;
    }
     
     
      public static boolean updateOrder(Item item) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Update orderdetail set  qty=?, unitPrice=? where orderId=? AND itemCode=?";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(4, item.getItemCode());
        stm.setObject(1, item.getQuty());
        stm.setObject(2, item.getPrice());
        stm.setObject(3, item.getOrderID());
        return stm.executeUpdate() > 0;
    }
      
     
       public static ArrayList<Order> getAllOrders() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * from orderdetail");
        ArrayList<Order> orderList=new ArrayList<>();
        while(rst.next()){
            Order order = new Order(rst.getString("orderId"), rst.getString("itemCode"), rst.getInt("qty"), rst.getDouble("unitPrice"));
            orderList.add(order);
        }
        return orderList;
    }    
       
        public static ArrayList<Order> getAllOrders2(String code) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From orderdetail where orderId='"+code+"'");
        ArrayList<Order> orderList=new ArrayList<>();
        while(rst.next()){
           Order order = new Order(code, rst.getString("itemCode"), rst.getInt("qty"), rst.getDouble("unitPrice"));
            orderList.add(order);
        }
        return orderList;
    }    
        
       
        public static ArrayList<Bill> getBillingCustomer(String code) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From bill where orderId='"+code+"'");
        ArrayList<Bill> orderList=new ArrayList<>();
        while(rst.next()){
           Bill bill = new Bill( rst.getString("itemCode"), rst.getString("description"),rst.getInt("qty"), rst.getDouble("unitPrice"),rst.getDouble("amount"));
            orderList.add(bill);
        }
        return orderList;
        }
       
        
}
       
       
      
   
    
    
   

