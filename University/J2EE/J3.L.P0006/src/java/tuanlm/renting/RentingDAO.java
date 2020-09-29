/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.renting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tuanlm.utils.DB_Utils;

/**
 *
 * @author Tuan
 */
public class RentingDAO implements Serializable{
    public List<RentingDTO> getDetailsForBill(String bill_id) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentingDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT renting.ID,car.Name,Rental_Date, Return_Date, renting.Quantity, Bill_ID "
                   + "FROM renting, car "
                   + "WHERE Bill_ID = ? AND car.ID = renting.Car_ID ";
           
           ps = con.prepareStatement(sql);
           
           ps.setString(1, bill_id);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String car = rs.getString("Name");
               String rental_date = rs.getString("Rental_Date");
               String return_date = rs.getString("Return_Date");
               int quantity = rs.getInt("Quantity");
               int bill = rs.getInt("Bill_ID");
               
               RentingDTO dto = new RentingDTO(id, car, rental_date, return_date, quantity, bill);
               
               if (list == null) list = new ArrayList<>();
               
               list.add(dto);
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return list;
    }  
    
    public List<RentingDTO> getHistoryForCar(String carID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentingDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT renting.ID,car.Name,Rental_Date, Return_Date, renting.Quantity, Bill_ID "
                   + "FROM renting, car "
                   + "WHERE car.ID = renting.ID AND Return_Date <= ? AND car.ID = ?";
           
           ps = con.prepareStatement(sql);
           
           ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
           ps.setString(2, carID);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String car = rs.getString("Name");
               String rental_date = rs.getString("Rental_Date");
               String return_date = rs.getString("Return_Date");
               int quantity = rs.getInt("Quantity");
               int bill = rs.getInt("Bill_ID");
               
               RentingDTO dto = new RentingDTO(id, car, rental_date, return_date, quantity, bill);
               
               if (list == null) list = new ArrayList<>();
               
               list.add(dto);
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return list;
    }
    
    public boolean createDetailBill(String car_id, String rental_date, String return_date,  int quantity, int bill_id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "INSERT INTO renting(Car_ID,Rental_Date,Return_Date,Quantity,Bill_ID) "
                   + "VALUES (?,?,?,?,?)";
           ps = con.prepareStatement(sql);
           
           ps.setString(1, car_id);
           ps.setString(2, rental_date);
           ps.setString(3, return_date);
           ps.setInt(4, quantity);
           ps.setInt(5, bill_id);

           int result = ps.executeUpdate();
           
           return result > 0;
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

    public List<RentingDTO> getDetailsToCheck(String car_id) throws NamingException, SQLException{
        //lấy details để kiểm tra có thể thuê xe được không
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RentingDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT ID,Car_ID,Rental_Date,Return_Date,Quantity,Bill_ID "
                   + "FROM renting "
                   + "WHERE Return_Date >= ? AND Car_ID = ? ";
           
           ps = con.prepareStatement(sql);
           
           ps.setString(1, tuanlm.utils.Date_Utils.getNow());
           ps.setString(2, car_id);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String car = rs.getString("Car_ID");
               String rental_date = rs.getString("Rental_Date");
               String return_date = rs.getString("Return_Date");
               int quantity = rs.getInt("Quantity");
               int bill = rs.getInt("Bill_ID");
               
               RentingDTO dto = new RentingDTO(id, car, rental_date, return_date, quantity, bill);
               
               if (list == null) list = new ArrayList<>();
               
               list.add(dto);
            }

        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return list;
    }     
}
