/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kmhasan
 */
import db.console.demo.Student;
import db.console.demo.StudentDAO;
import db.console.demo.StudentDAOMySQLImplementation;

public class StudentDAOMySQLImplementationTest {
    private static StudentDAO studentDAO;
    
    public StudentDAOMySQLImplementationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Preparing to run all the tests");
        studentDAO = new StudentDAOMySQLImplementation();
    }
    
    @AfterClass
    public static void tearDownClass() {
        studentDAO = null;
    }
    
    @Before
    public void setUp() {
        System.out.println("Somebody help me! I got a test coming!");
        studentDAO.deleteAll();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("Testing create method");
        Student student = new Student("6530", "Random Guy");
        
        Student createdStudent = studentDAO.create(student);
        
        assertEquals(student, createdStudent);
//        assertEquals(student.getId(), createdStudent.getId());
//        assertEquals(student.getName(), createdStudent.getName());
    }

    @Test
    public void testCreateStudentWithLongId() {
        System.out.println("Testing create method");
        // TODO expect a ValidationException to occur if the ID is too long
        Student student = new Student("6530238478274567234576", "Random Guy");
        
        Student createdStudent = studentDAO.create(student);
        
        assertEquals(student, createdStudent);
//        assertEquals(student.getId(), createdStudent.getId());
//        assertEquals(student.getName(), createdStudent.getName());
    }

    @Test
    public void testRetrieveById() {
        System.out.println("Testing retrieve by ID method");
        Student student = new Student("7528", "Random Guy");
        
        studentDAO.create(student);
        
        assertEquals(student, studentDAO.retrieve(student.getId()));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}














