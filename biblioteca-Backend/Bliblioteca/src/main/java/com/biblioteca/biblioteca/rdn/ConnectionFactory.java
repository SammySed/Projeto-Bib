/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.rdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samue
 */
public class ConnectionFactory {
    private String URL = "jdbc:mysql://localhost:3306/db_biblioteca_reges_first";    
    private String USER = "root";    
    private String PASSWORD = "3692440";
    
    public Connection getConnection()
    {
        try{
        
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
            
        }
        catch(SQLException ex){       
            throw new RuntimeException(ex);
        }
    }
}
