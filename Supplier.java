/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class Supplier {

    // Private variables to store the ID, name, phoneNo and email of the supplier
    private String supplierId;
    private String supplierName;
    private String supplierPhoneNum;
    private String supplierEmail;

    // Static variable to generate unique supplier IDs
    public static int nextSuppCount = 1000;

    // Constructor that initializes the supplier with a generated ID , name , phone num and email
    public Supplier(String sName, String sPhone, String sEmail) {
        this.supplierId = "SP" + nextSuppCount++;
        this.supplierName = sName;
        this.supplierPhoneNum = sPhone;
        this.supplierEmail = sEmail;
    }

    // Getters and setters for private variables
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String suppId) {
        if (suppId != null && suppId.matches("SP\\d+")) { // Ensure it starts with 'SP' and followed by digits
            this.supplierId = suppId;
        } else {
            System.out.println("Invalid Supplier ID format.");
        }
    }

    // Getter method for the name of the supplier
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String suppName) {
        this.supplierName = suppName;
    }

    public String getSupplierPhoneNum() {
        return supplierPhoneNum;
    }

    public void setSupplierPhoneNum(String suppPhone) {
        supplierPhoneNum = suppPhone;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String suppEmail) {
        supplierEmail = suppEmail;
    }

    // Get the next supplier count for supplier id ( "SP" + count)
    public static int getNextSuppCount() {
        return nextSuppCount;
    }

    @Override
    // Convert supplier details to a formatted string for saving to a file
    public String toString() {
        return supplierId + "|" + supplierName + "|" + supplierPhoneNum + "|" + supplierEmail;
    }

}
