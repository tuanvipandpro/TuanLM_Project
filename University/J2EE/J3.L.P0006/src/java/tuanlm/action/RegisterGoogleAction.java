/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDAO;
import tuanlm.account.AccountDTO;

/**
 *
 * @author Tuan
 */
public class RegisterGoogleAction extends ActionSupport {
    static Logger logger = Logger.getLogger(RegisterGoogleAction.class.getName());
    private final String SUCCESS = "success";  
    private final String FAIL = "fail";  
    private String googleName;
    private String googleAddress;
    private String googlePhone;
    private String googleEmail;
    
    @Override
    public void validate(){
        if (!googlePhone.matches("[0-9]{10}")){
            addFieldError("googlePhone", "Phone format isn't correct !");
        }
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }
    
    public RegisterGoogleAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            AccountDAO dao = new AccountDAO();
            boolean result = dao.insertGoogleAccount(googleEmail, googlePhone, googleName, googleAddress);
            if (result){
                AccountDTO dto = dao.checkGoogleAccount(googleEmail);
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.setAttribute("USER", dto);
                url = SUCCESS;
            }
        }
        catch(NamingException e){
            logger.error("RegisterGoogleAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("RegisterGoogleAction_SQLException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public String getGoogleAddress() {
        return googleAddress;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getGooglePhone() {
        return googlePhone;
    }

    public void setGooglePhone(String googlePhone) {
        this.googlePhone = googlePhone;
    }
}
