/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.sale;

import java.io.Serializable;

/**
 *
 * @author Tuan
 */
public class SaleDTO implements Serializable{
    private int id;
    private String code;
    private int sale;
    private String expiry;

    public SaleDTO() {
    }

    public SaleDTO(int id, String code, int sale, String expiry) {
        this.id = id;
        this.code = code;
        this.sale = sale;
        this.expiry = expiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
