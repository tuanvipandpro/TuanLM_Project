/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import tuanlm.utils.DB_Utils;

/**
 *
 * @author Tuan
 */
public class AccountDAO implements Serializable{
    public AccountDTO checkLogin(String email, String password) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT Email,Password,Phone,Name,Address,Date,Status_ID,Role_ID "
                   + "FROM account "
                   + "WHERE Email = ? AND Password = ? ";
                   //+ "AND Status_ID = ?";
           ps = con.prepareStatement(sql);
           ps.setString(1, email);
           ps.setString(2, password);
           //ps.setInt(3, 2);
           rs = ps.executeQuery(); 
           
           if (rs.next()){
               String mail = rs.getString("Email");
               String phone = rs.getString("Phone");
               String name = rs.getString("Name");
               String address = rs.getString("Address");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               int role = rs.getInt("Role_ID");
               
               AccountDTO dto = new AccountDTO(mail, phone, name, address, date, status, role);
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

    public boolean registerAccount(String email, String password, String phone, String name, String address) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "INSERT INTO account (Email,Password,Phone,Name,Address,Date,Status_ID,Role_ID) "
                       + "VALUES(?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, email);
//            String encodePassword = Hashing_Utils.Encode_SHA256(password);
            ps.setString(2, password);
            ps.setString(3, phone);
            ps.setString(4, name);
            ps.setString(5, address);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, 1); //status 1:new, 2:active
            ps.setInt(8, 1); //role 1:member, 2:admin
            
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

    public boolean verifyAccount(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "UPDATE account "
                       + "SET Status_ID = ? "
                       + "WHERE Email = ?";
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, 2);
            ps.setString(2, email);
            
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

    public boolean setVerifyCodeForEmail(String email, String code) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "UPDATE account "
                       + "SET Verify_Code = ? "
                       + "WHERE Email = ?";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, code);
            ps.setString(2, email);
            
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

    public String getVerifyCodeForEmail(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "SELECT Verify_Code "
                       + "FROM account "
                       + "WHERE Email = ?";
            ps = con.prepareStatement(sql);
            

            ps.setString(1, email);
            
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString("Verify_Code");
            }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return null;
    }

    public AccountDTO checkGoogleAccount(String email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT Email,Password,Phone,Name,Address,Date,Status_ID,Role_ID "
                   + "FROM account "
                   + "WHERE Email = ?";
           
           ps = con.prepareStatement(sql);
           ps.setString(1, email);

           rs = ps.executeQuery(); 
           
           if (rs.next()){
               String mail = rs.getString("Email");
               String phone = rs.getString("Phone");
               String name = rs.getString("Name");
               String address = rs.getString("Address");
               String date = rs.getString("Date");
               int status = rs.getInt("Status_ID");
               int role = rs.getInt("Role_ID");
               
               AccountDTO dto = new AccountDTO(mail, phone, name, address, date, status, role);
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
    
    public boolean insertGoogleAccount(String email, String phone, String name, String address) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "INSERT INTO account (Email,Password,Phone,Name,Address,Date,Status_ID,Role_ID) "
                       + "VALUES(?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, email);
            ps.setString(2, null);
            ps.setString(3, phone);
            ps.setString(4, name);
            ps.setString(5, address);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, 2); //status 1:new, 2:active
            ps.setInt(8, 1); //role 1:member, 2:admin
            
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

    public boolean checkEmailDup(String email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "SELECT Email,Password,Phone,Name,Address,Date,Status_ID,Role_ID "
                   + "FROM account "
                   + "WHERE [Password] IS NOT NULL AND Email = ? ";
           
           ps = con.prepareStatement(sql);
           ps.setString(1, email);

           rs = ps.executeQuery(); 
           
           if (rs.next()){
               return true;
           }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return false;
    }

    public boolean changePassword(String email, String newPassword) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DB_Utils.makeConnection();
            String sql = "UPDATE account "
                       + "SET Password = ? "
                       + "WHERE Email = ?";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, newPassword);
            ps.setString(2, email);
            
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
}
