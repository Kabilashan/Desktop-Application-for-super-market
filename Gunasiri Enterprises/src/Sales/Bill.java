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
public class Bill {
    private String itemCode;
    private String description;
    private int qty;
    private double unitPrice;
    private String orderId;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Bill(String itemCode,String description,int qty,double unitPrice,double amount){
        this.description= description;
        this.itemCode= itemCode;
        //this.orderId= orderId;
        this.qty= qty;
        this.amount = amount;
        this.unitPrice = unitPrice;
        
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    
}
