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
public class VerifyAction {
    static Logger logger = Logger.getLogger(VerifyAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String email;
    private String code;
    
    public VerifyAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            //lấy code trong database ra kiểm tra\
            AccountDAO dao = new AccountDAO();
            String correctCode = dao.getVerifyCodeForEmail(email);

            if (correctCode.equals(code)){
                dao.verifyAccount(email);
                url = SUCCESS;
            }            
        }
        catch(NamingException e){
            logger.error("VerifyAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("VerifyAction_SQLException : " + e.getMessage());
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
