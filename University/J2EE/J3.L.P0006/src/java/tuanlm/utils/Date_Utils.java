/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Tuan
 */
public class Date_Utils {
    public static int amountDay(String from, String to) throws ParseException{
        Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
        Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
        
        long result = dateTo.getTime() - dateFrom.getTime();
        int distance = (int) TimeUnit.MILLISECONDS.toDays(result);
        return distance + 1;
    }
    
    public static boolean isValidRent(String from, String to) throws ParseException{
        Date dateNow = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
        Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
        
        return (dateNow.compareTo(dateFrom) <= 0 && dateFrom.compareTo(dateTo) <= 0);
    }
    
    public static int compareTime(String firstDate, String secondDate) throws ParseException{
        Date first = new SimpleDateFormat("yyyy-MM-dd").parse(firstDate);
        Date second = new SimpleDateFormat("yyyy-MM-dd").parse(secondDate);
        
        return first.compareTo(second);
        // - 1 : nhỏ
        // 0   : bằng
        // 1   : lớn
    }
    
    public static String getNow(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }    
}
