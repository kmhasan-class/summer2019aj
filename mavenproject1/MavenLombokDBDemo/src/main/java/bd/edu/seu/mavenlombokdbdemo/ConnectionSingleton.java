/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mavenlombokdbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class ConnectionSingleton {
    private final static ConnectionSingleton INSTANCE = new ConnectionSingleton();
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }
    
    private ConnectionSingleton() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/registrationdb",
                    "summer2019aj",
                    "aj");
            System.out.println("Connected");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
