/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class Product {

    // Variables to store the ID, name, unit cost, price, category, and supplier of the product
    private String productId;
    private String productName;
    private double unitCost;
    private double price;
    private int quantityInStock;
    private Category category;
    private Supplier supplier;

    // Static variable to generate unique product IDs
    public static int nextProdCount = 1000;

    // Constructor that initializes the product with a generated ID, name, unitCost, price, quantity, category, and supplier
    public Product(String productName, double unitCost, double price, int quantityInStock, Category category, Supplier supplier) {

        this.productId = "P" + nextProdCount++;
        this.productName = productName;
        this.unitCost = unitCost;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.supplier = supplier;
    }

    // Getters and setters for private variables
    public String getProdId() {
        return productId;
    }

    public void setProdId(String productId) {
        // Ensure it starts with 'P' and followed by digits (PXXXX)
        if (productId != null && productId.matches("P\\d+")) {
            this.productId = productId;
        } else {
            System.out.println("Invalid Product ID format.");
        }
    }

    public String getProdName() {
        return productName;
    }

    public void setProdName(String productName) {
        this.productName = productName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // Get the next product count for supplier id ( "P" + count)
    public static int getNextProdCount() {
        return nextProdCount;
    }
    
    public String getCategoryName() { 
        return category.getCatName(); 
    }
    
    
    public String getSupplierName() { 
        return supplier.getSupplierName(); 
    }
    

    @Override
    // Convert product details to a formatted string for saving to a file
    public String toString() {
        return productId + "|" + productName + "|" + unitCost + "|" + price + "|" + quantityInStock + "|" + category.getCatId() + "|" + supplier.getSupplierId();
    }
}
