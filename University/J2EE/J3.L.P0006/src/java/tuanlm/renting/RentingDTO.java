/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.renting;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
public class RentingDTO implements Serializable{
    private int id;
    private String name, rental_date, return_date;
    private int quantity;
    private int bill_id;

    public RentingDTO() {
    }

    public RentingDTO(int id, String name, String rental_date, String return_date, int quantity, int bill_id) {
        this.id = id;
        this.name = name;
        this.rental_date = rental_date;
        this.return_date = return_date;
        this.quantity = quantity;
        this.bill_id = bill_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRental_date() {
        return rental_date;
    }

    public void setRental_date(String rental_date) {
        this.rental_date = rental_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }
}
