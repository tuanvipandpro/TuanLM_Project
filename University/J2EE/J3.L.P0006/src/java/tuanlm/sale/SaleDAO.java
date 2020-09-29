/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.sale;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tuanlm.utils.DB_Utils;

/**
 *
 * @author Tuan
 */
public class SaleDAO implements Serializable{
    public SaleDTO getSale(String Code) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = DB_Utils.makeConnection();
            String sql = "SELECT ID, Code, Sale, Expiry_Date "
                    + "FROM sale "
                    + "WHERE Expiry_Date >= ? AND Code = ?";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, tuanlm.utils.Date_Utils.getNow());
            ps.setString(2, Code);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                return new SaleDTO(rs.getInt("ID"),rs.getString("Code"),rs.getInt("Sale"), rs.getString("Expiry_Date"));
            }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();            
        }
        return null;
    } 
    
    public boolean createSale(String code, String sale, String expire) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = DB_Utils.makeConnection();
            String sql = "INSERT INTO sale(Code,Sale,Expiry_Date) "
                    + "VALUES (?,?,?)";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, sale);
            ps.setString(3, expire);
            
            int result = ps.executeUpdate();
            return result > 0;
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();            
        }
    } 
    
}
