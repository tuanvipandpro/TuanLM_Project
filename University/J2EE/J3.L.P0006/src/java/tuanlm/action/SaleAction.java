/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.sale.SaleDAO;

/**
 *
 * @author Tuan
 */
public class SaleAction extends ActionSupport{
    static Logger logger = Logger.getLogger(SaleAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String DUPLICATE = "dup";
    //param
    private String code,sale,expired;

    @Override
    public void validate() {
        try{
            SaleDAO dao = new SaleDAO();
            if (dao.getSale(code) != null){
                addFieldError("code", "Code is exist !");
            }
        }
        catch(NamingException e){
            logger.error("SaleAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("SaleAction_SQLException : " + e.getMessage());
        }  
    }

    public SaleAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            SaleDAO dao = new SaleDAO();
            if (dao.createSale(code, sale, expired)) url = SUCCESS;
        }
        catch(NamingException e){
            logger.error("SaleAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("SaleAction_SQLException : " + e.getMessage());
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

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    } 
}
