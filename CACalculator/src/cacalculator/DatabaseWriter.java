  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author revol
 */
public class DatabaseWriter{
    
    //Connection with my database
    String dbName = "admin_user";
    String url = "jdbc:mysql://localhost/" + dbName;
    String user = "root";
    String password = "root"; 
    
    public boolean outputSetup() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //Line to be able to get the mysql driver
        
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/", user, password);
        
        Statement sttt = con.createStatement();
        
        //Creating the schema and the table
        //SQL command to create a database
        sttt.execute("CREATE SCHEMA IF NOT EXISTS " + dbName + ";");
        
        //SQL command to create tables
        sttt.execute("USE admin_user;");
        sttt.execute(
                "CREATE TABLE IF NOT EXISTS user ("
                        + "user_id INT(20) NOT NULL PRIMARY KEY,"
                        + "name VARCHAR(30),"
                        + "surname VARCHAR(30));"
        );
        
        sttt.execute(
                "CREATE TABLE IF NOT EXISTS admin ("
                        + "admin_id INT(20) NOT NULL PRIMARY KEY,"
                        + "name VARCHAR(30),"
                        + "surname VARCHAR(30));"
        );
        
        sttt.execute("CREATE TABLE IF NOT EXISTS user_result ("
                        + "result DOUBLE NOT NULL PRIMARY KEY);"
                        
        );
        
        return true;
        
        } catch (SQLException e) {
            return true;
        }
        
    }
    
    //Method to insert value int he tables
    public boolean  outputData() throws ClassNotFoundException{
    Class.forName("com.mysql.cj.jdbc.Driver"); //Line to be able to get the mysql driver
    
    Scanner scan = new Scanner(System.in);
        
        try {
        Connection con = DriverManager.getConnection(url, user, password);
        
        Statement sttt = con.createStatement();
            int user_id = 1;
            System.out.println("Enter your name: ");
            String name = scan.next();
            
            System.out.println("Enter your Surname: ");
            String surname = scan.next();
            
            LinearAlgebra resultWriter = new LinearAlgebra();
            double result = resultWriter.result();
        
        //Executing commands to insert properties in the user table
        sttt.execute("USE admin_user;");
        sttt.execute(
                String.format("INSERT INTO user (user_id, name, surname) "
                        + "VALUES (%d, \"%s\", \"%s\") ;",
                        user_id++, name, surname)
        );
        
        sttt.execute("USE admin_user");
        
        //Executing commands to insert properties in the user_result
        sttt.execute(
                String.format("INSERT INTO user_result (result) "
                        + "VALUES (?);",
                        result)
        );
                
        return true;
            
        } catch (SQLException e) {
            return false;
        }
    }
    
}        
    

   
