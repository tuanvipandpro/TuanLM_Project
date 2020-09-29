/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.account.AccountDTO;
import tuanlm.cart.CartObj;
import tuanlm.sale.SaleDAO;
import tuanlm.sale.SaleDTO;

/**
 *
 * @author Tuan
 */
public class PaymentAction {
    static Logger logger = Logger.getLogger(PaymentAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";    
    private final String OUT = "out";  
    private final String SALE_ERROR = "sale";  
    //param
    private String sale;

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
    
    public PaymentAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            
            if (session != null){
                //lấy cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                AccountDTO user = (AccountDTO) session.getAttribute("USER");
                
                SaleDTO dto = null;
                //nếu có nhập vào ô sale
                if (!sale.isEmpty()) {
                    dto = new SaleDAO().getSale(sale);
                    if (dto == null) return SALE_ERROR;
                }
                
                if (cart != null){
                    //nếu không nhập sale thì dto = null
                    boolean result = cart.paymentCart(user.getEmail(), dto);
                    if (result) url = SUCCESS; 
                    else url = OUT; 
                }
                
                session.removeAttribute("CART");
                session.removeAttribute("TOTAL");                
            }
        }
        catch(SQLException e){
            logger.error("PaymentAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("PaymentAction_NamingException : " + e.getMessage());
        }          
        catch(ParseException e){
            logger.error("PaymentAction_ParseException : " + e.getMessage());
        }     
        finally{
            return url;
        }
    }
    
}
