/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import tuanlm.jframe.DateCheck;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Tuan
 */
public class IsValidDate_Test {

    public IsValidDate_Test() {
    }

    /**
     * Test of IsValidDate method, of class DateCheck.
     */
    @Test
    public void testIsValidDate_01() {//xong
        int year = 1999;
        int month = 10;
        int day = -5;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsValidDate_02() {//xong
        int year = 2020;
        int month = -7;
        int day = 12;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        assertEquals(expResult, result);
    } 

    @Test
    public void testIsValidDate_03() { //xong
        int year = 2000;
        int month = 0;
        int day = 28;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_04() {//xong
        int year = 1999;
        int month = 11;
        int day = 31;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_05() {//xong
        int year = 2020;
        int month = 2;
        int day = 28;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_06() {//xong
        int year = 2020;
        int month = 2;
        int day = 29;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_07() {//xong
        int year = 1999;
        int month = 2;
        int day = 29;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsValidDate_08() {//xong
        int year = 2000;
        int month = 11;
        int day = 12;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_09() {//xong
        int year = 2000;
        int month = -7;
        int day = 45;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsValidDate_10() {
        int year = 2020;
        int month = 2;
        int day = 29;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);

        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_11() {
        int year = 2000;
        int month = 11;
        int day = 31;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_12() {
        int year = 2020;
        int month = 2;
        int day = 30;
        boolean expResult = false;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testIsValidDate_13() {
        int year = 1999;
        int month = 11;
        int day = 30;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    }  
    
    @Test
    public void testIsValidDate_14() {
        int year = 2000;
        int month = -7;
        int day = 30;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsValidDate_15() {
        int year = 2020;
        int month = 10;
        int day = 31;
        boolean expResult = true;
        boolean result = DateCheck.IsValidDate(year, month, day);
        
        
        assertEquals(expResult, result);
    }    
}
