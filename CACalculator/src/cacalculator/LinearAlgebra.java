/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cacalculator;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author barxl
 */
public class LinearAlgebra {
    double userResult;
    String user;
    String resultString;
    
    public boolean equationSolver() {
        
        System.out.println("Enter the number of variables in the equations: ");
        
        try {
            
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            
            System.out.println("");
            System.out.println("Enter the coefficients of each variable for each equations");
            System.out.println("ax + by + cz + ... = d");
            System.out.println("");
            double [][]mat = new double[n][n];
            double [][]constants = new double[n][1];
            
            //user input
            
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                
                    mat[i][j] = input.nextDouble();
                
                }
                constants[i][0] = input.nextDouble();
            }
           
            //inverse of matrix 
            double inverted_mat[][] = invert(mat);
            
            
            //Multiplication of mat inverse and constants
            double result[][] = new double[n][1];
            for (int i = 0; i < n; i++) 
            {
                for (int j = 0; j < 1; j++)
                {
                    for (int k = 0; k < n; k++)
                    {
                        result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
                    }
                }
            }
            System.out.println("");
            System.out.println("The result is:");
            for(int i=0; i<n; i++)
            {
                
                System.out.println(result[i][0] + " ");
                
            }
        } catch (Exception e) {
            System.out.println("Please just use numbers!");
            System.out.println("Try again!");
        }
        return true;
    }
    //Method to convert 
    public double result(){
        
        return userResult;
    }
    
    //Method to get the inverse of the matrix
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
// Method to carry out the pivoting Gaussian. Here index[] stores pivoting order.
 
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
                a[index[i]][j] = pj;
 
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
        
    }
    
}


