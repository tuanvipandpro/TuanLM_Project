/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import tuanlm.car.CarDAO;
import tuanlm.car.CarDTO;
import tuanlm.category.CategoryDAO;
import tuanlm.color.ColorDAO;

/**
 *
 * @author Tuan
 */
public class HomeAction {
    static Logger logger = Logger.getLogger(HomeAction.class.getName());
    private final String SUCCESS = "success";
    private List<CarDTO> list;
    
    public HomeAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        
        try{
            CarDAO dao = new CarDAO();
            list = dao.getCarList();
            
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session != null){
                session.setAttribute("COLOR", new ColorDAO().loadColor());
                session.setAttribute("CATEGORY", new CategoryDAO().loadCategory());
                url = SUCCESS;
            }              
        }
        catch(NamingException e){
            logger.error("HomeAction_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("HomeAction_SQLException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public List<CarDTO> getList() {
        return list;
    }
}
