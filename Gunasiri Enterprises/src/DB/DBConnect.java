
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    

    public static Connection connect() throws ClassNotFoundException{
        
        Connection con = null;
        
        try{
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ge", "root", "");
          
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return con;
    }
      
}
