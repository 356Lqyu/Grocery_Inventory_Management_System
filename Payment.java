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

public class Payment {
    // Variables to store the ID, date, payment amount and the order for payment
    String paymentId;
    final LocalDate paymentDate;
    Order order;
    double paymentAmount;
    String paymentMethod;
    
    static int nextPaymentCount = 1000;
    
    // Constructor that initializes the payment with a generated ID, date, order, and total payment amount
    public Payment(LocalDate paymentDate, Order order, double paymentAmount, String paymentMethod) {
        this.paymentId = "PM" + nextPaymentCount++;
        this.paymentDate = LocalDate.now(); // Automatically sets the payment date to the current date
        this.order = order;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        // Ensure it starts with 'PM' and followed by digits (PMXXXX)
        if (paymentId != null && paymentId.matches("PM\\d+")) { 
            this.paymentId = paymentId;
        } else {
            System.out.println("Invalid Payment ID format.");
        }
    }
   
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Order order) {
        this.paymentAmount = order.getOrderAmount();
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }   
    
    // Get the next refund count for refund id ( "RF" + count)
    public static int getNextPaymentCount() {
        return nextPaymentCount;
    }
    
    // Convert product details to a formatted string for saving to a file
    public String toString() {
        return paymentId + "|" + paymentDate + "|" + order.getOrderId() + "|" + paymentAmount + "|" + paymentMethod;
    }
}