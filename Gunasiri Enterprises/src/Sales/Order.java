/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

/**
 *
 * @author Lenovo
 */
public class Order {
    private String orderID;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private String id;
    private String date;
    private String customerID;
    
      public Order() {
    }
      
      public Order(String orderID,String itemCode,int qty,double unitPrice){
          this.orderID = orderID;
          this.itemCode= itemCode;
          this.qty = qty;
          this.unitPrice= unitPrice;
      }
      
      public Order(String id, String date, String customerID){
          this.id= id;
          this.date = date;
          this.customerID =customerID;
      }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
      
      public String getID(){
          return orderID;
      }
      
      public String getCode(){
          return itemCode;
      }
      
      public int getQty(){
          return qty;
      }
      
      public double getUnitPrice(){
          return unitPrice;
      }
      
      public void setID(String id){
          this.orderID= id;
      }
      
      public void setCode(String code){
          this.itemCode= code;
      }
      
      public void setQty(int qty){
          this.qty = qty;
      }
      
      public void setUnitPrice(double price){
          this.unitPrice = price;
      }
}
