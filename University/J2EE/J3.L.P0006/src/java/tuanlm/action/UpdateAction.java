/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.car.CarDAO;
import tuanlm.category.CategoryDAO;
import tuanlm.color.ColorDAO;

/**
 *
 * @author Tuan
 */
public class UpdateAction {
    static Logger logger = Logger.getLogger(UpdateAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int id,price,quantity;
    private String category, color, status;
    
    public UpdateAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            CarDAO dao = new CarDAO();
            int category_id = new CategoryDAO().getCategoryIdByName(category);
            int color_id = new ColorDAO().getColorIdByName(color);
            int status_id = 1;
            if (status.equals("Active")) status_id = 2;
            else if (status.equals("InActive")) status_id = 3;
            
            boolean result = dao.updateCarByID(id+"", price, quantity, category_id, color_id, status_id);
            if (result) url = SUCCESS;
        }
        catch(SQLException e){
            logger.error("UpdateAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("UpdateAction_NamingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 
}
