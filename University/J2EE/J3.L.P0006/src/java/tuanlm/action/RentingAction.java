/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.renting.RentingDAO;
import tuanlm.renting.RentingDTO;

/**
 *
 * @author Tuan
 */
public class RentingAction {
    static Logger logger = Logger.getLogger(RentingAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String pk;
    private List<RentingDTO> details;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
    
    public List<RentingDTO> getDetails() {
        return details;
    }
    
    public RentingAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            RentingDAO dao = new RentingDAO();
            details = dao.getDetailsForBill(pk);
            url = SUCCESS;
        }
        catch(SQLException e){
            logger.error("RentingAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("RentingAction_NamingException : " + e.getMessage());
        }    
        finally{
            return url;
        }
    }
    
}
