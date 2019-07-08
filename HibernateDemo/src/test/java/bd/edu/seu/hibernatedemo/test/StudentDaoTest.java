/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo.test;

import bd.edu.seu.hibernatedemo.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
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
public class StudentDaoTest {
    
    public StudentDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class StudentDao.
     */
    @org.junit.Test
    public void testCreate() {
        System.out.println("create");
        Student student = new Student(1234l, new Name("John", "Doe"), LocalDate.of(2001, Month.FEBRUARY, 20), "nobody@nowhere.com", new ArrayList<>());
        student.getPhoneList().add("12344457");
        student.getPhoneList().add("88774565");
        StudentDao instance = new StudentDao();
        instance.create(student);
        
    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    // TODO make sure that this test passes
    @org.junit.Test
    public void testRetrieveAll() {
        System.out.println("retrieve all");
        StudentDao instance = new StudentDao();
        Student student1 = new Student(1236l, new Name("John", "Doe"), LocalDate.of(2001, Month.FEBRUARY, 20), "nobody@nowhere.com", new ArrayList<>());
        Student student2 = new Student(1237l, new Name("Jane", "Doe"), LocalDate.of(2002, Month.MARCH, 2), "nobody@nowhere.com", new ArrayList<>());
        instance.create(student1);
        instance.create(student2);
        List<Student> studentList = instance.retrieve();
        assertEquals(2, studentList.size());
        assertEquals(student1, studentList.get(0));
        assertEquals(student2, studentList.get(1));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    @org.junit.Test
    public void testRetrieveById() {
        System.out.println("retrieve by Id");
        StudentDao instance = new StudentDao();
        Long longId = 2441139l;
        Student student1 = new Student(longId, new Name("Bela", "Bose"), LocalDate.of(2001, Month.FEBRUARY, 20), "nobody@nowhere.com", new ArrayList<>());
        student1.getPhoneList().add("2441139");
        instance.create(student1);
        Student student2 = instance.retrieve(longId);
        assertEquals(student1, student2);
    }
    
}
