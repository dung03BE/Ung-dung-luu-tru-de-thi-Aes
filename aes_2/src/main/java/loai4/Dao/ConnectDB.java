package loai4.Dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectDB {
    protected Connection conn = null;

    public ConnectDB(){
        try {
        	  String URL = "jdbc:mysql://localhost:3306/dldethi";
             String USERNAME = "root";
             String PASSWORD = "root";
           
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
