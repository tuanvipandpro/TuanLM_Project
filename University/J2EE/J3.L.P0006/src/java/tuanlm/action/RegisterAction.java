/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuanlm.account.AccountDAO;
import tuanlm.utils.Hashing_Utils;

/**
 *
 * @author Tuan
 */
public class RegisterAction extends ActionSupport {
    static Logger logger = Logger.getLogger(RegisterAction.class.getName());
    private String email,password,confirm,name,phone,address;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    @Override
    public void validate(){
        if (!phone.matches("[0-9]{10}")){
            addFieldError("phone", "Phone format isn't correct !");
        }
    }    
    
    public RegisterAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try{
            AccountDAO dao = new AccountDAO();

            String hashPassword = Hashing_Utils.Encode_SHA256(password);

            boolean result = dao.registerAccount(email, hashPassword, phone, name, address);

            if (result) url = SUCCESS;            
        }
        catch(NamingException e){
            logger.error("RegisterAction_NamingException : " + e.getMessage());
        }
        catch(NoSuchAlgorithmException e){
            logger.error("RegisterAction_NoSuchAlgorithmException : " + e.getMessage());
        }
        catch(SQLException e){
            logger.error("RegisterAction_SQLException : " + e.getMessage());
        }
        catch(UnsupportedEncodingException e){
            logger.error("RegisterAction_UnsupportedEncodingException : " + e.getMessage());
        }
        finally{
            return url;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
