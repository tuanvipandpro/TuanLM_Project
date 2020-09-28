/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDTO;
import tuanlm.bill.BillDAO;
import tuanlm.bill.BillDTO;

/**
 *
 * @author Tuan
 */
public class HistoryAction {
    static Logger logger = Logger.getLogger(HistoryAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private List<BillDTO> list;

    public List<BillDTO> getList() {
        return list;
    }
    
    public HistoryAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session != null){
                AccountDTO user = (AccountDTO) session.getAttribute("USER");

                if (user != null){
                    BillDAO dao = new BillDAO();
                    list = dao.getBillByEmail(user.getEmail());
                    url = SUCCESS;

                }
            }            
        }
        catch(SQLException e){
            logger.error("HistoryAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("HistoryAction_NamingException : " + e.getMessage());
        }         
        finally{
            return url;
        }
    }
    
}
