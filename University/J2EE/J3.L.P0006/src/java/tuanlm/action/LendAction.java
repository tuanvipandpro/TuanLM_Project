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
import tuanlm.car.CarDTO;
import tuanlm.category.CategoryDAO;
import tuanlm.color.ColorDAO;

/**
 *
 * @author Tuan
 */
public class LendAction{
    static Logger logger = Logger.getLogger(LendAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";  
    //các param
    private String carName;
    private int carYear;
    private String carCategory;
    private int carPrice,carQuantity;
    private String carColor;
    
    public LendAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            int cateID = new CategoryDAO().getCategoryIdByName(carCategory);
            int colorID = new ColorDAO().getColorIdByName(carColor);
            
            CarDAO dao = new CarDAO();
            //nếu trùng tăng số
            if (!dao.checkCarNameDuplicate(carName)){
                boolean result = dao.createCar(carName, carYear, cateID, carPrice, carQuantity, colorID, 1);
                if (result) url = SUCCESS;                
            }
            else{
                CarDTO dto = dao.getCarByName(carName);
                if(dao.updateCarByID(dto.getQuantity() + 1, dto.getId())) url = SUCCESS;
            }
        }
        catch(NamingException e){
            logger.error("LendAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("LendAction_SQLException : " + e.getMessage());
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
