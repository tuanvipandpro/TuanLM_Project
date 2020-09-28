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
public class HistorySearchAction {
    static Logger logger = Logger.getLogger(HistorySearchAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private List<BillDTO> list;
    private String historyType;
    private String historyName;
    private String historyDate;

    public HistorySearchAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session != null){
                AccountDTO user = (AccountDTO) session.getAttribute("USER");
                BillDAO dao = new BillDAO();
                
                if (user != null && historyType.equals("Name")){
                    list = dao.searchBillByName(user.getEmail(), historyName);
                    url = SUCCESS;
                }
                else if (user != null && historyType.equals("Date")){
                    
                    list  = dao.searchBillByDate(user.getEmail(), historyDate);
                    url = SUCCESS;
                } 
            }
        }
        catch(NamingException e){
            logger.error("HistorySearchAction_NamingException : " + e.getMessage());
            url = FAIL;
        }
        catch(SQLException e){
            logger.error("HistorySearchAction_SQLException : " + e.getMessage());
            url = FAIL;
        }
        finally{
            return SUCCESS;
        }
    }

    public List<BillDTO> getList() {
        return list;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public String getHistoryName() {
        return historyName;
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }
}
