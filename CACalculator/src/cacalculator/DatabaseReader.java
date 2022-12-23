/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cacalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barxl
 */
public class DatabaseReader implements InputInterface {
    
    String dbName = "admin_user";
    String url = "jdbc:mysql://localhost/" + dbName;
    String user = "root";
    String password = "root";
    
    @Override
    public List<Users> inputData() throws ClassNotFoundException{
    Class.forName("com.mysql.cj.jdbc.Driver"); //Line to be able to get the mysql driver
        
        try {
        Connection con = DriverManager.getConnection(url, user, password);
        
        Statement sttt = con.createStatement();
        
        sttt.execute("USE admin_user;");
        
        ResultSet result = sttt.executeQuery("SELECT * from user;");
        
        //This is the list with the values from the database
        List<Users> userList = new ArrayList<>();
        result.next();
        //result.next();
        int user_id = result.getInt("user_id");
        String name = result.getString("name");
        String surname = result.getString("surname");
        
        Users user1 = new Users(user_id, name, surname);
        userList.add(user1);
        
        Users user2 = new Users(user_id, name, surname);
        userList.add(user2);
            
        return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
