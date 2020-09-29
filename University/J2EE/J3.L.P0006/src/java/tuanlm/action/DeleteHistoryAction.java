/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.bill.BillDAO;

/**
 *
 * @author Tuan
 */
public class DeleteHistoryAction {
    static Logger logger = Logger.getLogger(DeleteHistoryAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int pk;
    
    public DeleteHistoryAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            BillDAO dao = new BillDAO();
            boolean result = dao.changeBillStatus(pk+"", "3");
            //3 : InActive
            url = SUCCESS;            
        }
        catch(SQLException e){
            logger.error("DeleteHistoryAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("DeleteHistoryAction_NamingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
    
}
