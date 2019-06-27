/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import histogram.Histogram;
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
public class ParallelHistogramTest {

    public ParallelHistogramTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testParallelHistogramCalculation() {
        Histogram histogram = new Histogram();
        int[] sequentialHistogram = histogram.calculateHistogram("harley-davidson.jpg");
        int[] parallelHistogram = histogram.parallelCalculateHistogram("harley-davidson.jpg");
        
        //assertEquals(sequentialHistogram, parallelHistogram);
        assertEquals(sequentialHistogram.length, parallelHistogram.length);
        for (int i = 0; i < sequentialHistogram.length; i++)
            assertEquals(sequentialHistogram[i], parallelHistogram[i]);
    }
}
