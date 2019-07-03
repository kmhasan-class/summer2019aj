/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mavenlombokdbdemo;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author kmhasan
 */
public class Main {

    public Main() {
        System.out.println("Hello world!");
        
        Student student = new Student(343l, "John Doe", LocalDate.of(2000, Month.JANUARY, 6));
        System.out.println(student);
        
        Connection connection = ConnectionSingleton.getConnection();
    }
    
    public static void main(String args[]) {
        new Main();
    }
}
