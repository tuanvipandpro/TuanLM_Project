/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.bill;

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
public class BillDAO implements Serializable{
    public List<BillDTO> getBillByEmail(String Email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BillDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT ID,Username,Total,Booking_Date,Status_ID "
                   + "FROM bill "
                   + "WHERE Status_ID = ? AND Username = ? "
                   + "ORDER BY Booking_Date DESC ";
           
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, 2); //2 : active
           ps.setString(2, Email);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String email = rs.getString("Username");
               float total = rs.getFloat("Total");
               String booking_date = rs.getString("Booking_Date");
               int status_id = rs.getInt("Status_ID");
               
               BillDTO dto = new BillDTO(id, email, total, booking_date, status_id);
               
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
    
    public List<BillDTO> getAllBill() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BillDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT ID,Username,Total,Booking_Date,Status_ID "
                   + "FROM bill "
                   + "ORDER BY Booking_Date DESC ";
           
           ps = con.prepareStatement(sql);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String email = rs.getString("Username");
               float total = rs.getFloat("Total");
               String booking_date = rs.getString("Booking_Date");
               int status_id = rs.getInt("Status_ID");
               
               BillDTO dto = new BillDTO(id, email, total, booking_date, status_id);
               
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
    
    public boolean changeBillStatus(String id, String status) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;

        try{
           con = DB_Utils.makeConnection();
           String sql = "UPDATE bill "
                   + "SET Status_ID = ? "
                   + "WHERE ID = ? ";
           
           ps = con.prepareStatement(sql);
           ps.setString(1, status);
           ps.setString(2, id);
           
           int result = ps.executeUpdate();
           if (result > 0) return true;
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return false;
    }

    public List<BillDTO> searchBillByName(String user, String name) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BillDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT DISTINCT bill.ID, Username, Total, Booking_Date, bill.Status_ID "
                   + "FROM bill, renting, car "
                   + "WHERE bill.ID = renting.Bill_ID AND renting.Car_ID = car.ID "
                   + "AND car.Name LIKE ? AND bill.Username = ? AND bill.Status_ID = ? "
                   + "ORDER BY Booking_Date DESC ";
           
           ps = con.prepareStatement(sql);
           
           ps.setString(1, "%" + name +"%");
           ps.setString(2, user);
           ps.setInt(3, 2);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String email = rs.getString("Username");
               float total = rs.getInt("Total");
               String booking_date = rs.getString("Booking_Date");
               int status_id = rs.getInt("Status_ID");
               
               BillDTO dto = new BillDTO(id, email, total, booking_date, status_id);
               
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
    
    public List<BillDTO> searchBillByDate(String user, String date) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BillDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT DISTINCT bill.ID, Username, Total, Booking_Date, Status_ID "
                   + "FROM bill "
                   + "WHERE Booking_Date >= ? "
                   + "AND bill.Username = ? AND Status_ID = ? "
                   + "ORDER BY Booking_Date DESC ";
           
           ps = con.prepareStatement(sql);
           
           ps.setString(1, date);
           ps.setString(2, user);
           ps.setInt(3, 2);
           
           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String email = rs.getString("Username");
               float total = rs.getInt("Total");
               String booking_date = rs.getString("Booking_Date");
               int status_id = rs.getInt("Status_ID");
               
               BillDTO dto = new BillDTO(id, email, total, booking_date, status_id);
               
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
    
    public boolean createNewBill(String username, Timestamp date, float total, String sale_id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "INSERT INTO bill(Username,Total,Booking_Date,Status_ID,Sale_ID) "
                   + "VALUES (?,?,?,?,?)";
           ps = con.prepareStatement(sql);
           
           ps.setString(1, username);
           ps.setFloat(2, total);
           ps.setTimestamp(3, date);
           ps.setInt(4, 2);
           ps.setString(5, sale_id);

           int result = ps.executeUpdate();
           
           return result > 0;
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }    

    public int getIDByEmailAndDate(String Email, Timestamp date) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT ID,Username,Total,Booking_Date,Status_ID "
                   + "FROM bill "
                   + "WHERE Username = ? AND Booking_Date = ? ";
           
           ps = con.prepareStatement(sql);
           
           ps.setString(1, Email);
           ps.setTimestamp(2, date);
           
           rs = ps.executeQuery(); 
           
           if(rs.next()){
               return rs.getInt("ID");
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return 0;
    }    
}
