/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.account.AccountDAO;
import tuanlm.utils.Hashing_Utils;

/**
 *
 * @author Tuan
 */
public class ChangeAction extends ActionSupport {
    static Logger logger = Logger.getLogger(ChangeAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String email, password, confirm;
    
    
    @Override
    public void validate(){
        if (password.isEmpty()){
            addFieldError("password", "Password is empty !");
        }
        else if (!confirm.equals(password)){
            addFieldError("confirm", "Confirm is not like password !");
        }
    }    
    
    public ChangeAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            AccountDAO dao = new AccountDAO();
            boolean result = dao.changePassword(email, Hashing_Utils.Encode_SHA256(password));
            if (result){
                url = SUCCESS;
            }
        }
        catch(NamingException e){
            logger.error("ChangeAction_NamingException : " + e.getMessage());
        }
        catch(NoSuchAlgorithmException e){
            logger.error("ChangeAction_NoSuchAlgorithmException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("ChangeAction_SQLException : " + e.getMessage());
        }
        catch(UnsupportedEncodingException e){
            logger.error("ChangeAction_UnsupportedEncodingException: " + e.getMessage());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
