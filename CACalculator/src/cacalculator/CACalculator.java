/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacalculator;

import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author revol
 */
public class CACalculator {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     *
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        
        DatabaseWriter myDatabase = new DatabaseWriter();
        System.out.println(myDatabase.outputSetup());
        //Using boolean it will show true if it can write the values in the workbench
        
        //writing in the database the values in the database
        myDatabase.outputData();
        
        //calling and printing the calculator (Linear Algebra class)
        LinearAlgebra matrixDisplay = new LinearAlgebra();
        System.out.println(matrixDisplay.equationSolver());
        
        
        //List with the table information
        List<Users> userList;
        DatabaseReader input = new DatabaseReader();
        userList = input.inputData();
        
        //Printing each column of the table
        System.out.println(userList.get(0).user_id);
        System.out.println(userList.get(0).name); 
        System.out.println(userList.get(0).surname);
        
    }
    
}
