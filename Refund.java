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

public class Refund {
    // Variables to store the ID, date, the payment for refund, reason
    String refundId;
    LocalDate refundDate;
    Payment payment;
    double refundAmount;
    String reason;
    
    static int nextRefundCount = 1000;

    // Constructor that initializes the refund with a generated ID, date, payment, and order
    public Refund(LocalDate refundDate, Payment payment, String reason, double refundAmount) {
        this.refundId = "RF" + nextRefundCount++;
        this.payment = payment;
        this.refundDate = LocalDate.now(); // Automatically sets the return date to the current date
        this.reason = reason;
        this.refundAmount = refundAmount;
    }

    // Getters and Setters
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        // Ensure it starts with 'RF' and followed by digits (RFXXXX)
        if (refundId != null && refundId.matches("RF\\d+")) { 
            this.refundId = refundId;
        } else {
            System.out.println("Invalid Refund ID format.");
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getRefundAmount(){
        return refundAmount;
    }
    
    public void setRefundAmount(Payment payment){
        this.refundAmount = payment.getPaymentAmount();
    }
    
    // Get the next refund count for refund id ( "RF" + count)
    public static int getNextRefundCount() {
        return nextRefundCount;
    }
    
    // Convert product details to a formatted string for saving to a file
    public String toString() {
        return refundId + "|" + refundDate + "|" + payment.getPaymentId() + "|" + refundAmount + "|" + reason;
    }
}