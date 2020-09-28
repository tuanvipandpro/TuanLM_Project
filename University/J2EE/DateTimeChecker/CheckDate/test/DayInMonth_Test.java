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
public class DayInMonth_Test {
    
    public DayInMonth_Test() {
    }

    @Test
    public void testDayInMonth_01() { //xong
        int year = 2021;
        int month = 0;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_02() { //xong
        int year = 1999;
        int month = -5;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }   
    
    @Test
    public void testDayInMonth_03() { //xong
        int year = 2020;
        int month = 8;
        int expResult = 31;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_04() {//xong
        int year = 1999;
        int month = 8;
        int expResult = 31;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_05() {//xong
        int year = 199;
        int month = 15;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }    

    @Test
    public void testDayInMonth_06() {//xong
        int year = 2021;
        int month = 11;
        int expResult = 30;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testDayInMonth_07() {//xong
        int year = 2020;
        int month = 2;
        int expResult = 29;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }  

    @Test
    public void testDayInMonth_08() {//xong
        int year = 1999;
        int month = 2;
        int expResult = 28;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testDayInMonth_09() {//xong
        int year = -200;
        int month = 15;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testDayInMonth_10() {//xong
        int year = -200;
        int month = 2;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_11() {//xong
        int year = -200;
        int month = 8;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_12() {//xong
        int year = -200;
        int month = 15;
        int expResult = -1;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }   
    
    @Test
    public void testDayInMonth_13() {//xong
        int year = 2020;
        int month = 10;
        int expResult = 31;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDayInMonth_14() {
        int year = 2016;
        int month = 2;
        int expResult = 29;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }   
    
    @Test
    public void testDayInMonth_15() {
        int year = 2021;
        int month = 2;
        int expResult = 28;
        int result = DateCheck.DayInMonth(year, month);
        
        assertEquals(expResult, result);
    }      
}
