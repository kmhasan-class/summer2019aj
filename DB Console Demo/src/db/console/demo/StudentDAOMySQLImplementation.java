/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class StudentDAOMySQLImplementation implements StudentDAO {

    @Override
    public Student create(Student student) {
        try {
            Connection connection = DbConnection.getConnection();

            Statement statement = connection.createStatement();
            int executeUpdate = statement.executeUpdate("INSERT INTO student VALUES('" + student.getId() + "', '" + student.getName() + "')");
            // TODO fix this code so that you return the student that actually got saved
            // not the one that was passed to you
            if (executeUpdate == 1)
                return student;
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // TODO implement all the methods from the interface
    
    // TODO implement a file version of the DAO
}



