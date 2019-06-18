/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class DBConsoleDemo {

    public void insertStudent(Student student) {
        try {
            Connection connection = DbConnection.getConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO student VALUES('" + student.getId() + "', '" + student.getName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Student> retrieveStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
          
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
            
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                //System.out.printf("%s %s\n", id, name);
                Student student = new Student(id, name);
                studentList.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }
    
    public DBConsoleDemo() {
        //insertStudent("2012000000015", "Sadat Khan");
        // method handle
        //retrieveStudents().stream().forEach(System.out::println);
        
        StudentDAO studentDAO = new StudentDAOMySQLImplementation();
        studentDAO.create(new Student("1234", "Random Joe"));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DBConsoleDemo();
    }
    
}








