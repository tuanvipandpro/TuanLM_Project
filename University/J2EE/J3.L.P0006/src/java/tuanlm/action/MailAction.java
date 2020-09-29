/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.account.AccountDAO;
import tuanlm.utils.Mail_Utils;

/**
 *
 * @author Tuan
 */
public class MailAction {
    static Logger logger = Logger.getLogger(MailAction.class.getName());
    private final String SUCCESS = "success";
    private String email;
    
    public MailAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        
        try{
            Random r = new Random();
            int code = r.nextInt(10000);
            if (code < 1000) code = code + 1000;

            Mail_Utils.sendMail("tuanvipandpro@gmail.com",email,"Tuanvip123","localhost","Verify Code !",code+"");
            //ghi code vào database
            AccountDAO dao = new AccountDAO();
            dao.setVerifyCodeForEmail(email, code+"");            
        }
        catch(MessagingException e){
            logger.error("MailAction_MessagingException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("MailAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("MailAction_SQLException : " + e.getMessage());
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
}
