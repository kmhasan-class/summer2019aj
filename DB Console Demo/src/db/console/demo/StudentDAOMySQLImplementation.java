/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class StudentDAOMySQLImplementation implements StudentDAO {
    
    private Connection connection;
    private PreparedStatement preparedCreateStatement;
    private PreparedStatement preparedRetrieveByIdStatement;
    private PreparedStatement preparedRetrieveAllStatement;
    private PreparedStatement preparedDeleteAllStatement;
    
    public StudentDAOMySQLImplementation() {
        try {
            connection = DbConnection.getConnection();
            
            InputStream inputStream = getClass().getResourceAsStream("resources/query.properties");
            System.out.println("InputStream " + inputStream);
            InputStreamReader fileReader = new InputStreamReader(inputStream);
            System.out.println("InputStreamReader " + fileReader);
//            FileReader fileReader = new FileReader("resources/query.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            System.out.printf("INSERT [%s]\n", properties.getProperty("INSERT_STUDENT_QUERY"));
            
            preparedCreateStatement = connection.prepareStatement(properties.getProperty("INSERT_STUDENT_QUERY"));
//            preparedCreateStatement = connection.prepareStatement("INSERT INTO student VALUES(?, ?);");
            preparedRetrieveByIdStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_STUDENT_BY_ID_QUERY"));
            preparedRetrieveAllStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_ALL_STUDENTS_QUERY"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_STUDENTS_QUERY"));
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Student create(Student student) {
        // TODO throw ValidationException if the student ID is longer than 13 characters
        // TODO write your own ValidationException class extending it from the Exception class
        
        try {
            //Statement statement = connection.createStatement();
            System.out.println(preparedCreateStatement);
            preparedCreateStatement.setString(1, student.getId());
//            preparedCreateStatement.setString(2, student.getId());
            preparedCreateStatement.setString(2, student.getName());
            int executeUpdate = preparedCreateStatement.executeUpdate();
            return retrieve(student.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // TODO implement all the methods from the interface
    
    // TODO implement a file version of the DAO

    @Override
    public Student retrieve(String studentId) {
        try {
            //Statement statement = connection.createStatement();
            preparedRetrieveByIdStatement.setString(1, studentId);
            ResultSet resultSet = preparedRetrieveByIdStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student(resultSet.getString("id"), resultSet.getString("name"));
                return student;
            } else return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Student> retrieve() {
        List<Student> studentList = new ArrayList<>();
        try {
            //Statement statement = connection.createStatement();
            ResultSet resultSet = preparedRetrieveAllStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getString("id"), resultSet.getString("name"));
                studentList.add(student);
            }
            
            return studentList;
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }

    @Override
    public List<Student> retrieve(Predicate<Student> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public int deleteAll() {
        int deletedRows = 0;
        try {
            //Statement statement = connection.createStatement();
            System.out.println(preparedDeleteAllStatement);
            deletedRows = preparedDeleteAllStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deletedRows;
    }
}



