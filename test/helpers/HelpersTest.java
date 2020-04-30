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
        boolean Iscorreo = Helpers.ValidarMail("lopezarizamiguel@gmai.com");
        assertEquals(true, Iscorreo);
    }
    
    @Test
    public void testValidarUser() {
        boolean Iscorreo = Helpers.ValidarUser("adm");
        assertEquals(false, Iscorreo);
    }
    
}
