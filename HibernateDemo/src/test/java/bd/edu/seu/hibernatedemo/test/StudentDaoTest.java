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
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author kmhasan
 */
public class StudentDaoTest {

    private static Validator validator;

    public StudentDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
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
        Faculty faculty = new Faculty("KMH1", "Monirul Hasan", Rank.SENIOR_LECTURER, new ArrayList<>());
        Student student = new Student(1234l, // id
                new Name("John", "Doe"), // name
                LocalDate.of(2001, Month.FEBRUARY, 20), // dob
                "nobody@nowhere.com", // email
                new ArrayList<>(), // phone list
                Gender.MALE, // gender
                faculty); // faculty
        student.getPhoneList().add("12344457");
        student.getPhoneList().add("88774565");
        
        // TODO look up the Mockito framework
        StudentDao studentDao = new StudentDao();
        FacultyDao facultyDao = new FacultyDao();
        
        Set<ConstraintViolation<Student>> validate = null;
        try {
            validate = validator.validate(student);
            facultyDao.create(faculty);
            studentDao.create(student);
        } catch (Exception e) {
            assertEquals(1, validate.size());
            validate.forEach(v -> System.err.printf("Message [%s]\n", v.getMessage()));
        }
    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    // TODO make sure that this test passes
    @org.junit.Test
    @Ignore
    public void testRetrieveAll() {
        System.out.println("retrieve all");
        StudentDao instance = new StudentDao();
        Faculty faculty1 = new Faculty("KMH2", "Monirul Hasan", Rank.SENIOR_LECTURER);
        Faculty faculty2 = new Faculty("RAJ2", "Roksana Akter Jolly", Rank.ASSISTANT_PROFESSOR);
        Student student1 = new Student(1236l, new Name("John", "Doe"), LocalDate.of(2001, Month.FEBRUARY, 20), "nobody@nowhere.com", new ArrayList<>(), Gender.MALE, faculty1);
        Student student2 = new Student(1237l, new Name("Jane", "Doe"), LocalDate.of(2002, Month.MARCH, 2), "nobody@nowhere.com", new ArrayList<>(), Gender.FEMALE, faculty2);
        instance.create(student1);
        instance.create(student2);
        List<Student> studentList = instance.retrieve();
        assertEquals(3, studentList.size());
        assertEquals(student1, studentList.get(0));
        assertEquals(student2, studentList.get(1));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    @org.junit.Test
    @Ignore
    public void testRetrieveById() {
        System.out.println("retrieve by Id");
        StudentDao instance = new StudentDao();
        Long longId = 2441139l;
        Faculty faculty = new Faculty("RB1", "Rajon Bardhan", Rank.LECTURER);
        Student student1 = new Student(longId, new Name("Bela", "Bose"), LocalDate.of(2001, Month.FEBRUARY, 20), "nobody@nowhere.com", new ArrayList<>(), Gender.FEMALE, faculty);
        student1.getPhoneList().add("2441139");
        instance.create(student1);
        Student student2 = instance.retrieve(longId);
        assertEquals(student1, student2);
    }

}
