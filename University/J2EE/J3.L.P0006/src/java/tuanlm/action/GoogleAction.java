/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDAO;
import tuanlm.account.AccountDTO;
import tuanlm.utils.GG_Utils;
import tuanlm.utils.GooglePojo;

/**
 *
 * @author Tuan
 */
public class GoogleAction {
    static Logger logger = Logger.getLogger(GoogleAction.class.getName());
    private final String SUCCESS = "success";  
    private final String FAIL = "fail";  
    private final String NEW = "new";  
    private String code;
    
    public GoogleAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            if (code == null || code.isEmpty()){

            }
            else{
                String accessToken = GG_Utils.getToken(code);
                GooglePojo pojo = GG_Utils.getUserInfo(accessToken);

                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.checkGoogleAccount(pojo.getEmail());

                if (dto == null){
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("EMAIL", pojo.getEmail());
                    url = NEW;
                }
                else{
                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.setAttribute("USER", dto);
                    url = SUCCESS;
                }
            }            
        }
        catch(SQLException ex){
            logger.error("GoogleAction_SQLException : " + ex.getMessage());
        }
        catch(NamingException ex){
            logger.error("GoogleAction_NamingException : " + ex.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
