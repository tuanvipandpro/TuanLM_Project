/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDAO;
import tuanlm.account.AccountDTO;
import tuanlm.utils.CaptchaUtils;
import tuanlm.utils.Hashing_Utils;

/**
 *
 * @author Tuan
 */
public class LoginAction{
    static Logger logger = Logger.getLogger(LoginAction.class.getName());
    private final String ADMIN = "admin";
    private final String MEMBER = "home";
    private final String FAIL = "fail";
    private final String VERIFY = "verify";
    
    private HttpServletRequest request;
    private String email,password;

    public LoginAction() {
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
      
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            request = ServletActionContext.getRequest();

            String googleCode = request.getParameter("g-recaptcha-response");

            boolean result = CaptchaUtils.verifyCaptcha(googleCode);

            if (!result){
                return url;
            }

            AccountDAO dao = new AccountDAO();
            String hashPassword = Hashing_Utils.Encode_SHA256(password);
            AccountDTO dto = dao.checkLogin(email, hashPassword);

            if (dto != null){   
                HttpSession session = request.getSession();
                session.setAttribute("USER", dto);                

                if (dto.getStatus_id() == 2){
                    //1 Member, 2 Admin
                    if (dto.getRole_id() == 2){
                        url = ADMIN;
                    }
                    else if (dto.getRole_id() == 1){
                        url = MEMBER;
                    }                
                }
                else{
                    url = VERIFY;
                }
            }          
        }
        catch(SQLException e){
            logger.error("LoginAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("LoginAction_NamingException : " + e.getMessage());
        }      
        catch(IOException e){
            logger.error("LoginAction_IOException : " + e.getMessage());
        }
        catch(NoSuchAlgorithmException e){
            logger.error("LoginAction_NoSuchAlgorithmException: " + e.getMessage());
        }
        finally{
            return url;
        }
    }
}
