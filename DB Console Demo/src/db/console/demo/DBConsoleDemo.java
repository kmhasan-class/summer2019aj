/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
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
    
    public void propertiesDemo() {
        try {
            FileReader fileReader = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            
            String username = properties.getProperty("username");
            System.out.printf("Username [%s]\n", username);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public DBConsoleDemo() {
        //insertStudent("2012000000015", "Sadat Khan");
        // method handle
        //retrieveStudents().stream().forEach(System.out::println);
        
        StudentDAO studentDAO = new StudentDAOMySQLImplementation();
//        StudentDAO studentDAO = new StudentDAOFileImplementation();

        Random random = new Random();
        Student student = new Student(String.format("%013d", random.nextInt()), "Nemo Nobody");
        System.out.printf("Inserting [%s]\n", student);
        Student createdStudent = studentDAO.create(student);
        System.out.printf("Inserted  [%s]\n", createdStudent);
        
        studentDAO
                //.retrieve(student -> student.getId().startsWith("2012"))
                .retrieve()
                .stream()
                .forEach(System.out::println);
        
        System.out.printf("Student count: %d\n", studentDAO.count());
        
        System.out.printf("Student by id: [%s] [%s]\n", 1234, studentDAO.retrieve("1234"));

//        propertiesDemo();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DBConsoleDemo();
    }
    
}








