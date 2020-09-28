/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.account;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
public class AccountDTO implements Serializable{
    private String email,phone,name,address,date;
    private int status_id, role_id;

    public AccountDTO(String email, String phone, String name, String address, String date, int status_id, int role_id) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.date = date;
        this.status_id = status_id;
        this.role_id = role_id;
    }

    public AccountDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
