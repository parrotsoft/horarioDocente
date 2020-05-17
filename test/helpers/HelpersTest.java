/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel
 */
public class HelpersTest {
    
    public HelpersTest() {
    }

    @Test
    public void testValidarMail() {
        String correo = "lopezarizamiguel@gmai.com";
        boolean Iscorreo = Helpers.ValidarMail(correo);
        assertEquals(true, Iscorreo);
        System.out.println(correo + Iscorreo + " ---> OK");
    }
    
    @Test
    public void testValidarUser() {
        String user = "admin";
        boolean Iscorreo = Helpers.ValidarUser(user);
        assertEquals(true, Iscorreo);
        System.out.println(user + Iscorreo + " ---> OK");
    }
    
}
