/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.feedback;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
public class FeedbackDTO implements Serializable{
    private int id;
    private String car_name;
    private int point;
    private String email,date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int id, String car_name, int point, String email, String date) {
        this.id = id;
        this.car_name = car_name;
        this.point = point;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
