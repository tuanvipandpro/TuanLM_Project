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
import tuanlm.bill.BillDAO;
import tuanlm.bill.BillDTO;

/**
 *
 * @author Tuan
 */
public class ManagerAction {
    static Logger logger = Logger.getLogger(ManagerAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private List<BillDTO> manager;   
    //param
    
    
    public ManagerAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            BillDAO dao = new BillDAO();
            manager = dao.getAllBill();
            url = SUCCESS;
        }
        catch(NamingException e){
            logger.error("ManagerAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("ManagerAction_SQLException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public List<BillDTO> getManager() {
        return manager;
    }
}
