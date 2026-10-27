/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */


import java.time.LocalDate;

public class Order {
    
    //Variables to store the ID, date, quantity, order amount and the product for order 
    private String orderId;
    private LocalDate orderDate;
    private int quantity;
    private Product product;
    private double orderAmount;

    public static int nextOrderCount = 1000;
    

    // Constructor that initializes the order with a generated ID, date, quantity, product, and total order amount
    public Order(LocalDate orderDate, Product product, int quantity, double orderAmount) {
        
        this.orderId = "OD" + nextOrderCount++;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.product = product;  
        this.orderAmount = orderAmount;
    }

    Order(String orderId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Get Method 
    // Getter method for the id of the order
    public String getOrderId() {
        return orderId;
    }
    
    // Getter method for the date of the order
    public LocalDate getOrderDate(){
        return orderDate;
    }

    public Product getProduct() {
        return product;
    }
    
    // Getter method for the quantity of the order
    public int getQuantity() {
        return quantity;
    }
    
    // Getter method for the total amount of the order
    public double getOrderAmount() {
        return orderAmount;
    }

    // Get the next order count for order id ( "OD" + count)
    public static int getNextOrderCount() {
        return nextOrderCount;
    }

    // Setter Method
    //Setter method to set id of order (ensure orderId follow the format)
    public void setOrderId(String odId) {
        if (odId != null && odId.matches("OD\\d+")) { // Ensure it starts with 'OD' and followed by digits
            this.orderId = odId;
        } else {
            System.out.println("Invalid Order ID format.");
        }
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    // Setter method to set the quantity of order
    public void setQuantity(int qty) {
        this.quantity = qty;
    }
    
    // Setter method to set the amount of order
    public void setOrderAmount(double odAmount) {
        this.orderAmount = odAmount;
    }

    @Override
    // Convert product details to a formatted string for saving to a file
    public String toString() {
        return orderId + "|" + orderDate + "|" + product.getProdId() + "|" + product.getUnitCost() + "|" + quantity + "|" + orderAmount;
    }
}