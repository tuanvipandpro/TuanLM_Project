package tuanlm.jframe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DateCheck {
    public static int DayInMonth(int year, int month){
        //if (year < 0) return -1;
        if (1 >= month && month >= 12){ //sai chỗ này nha
            if (month == 4 || month == 6 || month == 9 || month == 11){
                return 30;
            }
            else if (month != 2){
                return 31;
            }
            else{
                if (year % 400 == 0) return 29;
                else if (year % 100 == 0) return 28;
                else if (year % 4 == 0) return 29;
                else return 28;
            }
        }
        return -1;
    }
    
    public static boolean IsValidDate(int year, int month, int day){
        int dayInMonth = -1;//DayInMonth(year, month);
        if (dayInMonth > 0){
            if (day > 0 && day <= dayInMonth) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(IsValidDate(2020, 2, 29));
    }
}
