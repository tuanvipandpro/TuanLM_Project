/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.car;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tuanlm.utils.DB_Utils;

/**
 *
 * @author Tuan
 */
public class CarDAO implements Serializable{
    public List<CarDTO> getCarList() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT car.ID, Name, Year, category.Category, Price, Quantity, color.Color, Date, Status_ID "
                   + "FROM car,category,color "
                   + "WHERE car.Category_ID = category.ID AND Color_ID = color.ID AND car.Status_ID  = ? ";
           
           ps = con.prepareStatement(sql);
           ps.setInt(1, 2);

           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String name = rs.getString("Name");
               int year = rs.getInt("Year");
               String category = rs.getString("Category");
               int price = rs.getInt("Price");
               int quantity = rs.getInt("Quantity");
               String color = rs.getString("Color");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               
               CarDTO dto = new CarDTO(id, name, year, price, quantity, category, color, date, status);
               if (list == null) list = new ArrayList<>();
               list.add(dto);
           }
           return list;
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public List<CarDTO> getCarListForAdmin() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarDTO> list = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT car.ID, Name, Year, category.Category, Price, Quantity, color.Color, Date, Status_ID "
                   + "FROM car,category,color "
                   + "WHERE car.Category_ID = category.ID AND Color_ID = color.ID ";
           
           ps = con.prepareStatement(sql);

           rs = ps.executeQuery(); 
           
           while(rs.next()){
               int id = rs.getInt("ID");
               String name = rs.getString("Name");
               int year = rs.getInt("Year");
               String category = rs.getString("Category");
               int price = rs.getInt("Price");
               int quantity = rs.getInt("Quantity");
               String color = rs.getString("Color");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               
               CarDTO dto = new CarDTO(id, name, year, price, quantity, category, color, date, status);
               if (list == null) list = new ArrayList<>();
               list.add(dto);
           }
           return list;
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }    

    public CarDTO getCarByID(int Id) throws NamingException, SQLException{ // đang sai nha
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT car.ID, Name, Year, category.Category, Price, Quantity, color.Color, Date, Status_ID "
                   + "FROM car,category,color "
                   + "WHERE car.Category_ID = category.ID AND Color_ID = color.ID "           
                   + "AND Status_ID = ? AND car.ID = ? ";
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, 2);
           ps.setInt(2, Id);
           
           rs = ps.executeQuery(); 
           
           if (rs.next()){
               int id = rs.getInt("ID");
               String name = rs.getString("Name");
               int year = rs.getInt("Year");
               String category = rs.getString("Category");
               int price = rs.getInt("Price");
               int quantity = rs.getInt("Quantity");
               String color = rs.getString("Color");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               
               CarDTO dto = new CarDTO(id, name, year, price, quantity, category, color, date, status);
               return dto;
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return null;
    }  
    
    public CarDTO getCarByName(String Name) throws NamingException, SQLException{ // đang sai nha
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT car.ID, Name, Year, category.Category, Price, Quantity, color.Color, Date, Status_ID "
                   + "FROM car,category,color "
                   + "WHERE car.Category_ID = category.ID AND Color_ID = color.ID "           
                   + "AND Status_ID = ? AND car.Name = ? ";
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, 2);
           ps.setString(2, Name);
           
           rs = ps.executeQuery(); 
           
           if (rs.next()){
               int id = rs.getInt("ID");
               String name = rs.getString("Name");
               int year = rs.getInt("Year");
               String category = rs.getString("Category");
               int price = rs.getInt("Price");
               int quantity = rs.getInt("Quantity");
               String color = rs.getString("Color");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               
               CarDTO dto = new CarDTO(id, name, year, price, quantity, category, color, date, status);
               return dto;
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return null;
    }    
    
    public boolean updateCarByID(String id, int price, int quantity, int cat_id, int color_id, int status_id) throws NamingException, SQLException{ // đang sai nha
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "UPDATE car "
                   + "SET Price = ?, Quantity = ?, Category_ID = ?, Color_ID = ?, Status_ID = ? "
                   + "WHERE ID = ? ";
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, price);
           ps.setInt(2, quantity);
           ps.setInt(3, cat_id);
           ps.setInt(4, color_id);
           ps.setInt(5, status_id);
           ps.setString(6, id);
           
           int result = ps.executeUpdate();
           if (result > 0){
               return true;
           }
           
        }
        
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return false;
    }
    
    public boolean updateCarByID(int quantity, int id) throws NamingException, SQLException{ // đang sai nha
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "UPDATE car "
                   + "SET Quantity = ? "
                   + "WHERE ID = ? ";
           ps = con.prepareStatement(sql);
           
           ps.setInt(1, quantity);
           ps.setInt(2, id);
           
           int result = ps.executeUpdate();

           return result > 0;
        }
        
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }    
    
    public boolean createCar(String name, int year, int category_id, int price, int quantity, int color_id, int status_id) throws NamingException, SQLException{ // đang sai nha
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "INSERT INTO car(Name,Year,Category_ID,Price,Quantity,Color_ID,Date,Status_ID) "
                   + "VALUES (?,?,?,?,?,?,?,?) ";
           ps = con.prepareStatement(sql);
           
           ps.setString(1, name);
           ps.setInt(2, year);
           ps.setInt(3, category_id);
           ps.setInt(4, price);
           ps.setInt(5, quantity);
           ps.setInt(6, color_id);
           ps.setString(7, tuanlm.utils.Date_Utils.getNow());
           ps.setInt(8, status_id);
           
           int result = ps.executeUpdate();
           if (result > 0){
               return true;
           }
           
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return false;
    } 

    public boolean checkCarNameDuplicate(String name) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT ID,Name,Year,Category_ID,Price,Quantity,Color_ID,Date,Status_ID "
                   + "FROM car "
                   + "WHERE Name = ? ";
           
           ps = con.prepareStatement(sql);
           ps.setString(1, name);

           rs = ps.executeQuery(); 
           
           return rs.next();
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }     
}
