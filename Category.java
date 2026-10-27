/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class Category {

    // Private variables to store the ID and name of the category
    private String categoryId;
    private String categoryName;

    // Static variable to generate unique category IDs
    public static int nextCatCount = 1000;

    // Constructor that initializes the category with a generated ID and name
    public Category(String cName) {
        this.categoryId = "C" + nextCatCount++;
        this.categoryName = cName;
    }

    // Getters and setters 
    public String getCatId() {
        return categoryId;
    }

    public void setCatId(String categoryId) {
        // Ensure it starts with 'C' and followed by digits
        if (categoryId != null && categoryId.matches("C\\d+")) {
            this.categoryId = categoryId;
        } else {
            System.out.println("Invalid Category ID format.");
        }
    }

    public String getCatName() {
        return categoryName;
    }

    public void setCatName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Get the next category count for category if ("C" + count)
    public static int getNextCatCount() {
        return nextCatCount;
    }

    // Convert category details to a formatted string for saving in file
    @Override
    public String toString() {
        return categoryId + "|" + categoryName;
    }

}
