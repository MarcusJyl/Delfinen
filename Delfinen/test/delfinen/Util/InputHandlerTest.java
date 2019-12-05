/*
 * To change this license header, choose License Headers in Project Prop
erties
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jannich
 */
public class InputHandlerTest {
    
    public InputHandlerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of lavMedlem method, of class InputHandler.
     */
    @Test
    public void testLavMedlem() {
    }

    /**
     * Test of beregnKontingent method, of class InputHandler.
     */
    @Test
    public void testBeregnKotingent() {
        double expected = 1600 * 0.75;
        String fødselsdato = "1936-03-21";
        int status = 2;
        
        double result = InputHandler.beregnKontingent(fødselsdato, status);
        
        assertEquals(expected, result, 0.09);
    }

    /**
     * Test of getAlder method, of class InputHandler.
     */
    @Test
    public void testGetAlder() {
        String fødselsdag = "1999-03-25";
        
        int expected = 20;
        
        int result = InputHandler.getAlder(fødselsdag);
        
        assertEquals(expected, result);
    }
    
}
