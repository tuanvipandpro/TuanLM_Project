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
public class ViewCarAction {
    static Logger logger = Logger.getLogger(ViewCarAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";  
    private CarDTO car;
    //param
    private int id;
    
    public ViewCarAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        
        try{
            CarDAO dao = new CarDAO();
            car = dao.getCarByID(id);
            url = SUCCESS;
        }
        catch(NamingException e){
            logger.error("ViewCarAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("ViewCarAction_SQLException : " + e.getMessage());
        }        
        finally{
            return url;
        }
    }
    
    public CarDTO getCar() {
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
