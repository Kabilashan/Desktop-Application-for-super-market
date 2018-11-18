/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class dbconnect {
    Connection conn = null;
   
   public static Connection java_db(){
       try{
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ge", "root", "");
            return conn;
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
       }
}
}