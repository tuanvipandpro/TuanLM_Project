/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.feedback;

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
public class FeedbackDAO implements Serializable{
    public boolean makeFeedback(int car_id, int point, String email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
           con = DB_Utils.makeConnection();
           String sql = "INSERT INTO feedback(Car_ID,Point,Email,Date) "
                   + "VALUES (?,?,?,?) ";
           
           ps = con.prepareStatement(sql);
           ps.setInt(1, car_id);
           ps.setInt(2, point);
           ps.setString(3, email);
           ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
           
           int result = ps.executeUpdate();
           
           if (result > 0) return true;
        }
        finally{
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return false;
    } 
    
    public List<FeedbackDTO> getFeedback(String car_id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FeedbackDTO> list = null;
        
        try{
            con = DB_Utils.makeConnection();
            String sql = "SELECT feedback.ID, car.Name, Point, Email, feedback.Date "
                   + "FROM feedback,car "
                   + "WHERE Car_ID = ? AND Car_ID = car.ID ";
           
            ps = con.prepareStatement(sql);

            ps.setString(1, car_id);
           
            rs = ps.executeQuery();
           
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int point = rs.getInt("Point");
                String email = rs.getString("Email");
                String date = rs.getString("Date");
                
                FeedbackDTO dto = new FeedbackDTO(id, name, point, email, date);
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
