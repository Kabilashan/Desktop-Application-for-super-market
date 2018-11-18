
package SupplierManagement;

import java.sql.Connection.*;
import java.sql.SQLException;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;




public class DBconnection {
    
    public static Connection connect(){
        Connection conn = null;
        
        try{
            
           Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ge", "root", "");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        
        }
        
        
        
        
        return conn;
    
            
    }
    
}
