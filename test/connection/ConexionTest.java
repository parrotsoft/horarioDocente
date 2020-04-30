/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel
 */
public class ConexionTest {
    private Conexion conexion = new Conexion();
    
    public ConexionTest() {
    }

    @Test
    public void testConectart() throws Exception {
        assertNull(conexion.conectart());
    }
    
}
