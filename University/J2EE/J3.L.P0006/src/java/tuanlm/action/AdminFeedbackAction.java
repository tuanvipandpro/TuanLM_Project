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
import tuanlm.feedback.FeedbackDAO;
import tuanlm.feedback.FeedbackDTO;

/**
 *
 * @author Tuan
 */
public class AdminFeedbackAction {
    static Logger logger = Logger.getLogger(AdminFeedbackAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";  
    
    //param
    private List<FeedbackDTO> feedback;
    private int id;
    private String name;
    
    public AdminFeedbackAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            FeedbackDAO dao = new FeedbackDAO();
            feedback = dao.getFeedback(id+"");
            //name = feedback.get(1).getCar_name();
            url = SUCCESS;
        }
        catch(NamingException e){
            logger.error("AdminFeedbackAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("AdminFeedbackAction_SQLException : " + e.getMessage());
        }          
        finally{
            return url;
        }
    }

    public List<FeedbackDTO> getFeedback() {
        return feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
