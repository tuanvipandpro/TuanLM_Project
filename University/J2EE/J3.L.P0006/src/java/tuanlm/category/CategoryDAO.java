/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.category;

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
public class CategoryDAO implements Serializable{
    public List<String> loadCategory() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = null;

        try{
            con = DB_Utils.makeConnection();
            String sql = "SELECT ID,Category "
                    + "FROM category";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                if (list == null) list = new ArrayList<>();
                list.add(rs.getString("Category"));
            }
        }
        finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();            
        }
        return list;
    }

    public int getCategoryIdByName(String category) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try{
            con = DB_Utils.makeConnection();
            String sql = "SELECT ID,Category "
                    + "FROM category "
                    + "WHERE Category = ?";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();
            
            if (rs.next()){
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
