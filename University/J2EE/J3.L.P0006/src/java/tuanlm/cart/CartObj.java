/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanlm.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tuanlm.bill.BillDAO;
import tuanlm.car.CarDTO;
import tuanlm.renting.RentingDAO;
import tuanlm.renting.RentingDTO;
import tuanlm.sale.SaleDTO;
import tuanlm.utils.Date_Utils;

/**
 *
 * @author Tuan
 */
public class CartObj implements Serializable{
    private Map<CarDTO,Integer> cart = null;
    private List<CarDTO> availableList; //list khi payment
    
    public CartObj() {
    } 

    public Map<CarDTO, Integer> getCart() {
        return cart;
    }
    
    public void addToCart(CarDTO dto){
        if(cart == null) cart = new HashMap<>();
        int quantity = 1;
        for (CarDTO car : cart.keySet()){
            if (car.getName().equals(dto.getName())) {
                //nếu trùng ngày thì tăng, không thì thêm xe mới
                if (car.getFrom().equals(dto.getFrom()) && car.getTo().equals(dto.getTo())){
                    quantity = cart.get(car) + 1;
                    cart.put(car,quantity); 
                    return;
                }
            }
        }
        cart.put(dto, quantity);
    }

    public void updateAmountCart(CarDTO dto, int amount){
        if(cart == null) cart = new HashMap<>();
        for (CarDTO car : cart.keySet()){
            if (car.getName().equals(dto.getName())) {
                //nếu trùng ngày thì cho update
                if (car.getFrom().equals(dto.getFrom()) && car.getTo().equals(dto.getTo())){
                    cart.put(car,amount); 
                    return;
                }
            }
        }
    }  
    
    public void removeMenu(CarDTO dto){
        if (cart == null) {
            
        }
        else{           
            Iterator<CarDTO> ite = cart.keySet().iterator();
            while(ite.hasNext()){
               CarDTO car = ite.next();
               if (car.getName().equals(dto.getName())) {
                   if (car.getFrom().equals(dto.getFrom()) && car.getTo().equals(dto.getTo())) ite.remove();
               }
            }
            if (cart.isEmpty()) {
                cart = null;
            }
        }
    }
    
    public float totalBill() throws ParseException{
        float total = 0;
        if (cart == null) {
            return 0;
        }
        else{
            for (CarDTO dto : cart.keySet()){
                
                int rentingDay = Date_Utils.amountDay(dto.getFrom(), dto.getTo());
                total = total + (dto.getPrice() * rentingDay * cart.get(dto));
            }
            return total;
        }
    }

    public boolean paymentCart(String username, SaleDTO sale) throws ParseException, NamingException, SQLException{
        //kiểm tra xem có thể thanh toán hay không 
        if (canPayment()){
            float total = totalBill(); //tổng bill
            if (sale != null) total = (total * sale.getSale())/100;            
            Timestamp now = new Timestamp(System.currentTimeMillis()); //thời gian thanh toán
            
            BillDAO dao = new BillDAO();
            
            boolean result;
            
            if (sale != null) result = dao.createNewBill(username, now, total, sale.getId() + "");
            else result = dao.createNewBill(username, now, total, null);
            
            if (result){
                int id = dao.getIDByEmailAndDate(username, now);
                
                RentingDAO rent_dao =  new RentingDAO();
                for (CarDTO dto : cart.keySet()){
                    if (!rent_dao.createDetailBill(dto.getId()+"", dto.getFrom(), 
                            dto.getTo(), cart.get(dto),id)) 
                        return false;
                }

                cart = null;
                availableList = null;
                return true;                
            }
        }
        return false;
    }
    
    boolean canPayment() throws NamingException, SQLException, ParseException{
        if (availableList == null) availableList = new ArrayList<>();
        
        //duyệt cart
        for (CarDTO dto : cart.keySet()){
            //kiểm tra từng xe trong giỏ có thể thanh toán hay không
            if (!checkQuantityCar(dto, cart.get(dto))) return false;
        }        
        
        return true;
    }
    
    boolean checkQuantityCar(CarDTO dto, int amount) throws NamingException, SQLException, ParseException{
        //kiểm tra xe này có còn hay không
        //amount là quantity trong giỏ
        int quantity = quantityCarInBill(dto,dto.getQuantity()); //duyệt qua availeble list trước
        
        RentingDAO dao = new RentingDAO();
        List<RentingDTO> list = dao.getDetailsToCheck(dto.getId()+"");
        
        if (list == null) {
            //list null, xe chưa có ai thuê, được thuê thoải mái
            if (quantity >= amount){
                availableList.add(dto);
                return true;
            }
            return false;
        }
        
        
        for (RentingDTO rent : list){
            if ((Date_Utils.compareTime(dto.getFrom(), rent.getReturn_date()) > 0) || 
                    (Date_Utils.compareTime(dto.getTo(), rent.getRental_date()) < 0)){
                
            }
            else{
                quantity = quantity - rent.getQuantity();
            }
        //hết vòng for-each
        }
        
        if (quantity >= amount) {
            availableList.add(dto);
            return true;
        }
        else return false;
    }
    
    int quantityCarInBill(CarDTO car, int quantity) throws ParseException{ 
        //số quantity car trong available list
        //nếu availableList empty, foreach không chạy
        for (CarDTO dto : availableList){
            if (car.getId() == dto.getId()){
                if ((Date_Utils.compareTime(car.getFrom(), dto.getTo()) > 0) || 
                        (Date_Utils.compareTime(car.getTo(), dto.getFrom()) < 0)){
                    
                }
                else{
                    quantity = quantity - cart.get(dto);
                }                  
            }
        }
        
        return quantity;
    }
}
