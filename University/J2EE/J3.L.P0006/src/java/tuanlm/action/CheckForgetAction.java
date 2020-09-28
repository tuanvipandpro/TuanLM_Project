/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.account.AccountDAO;

/**
 *
 * @author Tuan
 */
public class CheckForgetAction {
    static Logger logger = Logger.getLogger(CheckForgetAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String email,forgetCode;        
    
    public CheckForgetAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            AccountDAO dao = new AccountDAO();
            String correctCode = dao.getVerifyCodeForEmail(email);
            
            if (correctCode.equals(forgetCode)){
                dao.verifyAccount(email);
                url = SUCCESS;
            }             
        }
        catch(SQLException e){
            logger.error("CheckForgetAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("CheckForgetAction_NamingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(String forgetCode) {
        this.forgetCode = forgetCode;
    }    
}
