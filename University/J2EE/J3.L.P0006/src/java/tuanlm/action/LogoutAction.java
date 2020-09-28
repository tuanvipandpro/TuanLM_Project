/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Tuan
 */
public class LogoutAction implements SessionAware{
    private SessionMap<String, Object> sessionMap;
    private final String SUCCESS = "success";
    
    public LogoutAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        sessionMap = (SessionMap<String, Object>) session;
        
        this.sessionMap.invalidate();
        
        return SUCCESS;
    }

    @Override
    public void setSession(Map map) {
        sessionMap = (SessionMap<String, Object>) map;
    }
    
}
