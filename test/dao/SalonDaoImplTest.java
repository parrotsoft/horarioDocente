/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import models.Salon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel
 */
public class SalonDaoImplTest {
    
    public SalonDaoImplTest() {
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
     * Test of save method, of class SalonDaoImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Salon data = null;
        SalonDaoImpl instance = new SalonDaoImpl();
        boolean expResult = false;
        boolean result = instance.save(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class SalonDaoImpl.
     */
    @Test
    public void testList() {
        System.out.println("list");
        SalonDaoImpl instance = new SalonDaoImpl();
        List<Salon> result = instance.list();
        assertNotNull(result);
        System.out.println("List ---> OK");
    }

    /**
     * Test of update method, of class SalonDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Salon data = null;
        SalonDaoImpl instance = new SalonDaoImpl();
        boolean expResult = false;
        boolean result = instance.update(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class SalonDaoImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Salon data = null;
        SalonDaoImpl instance = new SalonDaoImpl();
        boolean expResult = false;
        boolean result = instance.delete(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
