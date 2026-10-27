/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
import static assignment.CategoryDrive.loadCategoryData;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
public class ProductDrive {

    private static Product[] addProductList;
    private static Product[] viewProductList;
    private static Product[] modifyProductList;
    private static Product[] deleteProductList;

    private static final int MAX_PRODUCT = 100;

    public static char choiceY;
    static boolean validChoiceY = false;

    //Scanner
    private static final Scanner scan = new Scanner(System.in);

    // Product Menu
    public static void productMenu() {

        Home.clearScreen();

        try {
            File myProd = new File("product.txt");
            if (myProd.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + myProd.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
            e.printStackTrace();
        }

        System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                          PRODUCT MENU                         |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. ADD Product                                          |");
        System.out.println("\t\t|       2. VIEW Product                                         |");
        System.out.println("\t\t|       3. UPDATE Product Info                                  |");
        System.out.println("\t\t|       4. DELETE Product                                       |");
        System.out.println("\t\t|       5. Back                                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        int choice = Home.getValidChoice(5);

        switch (choice) {
            case 1:
                addProduct();
                break;

            case 2:
                viewProduct();
                backToProductMenu();
                break;

            case 3:
                updateProductInfo();
                break;

            case 4:
                deleteProduct();
                break;

            case 5:
                Home.homeMenu();
                break;
        }

    }

// Method to add product
    public static void addProduct() {
        int numProd;
        boolean validNumProd;
        boolean validUnitCost;
        boolean validPrice;
        boolean validQuantity;
        boolean validCategoryId = false;
        int categoryIndex = -1;
        boolean validSuppId = false;
        int supplierIndex = -1;

        Home.clearScreen();

        // Read the text file to get the last product ID, then increment it
        try (BufferedReader readProduct = new BufferedReader(new FileReader("product.txt"))) {
            String prodLastLine = "";
            String line;
            while ((line = readProduct.readLine()) != null) {
                prodLastLine = line;
            }
            if (!prodLastLine.isEmpty()) {
                String lastProductId = prodLastLine.split("\\|")[0];
                if (lastProductId.startsWith("P") && lastProductId.length() > 1) {
                    Product.nextProdCount = Integer.parseInt(lastProductId.substring(1)) + 1;
                } else {
                    System.out.println("\n\n\t\t   ** Invalid Product ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading product IDs! **");
            e.printStackTrace();
        }

        do {
            // Let the user enter the number of products to add
            System.out.print("\n\t\tEnter the number of products to add: ");
            try {
                numProd = scan.nextInt();
                scan.nextLine();
                if (numProd > 0) {
                    validNumProd = true;

                    // Initialize the array with the size entered
                    addProductList = new Product[numProd];

                    System.out.println("\n\n\t\t===========================================================================");
                    System.out.println("\t\t|                             ADD PRODUCT                                 |");
                    System.out.println("\t\t===========================================================================");

                    try (FileWriter writeProduct = new FileWriter("product.txt", true)) {
                        for (int a = 0; a < addProductList.length; a++) {
                            String prodId = "P" + Product.getNextProdCount();
                            System.out.println("\t\t|                                                                         |");
                            System.out.printf("\t\t|  Product %d :                                                            |\n", a + 1);
                            System.out.println("\t\t| ----------------                                                        |");
                            System.out.printf("\t\t|   1. Product ID                                : %s\n", prodId);
                            System.out.println("\t\t                                                                          |");

                            System.out.print("\t\t|   2. Enter Name                                : ");
                            String prodName = scan.nextLine();
                            System.out.println("\t\t                                                                          |");

                            // Validate the unit cost
                            validUnitCost = false;
                            double unitCost = 0;
                            System.out.print("\t\t|   3. Enter Unit Cost                           : RM ");
                            while (!validUnitCost) {
                                try {
                                    unitCost = scan.nextDouble();
                                    scan.nextLine();
                                    if (unitCost > 0) {
                                        validUnitCost = true;
                                    } else {
                                        System.out.println("\t\t|       -- Unit Cost must be greater than 0! --                           |");
                                        System.out.println("\t\t                                                                          |");
                                        System.out.print("\t\t|      Re-enter Unit Cost                        : RM ");
                                        validUnitCost = false;
                                    }
                                } catch (InputMismatchException e) {
                                    scan.next();
                                    System.out.println("\t\t|       -- Invalid Unit Cost! Please enter a valid cost! --               |");
                                    System.out.println("\t\t                                                                          |");
                                    System.out.print("\t\t|      Re-enter Unit Cost                        : RM ");
                                    validUnitCost = false;
                                }
                            }
                            System.out.println("\t\t                                                                          |");

                            // Validate the price
                            validPrice = false;
                            double price = 0;
                            System.out.print("\t\t|   4. Enter Price                               : RM ");
                            while (!validPrice) {
                                try {
                                    price = scan.nextDouble();
                                    scan.nextLine();
                                    if (price >= unitCost) {
                                        validPrice = true;
                                    } else {
                                        System.out.println("\t\t|       -- Price must be greater than or equal to Unit Cost! --           |");
                                        System.out.println("\t\t                                                                          |");
                                        System.out.print("\t\t|      Re-enter Price                            : RM ");
                                        validPrice = false;
                                    }
                                } catch (InputMismatchException e) {
                                    scan.next();
                                    System.out.println("\t\t|       -- Invalid Price! Please enter a valid price! --                  |");
                                    System.out.println("\t\t                                                                          |");
                                    System.out.print("\t\t|      Re-enter Price                            : RM ");
                                    validPrice = false;
                                }
                            }
                            System.out.println("\t\t                                                                          |");

                            // Validate quantity
                            validQuantity = false;
                            int quantityInStock = 0;
                            System.out.print("\t\t|   5. Enter Quantity In Stock                   : ");
                            while (!validQuantity) {
                                try {
                                    quantityInStock = scan.nextInt();
                                    scan.nextLine();
                                    if (quantityInStock >= 0) {
                                        validQuantity = true;
                                    } else {
                                        System.out.println("\t\t|       -- Quantity must be 0 or more! --                                  |");
                                    }
                                } catch (InputMismatchException e) {
                                    scan.next();
                                    System.out.println("\t\t|       -- Invalid Quantity! Please enter a valid quantity! --            |");
                                    System.out.println("\t\t                                                                          |");
                                    System.out.print("\t\t|      Re-enter Quantity                         : ");
                                }
                            }
                            System.out.println("\t\t                                                                          |");

                            // Show category list and validate category ID
                            CategoryDrive.showCategoryList();
                            System.out.println("\t\t                                                                          |");

                            validCategoryId = false;
                            Category selectedCategory = null;
                            System.out.print("\t\t|   6. Enter Category ID                         : ");
                            while (!validCategoryId) {
                                String categoryId = scan.nextLine(); // Read input
                                categoryIndex = CategoryDrive.findCategoryIndexById(categoryId);
                                if (categoryIndex != -1) {
                                    selectedCategory = CategoryDrive.getCategoryByIndex(categoryIndex); // Get the Category object
                                    validCategoryId = true;
                                } else {
                                    System.out.println("\t\t|       -- Category ID not found! Please enter a valid ID! --             |");
                                    System.out.println("\t\t|                                                                         |");
                                    System.out.print("\t\t|      Re-enter Category ID                      : ");
                                }
                            }
                            System.out.println("\t\t                                                                          |");

                            // Show supplier list and validate supplier ID
                            SupplierDrive.showSupplierList();
                            System.out.println("\t\t                                                                          |");

                            validSuppId = false;
                            Supplier selectedSupplier = null;
                            System.out.print("\t\t|   7. Enter Supplier ID                         : ");
                            while (!validSuppId) {
                                String supplierId = scan.nextLine(); // Read input
                                supplierIndex = SupplierDrive.findSupplierIndexById(supplierId);
                                if (supplierIndex != -1) {
                                    selectedSupplier = SupplierDrive.getSupplierByIndex(supplierIndex); // Get the Supplier object
                                    validSuppId = true;
                                } else {
                                    System.out.println("\t\t|       -- Supplier ID not found! Please enter a valid ID! --             |");
                                    System.out.println("\t\t|                                                                         |");
                                    System.out.print("\t\t|      Re-enter Supplier ID                      : ");
                                }
                            }

                            System.out.println("\t\t                                                                          |");
                            System.out.println("\t\t---------------------------------------------------------------------------");

                            // Create a new product object with the selected category and supplier
                            addProductList[a] = new Product(prodName, unitCost, price, quantityInStock, selectedCategory, selectedSupplier);

                            // Write the product details to the file
                            writeProduct.write(addProductList[a].toString() + "\n");
                        }
                        System.out.println("\n\n\t\t   ** Added successfully! **");
                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred while writing the product! **");
                        e.printStackTrace();
                    }

                    viewProduct();
                    continueAddingProd();
                } else {
                    System.out.println("\t\t       ---  Invalid input! Please enter number of products (>0) -- \n");
                    validNumProd = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input! Please enter number of products (>0) -- ");
                validNumProd = false;
            }
        } while (!validNumProd);
    }

    //Method to continue add product
    public static void continueAddingProd() {

        // Let user choose to continue adding product or back to product menu
        System.out.println("\n\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Adding product      |");
        System.out.println("\t\t\t\t| 2. Back to product Menu         |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choice = Home.getValidChoice(2);

        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                productMenu();
                break;
        }
    }

    // Method to view product list
    public static void viewProduct() {

        Home.clearScreen();
        File prodList = new File("product.txt");

        // Initialize the array for the display product list
        viewProductList = new Product[MAX_PRODUCT];

        System.out.println("\n\n\t\t\t\t\t\t\t\t PRODUCT LIST");
        System.out.println("\t\t\t\t\t\t\t\t---------------");
        System.out.println("\t\t  __________________________________________________________________________________________________________________________________");
        System.out.println("\t\t  |     ID     |        NAME         |   Unit Cost(RM)   |   Price(RM)   |  Quantity In Stock  |   Category ID   |   Supplier ID   |");
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------------------------");

        // Read the file again to show all product details
        try (Scanner fileScanner = new Scanner(prodList)) {

            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String productData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (productData.isEmpty()) {
                    continue;
                }

                // Get the detail by separate using "|"
                String[] productDetails = productData.split("\\|");

                // Ensure the line has exactly 7 elements
                if (productDetails.length == 7) {
                    String productId = productDetails[0];
                    String productName = productDetails[1];
                    double unitCost = Double.parseDouble(productDetails[2].trim());
                    double price = Double.parseDouble(productDetails[3].trim());
                    int quantityInStock = Integer.parseInt(productDetails[4].trim());
                    String categoryId = productDetails[5];
                    String supplierId = productDetails[6];

                    // Create dummy Category and Supplier objects or retrieve from lists
                    Category category = CategoryDrive.getCategoryById(categoryId);
                    Supplier supplier = SupplierDrive.getSupplierById(supplierId);

                    // Create a new Product object and add it to the list
                    viewProductList[g] = new Product(productName, unitCost, price, quantityInStock, category, supplier);
                    viewProductList[g].setProdId(productId);

                    // Print the supplier information
                    System.out.printf("\t\t  |   %-9s|     %-16s|       %-12.2f|     %-10.2f|         %-12d|      %-11s|      %-11s|\n", productId, productName, unitCost, price, quantityInStock, category.getCatId(), supplier.getSupplierId());
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Product data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------------------------");

    }

    // Method to let user choose to back product menu when view product list
    public static void backToProductMenu() {

        System.out.println("\n\n\t\t  Back to Product Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceY = scan.next().toUpperCase().charAt(0);

                if (choiceY == 'Y') {
                    validChoiceY = true;
                    scan.nextLine();
                    Home.clearScreen();
                    productMenu();
                } else {
                    validChoiceY = false;
                    System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoiceY = false;

                System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)...\n\n");

            }
        } while (!validChoiceY);

    }

    // Method to update product details
    public static void updateProductInfo() {

        boolean continueModify;
        boolean validProdId = false;
        int productIndex = -1;

        Home.clearScreen();

        // Read the detail from the text file and populate modifyProductList
        loadProductData();

        // Display the product list and let the user select the product to modify by entering the product ID
        viewProduct();

        System.out.print("\n\t\tEnter the ID of the product you want to modify (PXXXX) : ");
        // DO-WHILE loop to make sure the ID matches
        do {
            String modifyPId = scan.nextLine();

            // Check for empty input
            if (modifyPId.isEmpty()) {
                System.out.println("\t\t\t     -- Product ID cannot be empty! --");
                System.out.print("\n\t\tRe-enter a valid ID (PXXXX) : ");
                continue; // Prompt for ID again
            }

            // Find product index with the match id
            productIndex = findProductIndexById(modifyPId);

            if (productIndex != -1) {
                validProdId = true;

                Home.clearScreen();

                // Display the specified product's detail with match id
                displayProductDetails(modifyPId, productIndex);
                modifyProductDetails(productIndex);
            } else {
                System.out.println("\t\t\t     -- Product ID not found! --");
                System.out.print("\n\t\tRe-enter Valid ID (PXXXX)  : ");
            }
        } while (!validProdId);
    }

// Method to handle product modification choices
    private static void modifyProductDetails(int productIndex) {
        boolean continueModify = true;

        System.out.println("\n\n\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t\t|  Modify                                             |");
        System.out.println("\t\t\t- ................................................... -");
        System.out.println("\t\t\t|          1. Product Name                            |");
        System.out.println("\t\t\t!          2. Product Unit Cost                       |");
        System.out.println("\t\t\t|          3. Product Price                           |");
        System.out.println("\t\t\t|          4. Product Quantity In Stock               |");
        System.out.println("\t\t\t|          5. Category ID                             |");
        System.out.println("\t\t\t|          6. Supplier ID                             |");
        System.out.println("\t\t\t|          7. Return Product Menu                     |");
        System.out.println("\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        int choice = Home.getValidChoice(7);

        switch (choice) {
            case 1:

                Home.clearScreen();

                System.out.println("\n\n\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Name : %-21s'\n", modifyProductList[productIndex].getProdName());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                System.out.println("\n\n\t\t\t Modify Product Name:");
                System.out.println("\t\t\t-------------------------------");
                System.out.print("\t\t\t Enter new name : ");
                String modifyPName = scan.nextLine();
                // Confirm modification

                if (Home.confirmModification(modifyProductList[productIndex].getProdName(), modifyPName, "name")) {
                    modifyProductList[productIndex].setProdName(modifyPName);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }

                break;

            case 2:
                Home.clearScreen();

                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Unit Cost : %-16.2f'\n", modifyProductList[productIndex].getPrice());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                double modifyPUnitCost = getValidDouble(
                        "\n\n\t\t\t Modify Product Unit Cost:\n \t\t\t--------------------------------------\n \t\t\t Enter new unit cost : RM ");

                if (Home.confirmModification(String.format("%.2f", modifyProductList[productIndex].getUnitCost()),
                        String.format("%.2f", modifyPUnitCost), "unit cost")) {
                    modifyProductList[productIndex].setUnitCost(modifyPUnitCost);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }
                break;

            case 3:
                Home.clearScreen();

                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Price : %-20.2f'\n", modifyProductList[productIndex].getPrice());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                double modifyPrice = getValidDouble("\n\n\t\t\t Modify Product Price: \n\t\t\t-------------------------------------- \n\t\t\t Enter new price     : RM ");

                if (Home.confirmModification(String.format("%.2f", modifyProductList[productIndex].getPrice()),
                        String.format("%.2f", modifyPrice), "price")) {
                    modifyProductList[productIndex].setPrice(modifyPrice);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }
                break;

            case 4:
                Home.clearScreen();

                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Quantity    : %-14s'\n", modifyProductList[productIndex].getQuantityInStock());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                int modifyQuantity = getValidInt("\n\n\t\t\t Modify Quantity In Stock: \n\t\t\t-------------------------------------- \n\t\t\t Enter quantity      : ");

                if (Home.confirmModification(String.valueOf(modifyProductList[productIndex].getQuantityInStock()),
                        String.valueOf(modifyQuantity), "quantity")) {
                    modifyProductList[productIndex].setQuantityInStock(modifyQuantity);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }
                break;

            case 5:

                Home.clearScreen();

                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Category ID : %-14s'\n", modifyProductList[productIndex].getCategory().getCatId());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                //Show simple category table
                CategoryDrive.showCatTable();

                System.out.println("\n\n\t\t\t Modify Category ID :");
                System.out.println("\t\t\t--------------------------------------");
                System.out.print("\t\t\t Enter Category ID      : ");

                // Validate Category ID
                boolean validCategory = false;
                Category newCategory = null;

                while (!validCategory) {
                    String categoryId = scan.nextLine(); // Read input

                    newCategory = CategoryDrive.getCategoryById(categoryId);

                    if (newCategory != null) {
                        validCategory = true; // Valid ID found, exit loop
                    } else {
                        System.out.println("\t\t\t    -- Invalid Category ID! Please enter valid ID! --\n");
                        System.out.print("\t\t\t Re-enter category ID   : ");
                    }
                }

                if (Home.confirmModification(modifyProductList[productIndex].getCategory().getCatId(), newCategory.getCatId(), "category")) {
                    modifyProductList[productIndex].setCategory(newCategory);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }
                break;

            case 6:
                Home.clearScreen();

                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                System.out.printf("\t\t\t ' Current Supplier ID : %-15s'\n", modifyProductList[productIndex].getSupplier().getSupplierId());
                System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                //Show simple supplier table
                SupplierDrive.showSuppTable();

                System.out.println("\n\n\t\t\t Modify Supplier ID :");
                System.out.println("\t\t\t--------------------------------------");
                System.out.print("\t\t\t Enter Supplier ID      : ");

                // Validate Supplier ID
                boolean validSupplier = false;
                Supplier newSupplier = null;

                while (!validSupplier) {
                    String supplierId = scan.nextLine(); // Read input

                    newSupplier = SupplierDrive.getSupplierById(supplierId); // Assuming you have a method to find a supplier by ID

                    if (newSupplier != null) {
                        validSupplier = true; // Valid ID found, exit loop
                    } else {
                        System.out.println("\t\t\t    -- Invalid Supplier ID! Please enter valid ID! --\n");
                        System.out.print("\t\t\t Re-enter supplier ID   : ");
                    }
                }

                if (Home.confirmModification(modifyProductList[productIndex].getSupplier().getSupplierId(), newSupplier.getSupplierId(), "supplier")) {
                    modifyProductList[productIndex].setSupplier(newSupplier);
                    System.out.println("\t\t\t\t\t -- Modification successful! --");
                }
                break;

            case 7:
                productMenu();
                break;
        }

        // Save the modified info to the text file
        try (FileWriter writeModifyProd = new FileWriter("product.txt")) {
            for (Product product : modifyProductList) {
                if (product != null) {
                    writeModifyProd.write(product.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\t\t-- An error occurred while saving! --");
            e.printStackTrace();
        }

        Home.clearScreen();

        // Show the updated product list
        viewProduct();

        continueModifyProd();

    }
    
    
   
    // Helper method to get a valid double input
    public static double getValidDouble(String prompt) {
        double value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scan.nextDouble();
                scan.nextLine();  // Consume newline
                if (value > 0) {
                    valid = true;
                } else {
                    System.out.println("\t\t\t\t\t -- Invalid input! Value must be greater than 0 --");
                }
            } catch (InputMismatchException e) {
                scan.next();  // Consume invalid input
                System.out.println("\t\t\t\t\t -- Invalid input! Please enter a valid number --");
            }
        }
        return value;
    }

// Helper method to get a valid integer input
    public static int getValidInt(String prompt) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scan.nextInt();
                scan.nextLine();  // Consume newline
                if (value > 0) {
                    valid = true;
                } else {
                    System.out.println("\t\t\t\t\t -- Invalid input! Value must be greater than 0 --");
                }
            } catch (InputMismatchException e) {
                scan.next();  // Consume invalid input
                System.out.println("\t\t\t\t\t -- Invalid input! Please enter a valid number --");
            }
        }
        return value;
    }

    // Method to read the detail from the text file and populate modifyProductList
    public static void loadProductData() {
        if (modifyProductList == null) {
            modifyProductList = new Product[MAX_PRODUCT]; // Initialize if null
        }

        try (Scanner fileScanner = new Scanner(new File("product.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String productData = fileScanner.nextLine().trim();
                if (!productData.isEmpty()) {
                    String[] productDetails = productData.split("\\|");
                    if (productDetails.length == 7) {
                        if (index >= modifyProductList.length) {
                            System.out.println("\n\t\t  -- Error!!! Product list is full! --");
                            break;
                        }

                        String productId = productDetails[0];
                        String productName = productDetails[1];
                        double unitCost = Double.parseDouble(productDetails[2].trim());
                        double price = Double.parseDouble(productDetails[3].trim());
                        int quantityInStock = Integer.parseInt(productDetails[4].trim());
                        String categoryId = productDetails[5];
                        String supplierId = productDetails[6];

                        // Create Category and Supplier objects using their IDs
                        Category category = CategoryDrive.getCategoryById(categoryId);
                        Supplier supplier = SupplierDrive.getSupplierById(supplierId);

                        // Create a new Product object and add it to the list for modification
                        modifyProductList[index] = new Product(productName, unitCost, price, quantityInStock, category, supplier);
                        modifyProductList[index].setProdId(productId);

                        index++;
                    } else {
                        System.out.println("\n\t\t  -- Error!!! Product data is missing or corrupted in the file! --");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t ** Can't find the file!!! **");
            e.printStackTrace();
        }
    }

    // Method to find the index of a product by its ID
    public static int findProductIndexById(String id) {
        // Ensure product data is loaded
        if (modifyProductList == null) {
            loadProductData(); // Load supplier data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            return -1; // Invalid input
        }

        // Loop through the list to find the product
        for (int i = 0; i < modifyProductList.length; i++) {
            // Check for null elements in the list and Product id found
            if (modifyProductList[i] != null && id.equals(modifyProductList[i].getProdId())) {
                return i;
            }
        }

        // Category not found
        return -1;
    }
    
    
    // Method to get a Product by its ID
    public static Product getProductById(String id) {
        // Ensure product data is loaded
        if (modifyProductList == null) {
            loadProductData(); // Load product data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return null; // Invalid input
        }

        // Loop through the list to find the product
        for (int i = 0; i < modifyProductList.length; i++) {
            // Check for null elements in the list and Product id found
            if (modifyProductList[i] != null && id.equals(modifyProductList[i].getProdId())) {
                return modifyProductList[i]; // Return the found Category
            }
        }

        // Category not found
        System.out.println("\t\t\t    -- Product with ID " + id + " not found.");
        return null;
    }
    
    
    
    // Method to get a Product by its index
    public static Product getProductByIndex(int index) {
        // Ensure product data is loaded
        if (modifyProductList == null) {
            loadCategoryData(); // Load product data if not already loaded
        }

        // Check if the index is valid
        if (index < 0 || index >= modifyProductList.length || modifyProductList[index] == null) {
            System.out.println("Invalid index provided.");
            return null; // Invalid input
        }

        // Return the Category at the specified index
        return modifyProductList[index];
    }
    
    

    // Method to get the unit cost of a product by its ID
    public static double getUnitCostById(String id) {
        // Ensure product data is loaded
        if (modifyProductList == null) {
            loadProductData(); // Load product data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return -1; // Invalid input
        }

        // Loop through the list to find the product
        for (int i = 0; i < modifyProductList.length; i++) {
            // Check for null elements in the list and Product id found
            if (modifyProductList[i] != null && id.equals(modifyProductList[i].getProdId())) {
                return modifyProductList[i].getUnitCost(); // Return the unit cost
            }
        }

        // Product not found
        System.out.println("\t\t\t    -- Product with ID " + id + " not found.");
        return -1; // Indicating that the product was not found
    }


    

    // Method to display product details with ID
    private static void displayProductDetails(String productId, int productIndex) {
        System.out.printf("\n\n\n\t\t\t\t\t\t\tDetails of Product with ID(%s)\n", productId);
        System.out.println("\t\t  __________________________________________________________________________________________________________________________________");
        System.out.println("\t\t  |     ID     |        NAME         |   Unit Cost(RM)   |   Price(RM)   |  Quantity In Stock  |   Category ID   |   Supplier ID   |");
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\t\t  |   %-9s|     %-16s|       %-12.2f|     %-10.2f|         %-12d|      %-11s|      %-11s|\n", modifyProductList[productIndex].getProdId(),
                modifyProductList[productIndex].getProdName(), modifyProductList[productIndex].getUnitCost(), modifyProductList[productIndex].getPrice(), modifyProductList[productIndex].getQuantityInStock(), modifyProductList[productIndex].getCategory().getCatId(), modifyProductList[productIndex].getSupplier().getSupplierId());
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------------------------");
    }

    // Method for continue modify the detail of product in modifyProductInfo function
    private static void continueModifyProd() {

        System.out.println("\n\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| Choice                              |");
        System.out.println("\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Modify                  |");
        System.out.println("\t\t\t\t| 2. Back to Product Menu             |");
        System.out.println("\t\t\t\t---------------------------------------");

        int choice = Home.getValidChoice(4);

        switch (choice) {
            case 1:
                updateProductInfo();
                break;
            case 2:
                productMenu();
                break;
        }
    }

    public static void deleteProduct() {

        boolean validProdId = false;
        int n = 0; // Tracks the number of products loaded from the file

        deleteProductList = new Product[MAX_PRODUCT];

        File delProdList = new File("product.txt");

        // Read the file again to show all product details
        try (Scanner fileScanner = new Scanner(delProdList)) {

            while (fileScanner.hasNextLine()) {
                // Read the next line (remove any leading and trailing whitespace characters)
                String productData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (productData.isEmpty()) {
                    continue;
                }

                // Get the details by splitting using "|"
                String[] productDetails = productData.split("\\|");

                // Ensure the line has exactly 7 elements
                if (productDetails.length == 7) {
                    String productId = productDetails[0];
                    String productName = productDetails[1];
                    double unitCost = Double.parseDouble(productDetails[2].trim());
                    double price = Double.parseDouble(productDetails[3].trim());
                    int quantityInStock = Integer.parseInt(productDetails[4].trim());
                    String categoryId = productDetails[5];
                    String supplierId = productDetails[6];

                    int categoryIndex = CategoryDrive.findCategoryIndexById(categoryId);

                    // Create Category and Supplier objects using their IDs
                    Category category = CategoryDrive.getCategoryByIndex(categoryIndex);
                    Supplier supplier = SupplierDrive.getSupplierById(supplierId);

                    // Create a new Product object and add it to the list
                    deleteProductList[n] = new Product(productName, unitCost, price, quantityInStock, category, supplier);
                    deleteProductList[n].setProdId(productId);
                    n++; // Increment the product count
                } else {
                    System.out.println("\n\t\t  -- Error!!! Product data is missing or corrupted in the file! --");
                }
            }

            // View all products after loading from the file
            viewProduct();

            System.out.print("\t\t  Enter the ID of the product you want to remove (PXXXX) : ");

            do {
                String delPId = scan.nextLine();

                for (int o = 0; o < n; o++) {
                    if (delPId.equals(deleteProductList[o].getProdId())) {
                        validProdId = true;

                        Home.clearScreen();
                        System.out.printf("\n\n\n\t\t\t\t\t\t   Details of Product with ID(%s)\n", delPId);

                        System.out.println("\t\t  ____________________________________________________________________________________________________________");
                        System.out.println("\t\t  |     ID     |        NAME         |   Unit Cost(RM)   |   Price(RM)   |   Category ID   |   Supplier ID   |");
                        System.out.println("\t\t  ------------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\t  |   %-9s|     %-16s|       %-12.2f|     %-10.2f|      %-11s|      %-11s|\n",
                                deleteProductList[o].getProdId(), deleteProductList[o].getProdName(),
                                deleteProductList[o].getUnitCost(), deleteProductList[o].getPrice(),
                                deleteProductList[o].getCategory().getCatId(), deleteProductList[o].getSupplier().getSupplierId());
                        System.out.println("\t\t  ------------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\tAre you sure you want to remove the product with ID(%s)? \n", delPId);
                        boolean validChoiceY = false;
                        char choiceY;

                        do {
                            System.out.print("\t\t(Y/N) : ");
                            choiceY = scan.next().toUpperCase().charAt(0);

                            if (choiceY == 'Y' || choiceY == 'N') {
                                validChoiceY = true;
                                scan.nextLine();

                                if (choiceY == 'Y') {
                                    // Shift the product in the array to remove the chosen product
                                    for (int j = o; j < n - 1; j++) {
                                        deleteProductList[j] = deleteProductList[j + 1];
                                    }
                                    deleteProductList[n - 1] = null; // Clear the last entry
                                    n--; // Reduce the count of products

                                    // Update the file after deletion
                                    try (FileWriter writeProduct = new FileWriter("product.txt")) {
                                        for (int i = 0; i < n; i++) {
                                            Product product = deleteProductList[i];
                                            if (product != null) {
                                                writeProduct.write(product.toString() + "\n");
                                            }
                                        }
                                        System.out.println("\t\t\t\t\t -- Product file updated successfully! --");
                                    } catch (IOException e) {
                                        System.out.println("\t\t\t\t\t -- An error occurred while updating the product file. --");
                                        e.printStackTrace();
                                    }

                                    System.out.println("\t\t\t\t\t -- Product deleted successfully! --");

                                } else {
                                    System.out.println("\t\t\t\t\t -- Product deletion canceled! --");
                                }

                                // Show updated product list
                                viewProduct();

                                // Let user make selection to return Product Menu
                                backToProductMenu();
                            } else {
                                System.out.println("\t\t\t        --- Invalid choice! Please enter Y or N...");
                            }
                        } while (!validChoiceY);

                        break; // Exit loop after handling the ID
                    }
                }

                if (!validProdId) {
                    System.out.println("\t\t\t     -- Product ID not found! --");
                    System.out.print("\n\t\t  Re-enter Valid ID (PXXXX) : ");
                }
            } while (!validProdId);

        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
    }
}
