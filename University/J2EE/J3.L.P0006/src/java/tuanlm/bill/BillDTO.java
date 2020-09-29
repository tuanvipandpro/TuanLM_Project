/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.bill;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
public class BillDTO implements Serializable{
    private int id;
    private String username;
    private float total;
    private String booking_date;
    private int status_id;

    public BillDTO() {
    }

    public BillDTO(int id, String username, float total, String booking_date, int status_id) {
        this.id = id;
        this.username = username;
        this.total = total;
        this.booking_date = booking_date;
        this.status_id = status_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
