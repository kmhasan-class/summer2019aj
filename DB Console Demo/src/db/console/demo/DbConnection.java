/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.demo;

import com.sun.media.jfxmediaimpl.platform.Platform;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class DbConnection {
    private static Connection connection = null;
    private static DbConnection instance = new DbConnection();
    
    private DbConnection() {
        
        try {
//            FileReader fileReader = new FileReader("db.properties");
//            Properties properties = new Properties();
//            properties.load(fileReader);
//            
//            String username = properties.getProperty("username");
//            String password = properties.getProperty("password");
            
            // TODO replace the hardcoded URL with something you read from the properties
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationdb", username, password);

            // TODO Note to self: fix the environment variable reading code
//            System.out.println("Printing all environment variables");
//            System.getenv().forEach((key, value) -> System.out.printf("Key [%s] value [%s]\n", key, value));
//            System.out.println("Done printing");
            
            String username = System.getProperty("username");
            String password = System.getProperty("password");
            System.out.printf("Username:password as environment variable: [%s]:[%s]\n", username, password);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationdb", username, password);
            System.out.println("Connection created");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}













