/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManagement;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Dhananjani
 */
public class db {
    
   Connection conn=null;
   
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