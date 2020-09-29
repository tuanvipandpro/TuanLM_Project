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
import tuanlm.car.CarDAO;
import tuanlm.car.CarDTO;
import tuanlm.cart.CartObj;
import tuanlm.utils.Date_Utils;

/**
 *
 * @author Tuan
 */
public class BuyAction {
    static Logger logger = Logger.getLogger(BuyAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int car_id;
    private String from, to;

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
    
    public BuyAction() {
        
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            if (!Date_Utils.isValidRent(from, to)){
                return url;
            }
            
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session != null){
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart == null) cart = new CartObj();
                
                CarDAO dao = new CarDAO();
                CarDTO dto = dao.getCarByID(car_id);
                dto.setFrom(from);
                dto.setTo(to);
                
                cart.addToCart(dto);
                
                session.setAttribute("CART", cart);
                session.setAttribute("TOTAL", cart.totalBill());
                url = SUCCESS;
            }

        }
        catch(ParseException e){
            logger.error("BuyAction_ParseException : " + e.getMessage());
            url = FAIL;
        }
        catch(NamingException e){
            logger.error("BuyAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("BuyAction_SQLException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
