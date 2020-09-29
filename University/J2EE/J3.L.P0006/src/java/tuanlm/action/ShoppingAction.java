/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.car.CarDAO;
import tuanlm.car.CarDTO;
import tuanlm.cart.CartObj;

/**
 *
 * @author Tuan
 */
public class ShoppingAction {
    static Logger logger = Logger.getLogger(ShoppingAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String btAction;
    private int quantity;
    private int car_id;
    private String from,to;
    
    public ShoppingAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            CartObj cart = (CartObj) session.getAttribute("CART");
            CarDAO dao = new CarDAO();
            
            if (cart != null){
                CarDTO dto = dao.getCarByID(car_id);
                dto.setFrom(from); dto.setTo(to);
                
                if (btAction.equals("Delete")){
                    cart.removeMenu(dto);
                    url = SUCCESS;
                }
                else if (btAction.equals("Update")){
                    cart.updateAmountCart(dto, quantity);
                    url = SUCCESS;
                }               
            }
            
            if (cart.getCart() == null){
                session.removeAttribute("CART");
                session.removeAttribute("TOTAL");
            }
            else{
                session.setAttribute("CART", cart);
                session.setAttribute("TOTAL", cart.totalBill());                
            }
        }
        catch(SQLException e){
            logger.error("ShoppingAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("ShoppingAction_NamingException : " + e.getMessage());
        }          
        finally{
           return url;
        }
    }

    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
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
