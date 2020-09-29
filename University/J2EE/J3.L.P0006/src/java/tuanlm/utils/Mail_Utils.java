/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tuan
 */
public class Mail_Utils implements Serializable{
    public static void sendMail(String from, String to, String passFrom, String host, String subject, String body) throws MessagingException{
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);
        
        User aut = new User(from,passFrom);
        Session session = Session.getInstance(p,aut);  

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(body);
            
        Transport.send(msg);

    }
}

class User extends Authenticator implements Serializable{
    private String Username, Password;

    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(Username, Password);
    }
}
