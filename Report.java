/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
import static assignment.ProductDrive.choiceY;
import static assignment.ProductDrive.productMenu;
import static assignment.ProductDrive.validChoiceY;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Report {

    private static final String PRODUCT_FILE_PATH = "product.txt";

    public static void generateReport() {
        List<Product> products = loadAllProducts();
        if (products != null && !products.isEmpty()) {
            displayProductReport(products);
        } else {
            System.out.println("No products found.");
        }
    }

    private static List<Product> loadAllProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    
                    String productId = parts[0];
                    String productName = parts[1];
                    double unitCost = Double.parseDouble(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    int quantityInStock = Integer.parseInt(parts[4].trim());
                    String categoryId = parts[5];
                    String supplierId = parts[6];
                    

                    // Create Category and Supplier objects using parts[5] and parts[6]
                    Category category = CategoryDrive.getCategoryById(categoryId);
                    Supplier supplier = SupplierDrive.getSupplierById(supplierId);
                    
                    // Create a Product object from the file data
                    Product product = new Product(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), category, supplier);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return products;
    }

    private static void displayProductReport(List<Product> products) {
        
     Scanner scan = new Scanner(System.in);
     
     int choice;
     boolean validChoice = false;
     
    // Define column widths
    final int ID_WIDTH = 15;
    final int NAME_WIDTH = 30;
    final int QUANTITY_WIDTH = 10;
    final int CATEGORY_WIDTH = 15;
    final int SUPPLIER_WIDTH = 15;

    // Print header with ASCII art
    System.out.println("\n\tIIIIII  NN     NN   VV      VV  EEEEEE  NN     NN  TTTTTT   OOOOO    RRRRR   YY    YY");
    System.out.println("\t  II    NN N   NN    VV    VV   EE      NN N   NN    TT    OO   OO   RR  RR   YY  YY");
    System.out.println("\t  II    NN  N  NN     VV  VV    EEEEEE  NN  N  NN    TT    OO   OO   RR RR     YYYY");
    System.out.println("\t  II    NN   N NN      VVVV     EE      NN   N NN    TT    OO   OO   RRR        YY");
    System.out.println("\tIIIIII  NN    NNN       VV      EEEEEE  NN    NNN    TT     OOOOO    RR RR      YY");
    System.out.println("\n");
    System.out.println("\t\t\tRRRRR    EEEEEE   PPPPP    OOOOO    RRRRR   TTTTTT");
    System.out.println("\t\t\tRR  RR   EE       PP  PP  OO   OO   RR  RR    TT");
    System.out.println("\t\t\tRR RR    EEEEEE   PP PP   OO   OO   RR RR     TT");
    System.out.println("\t\t\tRRR      EE       PPP     OO   OO   RRR       TT");
    System.out.println("\t\t\tRR RR    EEEEEE   PP       OOOOO    RR        TT\n\n");
    
    // Print table header
        System.out.println("=====================================================================================================");
        System.out.printf("| %-"+ID_WIDTH+"s | %-"+NAME_WIDTH+"s | %"+QUANTITY_WIDTH+"s | %-"+CATEGORY_WIDTH+"s | %-"+SUPPLIER_WIDTH+"s |%n",
            "Product ID", "Product Name", "Quantity", "Category", "Supplier");
        System.out.println("|".repeat(ID_WIDTH + NAME_WIDTH + QUANTITY_WIDTH + CATEGORY_WIDTH + SUPPLIER_WIDTH + 11).replace("||", "+-").replace("|", "-") + "+");

        // Print product rows
        for (Product product : products) {
            System.out.printf("| %-"+ID_WIDTH+"s | %-"+NAME_WIDTH+"s | %"+QUANTITY_WIDTH+"d | %-"+CATEGORY_WIDTH+"s | %-"+SUPPLIER_WIDTH+"s |%n",
                product.getProdId(),
                product.getProdName(),
                product.getQuantityInStock(),
                product.getCategoryName(),
                product.getSupplierName());
        }

        // Print table footer
        System.out.println("=====================================================================================================");
        
        
        
        // Let user choose to back admin menu 

        System.out.println("\n\n\t  Back to Admin Menu?(Y)");

        do {
            System.out.print("\t  : ");

            try {
                choice = scan.next().toUpperCase().charAt(0);

                if (choice == 'Y') {
                    validChoice = true;
                    scan.nextLine();
                    Home.clearScreen();
                    Administrator.adminMenu(scan);
                } else {
                    validChoiceY = false;
                    System.out.print("\t      ---  Invalid choice!!! Please enter (Y)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoiceY = false;

                System.out.print("\t      ---  Invalid choice!!! Please enter (Y)...\n\n");

            }
        } while (!validChoiceY);

    }
    
  }


