/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDTO;
import tuanlm.feedback.FeedbackDAO;

/**
 *
 * @author Tuan
 */
public class FeedbackAction {
    static Logger logger = Logger.getLogger(FeedbackAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int carID,point;
    
    public FeedbackAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            HttpSession session = ServletActionContext.getRequest().getSession(false);

            if (session != null){
                AccountDTO dto = (AccountDTO) session.getAttribute("USER");

                if (dto != null){
                    FeedbackDAO dao = new FeedbackDAO();
                    boolean result = dao.makeFeedback(carID, point, dto.getEmail());
                    if (result) url = SUCCESS;
                }
                //end if
            }            
        }
        catch(SQLException e){
            logger.error("FeedbackAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("FeedbackAction_NamingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
