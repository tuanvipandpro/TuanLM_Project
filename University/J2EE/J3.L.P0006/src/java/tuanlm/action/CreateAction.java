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
import tuanlm.car.CarDAO;
import tuanlm.category.CategoryDAO;
import tuanlm.color.ColorDAO;

/**
 *
 * @author Tuan
 */
public class CreateAction extends ActionSupport{
    static Logger logger = Logger.getLogger(CreateAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";  
    //các param
    private String carName;
    private int carYear;
    private String carCategory;
    private int carPrice,carQuantity;
    private String carColor;
    
    @Override
    public void validate(){
        CarDAO dao = new CarDAO();
        try{
            if (dao.checkCarNameDuplicate(carName)){
                addFieldError("carName", "This car is exist !");
            }            
        }
        catch(NamingException e){
            logger.error("Valid_CreateAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("Valid_CreateAction_SQLException : " + e.getMessage());
        }
    }     
    
    public CreateAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            int cateID = new CategoryDAO().getCategoryIdByName(carCategory);
            int colorID = new ColorDAO().getColorIdByName(carColor);
            
            CarDAO dao = new CarDAO();
            boolean result = dao.createCar(carName, carYear, cateID, carPrice, carQuantity, colorID, 2);
            if (result) url = SUCCESS;
        }
        catch(NamingException e){
            logger.error("CreateAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("CreateAction_SQLException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(int carQuantity) {
        this.carQuantity = carQuantity;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
}
