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

/**
 *
 * @author Tuan
 */
public class DetailsAction {
    static Logger logger = Logger.getLogger(DetailsAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String carName;
    private CarDTO dto;
    
    public DetailsAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            CarDAO dao = new CarDAO();
            dto = dao.getCarByName(carName);
            url = SUCCESS;
        }
        catch(SQLException e){
            logger.error("DetailsAction_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            logger.error("DetailsAction_NamingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public CarDTO getDto() {
        return dto;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
