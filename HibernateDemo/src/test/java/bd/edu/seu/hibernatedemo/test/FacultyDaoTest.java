/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo.test;

import bd.edu.seu.hibernatedemo.Faculty;
import bd.edu.seu.hibernatedemo.FacultyDao;
import bd.edu.seu.hibernatedemo.Rank;
import java.util.List;
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
public class FacultyDaoTest {
    
    public FacultyDaoTest() {
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
     * Test of create method, of class FacultyDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Faculty t = new Faculty("KMH", "Monirul Hasan", Rank.SENIOR_LECTURER);
        FacultyDao instance = new FacultyDao();
        instance.create(t);
    }

    /**
     * Test of retrieve method, of class FacultyDao.
     */
    @Test
    @Ignore
    public void testRetrieve_0args() {
        System.out.println("retrieve");
        FacultyDao instance = new FacultyDao();
        List<Faculty> expResult = null;
        List<Faculty> result = instance.retrieve();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieve method, of class FacultyDao.
     */
    @Test
    @Ignore
    public void testRetrieve_String() {
        System.out.println("retrieve");
        String id = "";
        FacultyDao instance = new FacultyDao();
        Faculty expResult = null;
        Faculty result = instance.retrieve(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
