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
import java.time.LocalDate; //to auto-generate date
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDrive {

    private static Order[] addOrderList;
    private static Order[] viewOrderList;
    private static Order[] deleteOrderList;

    private static final int MAX_ORDER = 100;
    private static int choiceO;
    private static boolean validChoiceO;
    private static final Scanner scan = new Scanner(System.in);//Scannercan
    private static final LocalDate orderDate = LocalDate.now();//Date

    //order menu
    public static void orderMenu() throws IOException {
        Home.clearScreen();

        try {
            File myOrder = new File("order.txt");
            if (myOrder.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + myOrder.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
        }

        System.out.println("\n\n");
        System.out.println("\t\t------------------------------------------------\t\t");
        System.out.println("\t\t|                  ORDER MENU                  |\t\t");
        System.out.println("\t\t------------------------------------------------\t\t");
        System.out.println("\t\t|       1. ADD Order                           |\t\t");
        System.out.println("\t\t|       2. DELETE Order                        |\t\t");
        System.out.println("\t\t|       3. VIEW Order                          |\t\t");
        System.out.println("\t\t|       4. Back                                |\t\t");
        System.out.println("\t\t------------------------------------------------\t\t");

        //allow the user to re-enter the input selections for the order function
        do {
            System.out.print("\t\t  Enter Choice (1-4) : ");

            try {
                choiceO = scan.nextInt();
                scan.nextLine();

                if (choiceO >= 1 && choiceO <= 4) {
                    validChoiceO = true;
                } else {
                    validChoiceO = false;
                    System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (1-4)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoiceO = false;
                System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (1-4)...\n\n");
            }
        } while (!validChoiceO);

        switch (choiceO) {
            case 1:
                addOrder();
                break;
            case 2:
                deleteOrder();
                break;
            case 3:
                viewOrder();
                backToOrderMenu();
                break;
            case 4:
                Home.homeMenu();
                break;
        }
    }

    //add order function
    public static void addOrder() throws FileNotFoundException, IOException {

        int numOrder;
        boolean validNumOrder;
        boolean validQuantity;
        boolean validProductId = false;
        int productIndex = -1;

        String productId = ""; // Ensure productId is declared and initialized

        Home.clearScreen();

        try (BufferedReader readOrder = new BufferedReader(new FileReader("order.txt"))) {
            String OrderLastLine = "", line;
            while ((line = readOrder.readLine()) != null) {
                OrderLastLine = line;
            }
            if (!OrderLastLine.isEmpty()) {
                // Extract the numeric part of the last order ID and increment it
                String lastOrderId = OrderLastLine.split("\\|")[0];
                if (lastOrderId.startsWith("OD") && lastOrderId.length() > 2) {
                    Order.nextOrderCount = Integer.parseInt(lastOrderId.substring(2)) + 1;
                } else {
                    System.out.println("\n\n\t\t   ** Invalid order ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading order IDs! **");
        }

        do {
            // Let the user enter the number of orders to add
            System.out.print("\n\t\tEnter the number of order(s) to add : ");

            try {
                numOrder = scan.nextInt();
                scan.nextLine();

                if (numOrder > 0) {
                    validNumOrder = true;

                    // Initialize the array with the size entered
                    addOrderList = new Order[numOrder];

                    System.out.println("\n\n");
                    System.out.println("\t\t=====================================================================================================================================");
                    System.out.println("\t\t|                                                          ADD ORDER                                                                |");
                    System.out.println("\t\t=====================================================================================================================================");

                    try (FileWriter writeOrder = new FileWriter("order.txt", true)) {
                        for (int a = 0; a < addOrderList.length; a++) {
                            //auto-generated order ID
                            String orderId = "OD" + Order.getNextOrderCount();
                            System.out.print("\t\t|                                                                                                                                   |\n");
                            System.out.printf("\t\t|  Order %d :                                                                                                                        |\n", a + 1);
                            System.out.print("\t\t| ----------------                                                                                                                  |\n");
                            System.out.printf("\t\t|   1. Order ID                               : %s\n", orderId);
                            System.out.print("\t\t|                                                                                                                                   |\n");

                            //auto-generated date
                            System.out.print("\t\t|   2. Date                                   : " + orderDate.toString());
                            System.out.print("\n\t\t|                                                                                                                                   |\n");

                            //Show product list
                            ProductDrive.viewProduct();
                            System.out.print("\t\t|                                                                                                                                   |\n");
                            // Consume the leftover newline
                            scan.nextLine(); 

                           // Validate Product ID
      
                            validProductId = false;
                            Product selectedProduct = null;
                            
                            
    
                            System.out.print("\t\t|   3. Enter Product ID(PXXXX)                : ");
                            while (!validProductId) {
                                productId = scan.nextLine(); // Read input
                                productIndex = ProductDrive.findProductIndexById(productId);

                                if (productIndex != -1) {
                                    selectedProduct = ProductDrive.getProductByIndex(productIndex);
                                    double price = ProductDrive.getUnitCostById(productId);
                                    System.out.print("\t\t|                                                                                                                                   |\n");
                                    
                                    System.out.println("\t\t|   4. Product Price                          : RM" + price);
                                    validProductId = true; // Valid ID found, exit loop
                                } else {
                                    // If ID is invalid or not found, prompt user to re-enter
                        
                                    System.out.println("\t\t|       -- Product ID not found! Please enter a valid ID! --                                                                        |");
                                    System.out.print("\t\t|                                                                                                                                   |\n");
                                    System.out.print("\t\t|      Re-enter Product ID                    : ");
                                }
                            }
                            System.out.print("\t\t|                                                                                                                                   |\n");
                            

                            // Validate quantity
                            validQuantity = false;
                            int quantity = 0;
                            System.out.print("\t\t|   5. Enter Quantity                         : ");
                            while (!validQuantity) {
                                try {
                                    quantity = scan.nextInt();
                                    scan.nextLine();

                                    // Validate to make sure quantity >0
                                    if (quantity > 0) {
                                        validQuantity = true;
                                    } else {
                                        validQuantity = false;
                                    }

                                } catch (InputMismatchException e) {
                                    scan.next();
                                    System.out.println("\t\t|       -- Invalid Quantity! Please enter valid quantity! --                                                                        |");
                                    System.out.print("\t\t|                                                                                                                                   |\n");
                                    System.out.print("\t\t|      Re-enter Quantity                         :  ");
                                }
                            }
                            System.out.print("\t\t                                                                                                                                    |\n");
                            System.out.print("\t\t|\n");
                            System.out.print("\t\t                                                                                                                                    |\n");

                            double productPrice = ProductDrive.getUnitCostById(productId);
                            double orderAmount = quantity * productPrice;
                            System.out.printf("\t\t|   6. Order Amount                           : RM%.2f" , orderAmount);
                            System.out.print("\t\t|                                                                                                                                   |\n");

                            System.out.print("\n\t\t|                                                                                                                                   |\n");
                            System.out.println("\t\t-----------------------------------------------------------------------------------------------------------------------------------");

                            // Create a new Order object and add it to array
                            addOrderList[a] = new Order(orderDate, selectedProduct,  quantity, orderAmount);
                            

                            // Write to the file
                            writeOrder.write(addOrderList[a].toString() + "\n");
                        }
                        System.out.println("\n\n\t\t   ** Added successfully! **");
                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred!!! **");
                        e.printStackTrace();
                    }
                    viewOrder();
                    continueAddingOrder();
                } else {
                    System.out.println("\t\t       ---  Invalid input!!! Please enter number of order (>0)-- \n");
                    validNumOrder = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input!!! Please enter number of order (>0)-- ");
                validNumOrder = false;
            }
        } while (!validNumOrder);
    }

    //Method to continue add order
    public static void continueAddingOrder() throws IOException {

        // Let user choose to continue adding order or back to order menu
        System.out.println("");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Adding ORDER        |");
        System.out.println("\t\t\t\t| 2. Back to Order Menu           |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choiceR = Home.getValidChoice(2);

        switch (choiceR) {
            case 1: {
                try {
                    addOrder();
                } catch (IOException ex) {
                    Logger.getLogger(OrderDrive.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 2:
                orderMenu();
                break;
        }
    }
    
    
    
    // Method to let user choose to back order menu when view order list
    public static void backToOrderMenu() {

        System.out.println("\n\n\t\t  Back to Order Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceY = scan.next().toUpperCase().charAt(0);

                if (choiceY == 'Y') {
                    validChoiceY = true;
                    scan.nextLine();
                    Home.clearScreen();
                    try {
                        orderMenu();
                    } catch (IOException e) {
                        System.out.println("\n\t\t  -- Error while opening Order Menu! --");
                        e.printStackTrace();
                    }
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

    // Display Order List
    public static void viewOrder() {

        //Home.clearScreen();
        File orderList = new File("order.txt");

        // Initialize the array for the display order list
        viewOrderList = new Order[MAX_ORDER];

        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\t\t   ORDER LIST  ");
        System.out.println("\t\t\t\t\t\t\t\t---------------");
        System.out.println("\t\t  ________________________________________________________________________________________________________________");
        System.out.println("\t\t  |       ID       |         DATE         |     Product ID     | Product Price(RM) | Quantity | Order Amount(RM) |");
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------");

        // Read the file again to show all order details
        try (Scanner filescan = new Scanner(orderList)) {

            int o = 0;

            while (filescan.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String orderData = filescan.nextLine().trim();

                // Skip empty lines
                if (orderData.isEmpty()) {
                    continue;
                }

                // Get the detail by separate using "|"
                String[] orderDetails = orderData.split("\\|");
                
   
                

                // Ensure the line has exactly 6 elements
                if (orderDetails.length == 6) {
                    String orderId = orderDetails[0].trim();
                    LocalDate orderDate = LocalDate.parse(orderDetails[1].trim());
                    String productId = orderDetails[2].trim();
                    double productPrice = Double.parseDouble(orderDetails[3].trim());
                    int quantity = Integer.parseInt(orderDetails[4].trim());
                    double orderAmount = Double.parseDouble(orderDetails[5].trim());

                    // Create dummy product objects or retrieve from lists
                    Product product = ProductDrive.getProductById(productId);

                    // Create a new Order object and add it to the list
                    viewOrderList[o] = new Order(orderDate, product,  quantity, orderAmount);
                    viewOrderList[o].setOrderId(orderId);
   
                  
                    // Print the order information
                    System.out.printf("\t\t  |     %-11s|       %-15s|       %-13s|      %-11.2f  |    %-5d |     %-9.2f    |\n", orderId, orderDate, productId, productPrice, quantity, orderAmount);
                    o++;
                    
                 
                } else {
                    System.out.println("\n\t\t  -- Error!!! Order data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------");
    }

    // Method to read the detail from the text file and populate addOrderList
    public static void loadOrderData() {
        if (addOrderList == null) {
            addOrderList = new Order[MAX_ORDER]; // Initialize if null
        }

        try (Scanner fileScanner = new Scanner(new File("order.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String orderData = fileScanner.nextLine().trim();
                if (!orderData.isEmpty()) {
                    String[] orderDetails = orderData.split("\\|");
                    if (orderDetails.length == 6) {
                        if (index >= addOrderList.length) {
                            System.out.println("\n\t\t  -- Error!!! Order list is full! --");
                            break;
                        }
                        String orderId = orderDetails[0];
                        LocalDate orderDate = LocalDate.parse(orderDetails[1].trim());
                        String productId = orderDetails[2];
                        double productPrice = Double.parseDouble(orderDetails[3].trim());
                        int quantity = Integer.parseInt(orderDetails[4].trim());
                        double orderAmount = Double.parseDouble(orderDetails[5].trim());
                        
                        // Create Product objects using their ID and unit cost
                        Product product = ProductDrive.getProductById(productId);
                        productPrice = ProductDrive.getUnitCostById(productId);

                        addOrderList[index] = new Order(orderDate, product, quantity, orderAmount);
                        addOrderList[index].setOrderId(orderId);
                        
                        index++;
                    } else {
                        System.out.println("\n\t\t  -- Error!!! Order data is missing or corrupted in the file! --");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t ** Can't find the file!!! **");
            e.printStackTrace();
        }
    }
    
    // Method to find the index of a order by its ID
    public static int findOrderIndexById(String id) {
        // Ensure order data is loaded
        if (addOrderList == null) {
            loadOrderData(); // Load order data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            return -1; // Invalid input
        }

        // Loop through the list to find the order
        for (int i = 0; i < addOrderList.length; i++) {
            // Check for null elements in the list and Order id found
            if (addOrderList[i] != null && id.equals(addOrderList[i].getOrderId())) {
                return i;
            }
        }

        // Order not found
        return -1;
    }
    
    // Method to get a Order by its ID
    public static Order getOrderById(String id) {
        // Ensure category data is loaded
        if (addOrderList == null) {
            loadOrderData(); // Load category data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return null; // Invalid input
        }

        // Loop through the list to find the order
        for (int i = 0; i < addOrderList.length; i++) {
            // Check for null elements in the list and Order id found
            if (addOrderList[i] != null && id.equals(addOrderList[i].getOrderId())) {
                return addOrderList[i]; // Return the found Order
            }
        }

        // Order not found
        System.out.println("\t\t\t    -- Order with ID " + id + " not found.");
        return null;
    }
    
    // Method to get the unit cost of a order by its ID
    public static double getOrderAmountById(String id) {
        // Ensure order data is loaded
        if (addOrderList == null) {
            loadOrderData(); // Load order data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return -1; // Invalid input
        }

        // Loop through the list to find the order
        for (int i = 0; i < addOrderList.length; i++) {
            // Check for null elements in the list and order id found
            if (addOrderList[i] != null && id.equals(addOrderList[i].getOrderId())) {
                return addOrderList[i].getOrderAmount(); // Return the order amount
            }
        }

        // Order not found
        System.out.println("\t\t\t    -- Order with ID " + id + " not found.");
        return -1; // Indicating that the Order was not found
    }


    // Method to get a Order by its index
    public static Order getOrderByIndex(int index) {
        // Ensure Order data is loaded
        if (addOrderList == null) {
            loadOrderData(); // Load Order data if not already loaded
        }

        // Check if the index is valid
        if (index < 0 || index >= addOrderList.length || addOrderList[index] == null) {
            System.out.println("Invalid index provided.");
            return null; // Invalid input
        }

        // Return the Order at the specified index
        return addOrderList[index];
    }
    
    
    public static void deleteOrder() throws IOException {

        boolean validOrderId = false;
        int n = 0; // Tracks the number of orders loaded from the file

        deleteOrderList = new Order[MAX_ORDER];

        File delOrderList = new File("order.txt");

        // Read the file again to show all order details
        try (Scanner fileScanner = new Scanner(delOrderList)) {

            while (fileScanner.hasNextLine()) {
                // Read the next line (remove any leading and trailing whitespace characters)
                String orderData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (orderData.isEmpty()) {
                    continue;
                }

                // Get the details by splitting using "|"
                String[] orderDetails = orderData.split("\\|");

                // Ensure the line has exactly 6 elements
                if (orderDetails.length == 6) {
                    String orderId = orderDetails[0].trim();
                    LocalDate orderDate = LocalDate.parse(orderDetails[1].trim());
                    String productId = orderDetails[2].trim();
                    double productPrice = Double.parseDouble(orderDetails[3].trim());
                    int quantity = Integer.parseInt(orderDetails[4].trim());
                    double orderAmount = Double.parseDouble(orderDetails[5].trim());
                    
                    // Create Product objects using their ID and unit cost
                    Product product = ProductDrive.getProductById(productId);
                    productPrice = ProductDrive.getUnitCostById(productId);

                    // Create a new Order object and add it to the list
                    deleteOrderList[n] = new Order(orderDate, product, quantity, orderAmount);
                    deleteOrderList[n].setOrderId(orderId);
                    n++; // Increment the product count
                } else {
                    System.out.println("\n\t\t  -- Error!!! Product data is missing or corrupted in the file! --");
                }
            }

            // View all products after loading from the file
            viewOrder();

            System.out.print("\t\t  Enter the ID of the order you want to remove (ODXXXX) : ");

            do {
                String delODId = scan.nextLine();

                for (int o = 0; o < n; o++) {
                    if (delODId.equals(deleteOrderList[o].getOrderId())) {
                        validOrderId = true;

                        Home.clearScreen();
                        System.out.printf("\n\n\n\t\t\t\t\t\t   Details of Order with ID(%s)\n", delODId);

                        System.out.println("\t\t  ________________________________________________________________________________________________________________");
                        System.out.println("\t\t  |       ID       |         DATE         |     Product ID     | Product Price(RM) | Quantity | Order Amount(RM) |");
                        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\t  |     %-11s|       %-15s|       %-13s|      %-11.2f  |    %-5d |     %-9.2f    |\n",
                                deleteOrderList[o].getOrderId(), deleteOrderList[o].getOrderDate(),
                                deleteOrderList[o].getProduct().getProdId(), deleteOrderList[o].getProduct().getUnitCost(),
                                deleteOrderList[o].getQuantity(), deleteOrderList[o].getOrderAmount());
                        System.out.println("\t\t  ----------------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\t  Are you sure you want to remove the order with ID(%s)? ", delODId);
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
                                        deleteOrderList[j] = deleteOrderList[j + 1];
                                    }
                                    deleteOrderList[n - 1] = null; // Clear the last entry
                                    n--; // Reduce the count of products

                                    // Update the file after deletion
                                    try (FileWriter writeProduct = new FileWriter("order.txt")) {
                                        for (int i = 0; i < n; i++) {
                                            Order order = deleteOrderList[i];
                                            if (order != null) {
                                                writeProduct.write(order.toString() + "\n");
                                            }
                                        }
                                        System.out.println("\t\t\t\t\t -- Order file updated successfully! --");
                                    } catch (IOException e) {
                                        System.out.println("\t\t\t\t\t -- An error occurred while updating the order file. --");
                                        e.printStackTrace();
                                    }

                                    System.out.println("\t\t\t\t\t -- Order deleted successfully! --");

                                    
                                } else {
                                    System.out.println("\t\t\t\t\t -- Order deletion canceled! --");
                                }
                                
                                // Show updated product list
                                viewOrder();

                                backToOrderMenu();
                            } else {
                                System.out.println("\t\t\t        --- Invalid choice! Please enter Y or N...");
                            }
                        } while (!validChoiceY);

                        break; // Exit loop after handling the ID
                    }
                }

                if (!validOrderId) {
                    System.out.println("\t\t\t     -- Order ID not found! --");
                    System.out.print("\n\t\t  Re-enter Valid ID (ODXXXX) : ");
                }
            } while (!validOrderId);

            
                                    
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
    }
}