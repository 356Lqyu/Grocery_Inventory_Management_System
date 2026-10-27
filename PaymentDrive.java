/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.time.LocalDate;

public class PaymentDrive {
    private static Payment[] processPaymentList;
    private static Payment[] viewPaymentHistoryList;

    private static final int MAX_PAYMENT = 100;

    public static char choiceY;
    static boolean validChoiceY = false;

    private static final Scanner scan = new Scanner(System.in);//Scanner
    private static final LocalDate paymentDate = LocalDate.now();//Date
    
    // payment Menu
    public static void paymentMenu() throws IOException {

        Home.clearScreen();

        try {
            File myPaym = new File("payment.txt");
            if (myPaym.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + myPaym.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
            e.printStackTrace();
        }

        System.out.println("\n\n");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                          PAYMENT MENU                         |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. PROCESS payment                                      |");
        System.out.println("\t\t|       2. VIEW Payment History                                 |");
        System.out.println("\t\t|       3. Back                                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        int choice = Home.getValidChoice(3);

        switch (choice) {
            case 1:
                processPayment();
                break;

            case 2:
                viewPaymentHistory();
                backToPaymentMenu();
                break;
                
            case 3:
                Home.homeMenu();
                break;
        }
    }
    
    // Method to add payment
    public static void processPayment() throws IOException {
        int numPaym;
        boolean validNumPaym;
        boolean validOrderId = false;
        boolean validPaymentMethod = false;
        int orderIndex = -1;

        String orderId = "";

        Home.clearScreen();

        // Read the text file to get the last payment ID, then increment it
        try (BufferedReader readPayment = new BufferedReader(new FileReader("payment.txt"))) {
            String paymLastLine = "";
            String line;
            while ((line = readPayment.readLine()) != null) {
                paymLastLine = line;
            }
            if (!paymLastLine.isEmpty()) {
                String lastPaymentId = paymLastLine.split("\\|")[0];
                if (lastPaymentId.startsWith("PM") && lastPaymentId.length() > 1) {
                    try {
                        Payment.nextPaymentCount = Integer.parseInt(lastPaymentId.substring(2)) + 1;
                    } catch (NumberFormatException e) {
                        System.out.println("\n\n\t\t   ** Invalid Payment ID format in the file! **");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("\n\n\t\t   ** Invalid Payment ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading payment IDs! **");
            e.printStackTrace();
        }

        do {
            // Let the user enter the number of payment to process to
            System.out.print("\n\t\tEnter the number of payment to process: ");
            try {
                numPaym = scan.nextInt();
                scan.nextLine();
                if (numPaym > 0) {
                    validNumPaym = true;

                    // Initialize the array with the size entered
                    processPaymentList = new Payment[numPaym];
                    
                    

                    System.out.println("\n\n");
                    System.out.println("\t\t===================================================================================================================");
                    System.out.println("\t\t|                                              PROCESS PAYMENT                                                    |");
                    System.out.println("\t\t===================================================================================================================");

                    try (FileWriter writePayment = new FileWriter("payment.txt", true)) {
                        for (int a = 0; a < processPaymentList.length; a++) {
                            
                            String paymentId = "PM" + Payment.getNextPaymentCount();
                            System.out.println("\t\t|                                                                                                                 |");
                            System.out.printf("\t\t|  Payment %d :                                                                                                    |\n", a + 1);
                            System.out.println("\t\t| ----------------                                                                                                |");
                            System.out.printf("\t\t|   1. Payment ID                                : %s\n", paymentId);
                            System.out.println("\n\t\t                                                                                                                  |");
                            
                            //auto-generated date
                            System.out.print("\t\t|   2. Date                                      : " + paymentDate.toString());
                            System.out.println("\n\t\t                                                                                                                  |");

                            //Show order list
                            OrderDrive.viewOrder();
                            System.out.println("\n\t\t|                                                                                                                 |");
                            // Consume the leftover newline
                            scan.nextLine(); 

                            // Validate Order ID
                            validOrderId = false;
                            Order selectedOrder = null;
                            System.out.print("\t\t|   3. Enter Order ID you wish to make payment   : ");
                            
                            while (!validOrderId) {
                                orderId = scan.nextLine(); // Read input
                                orderIndex = OrderDrive.findOrderIndexById(orderId);

                                if (orderIndex != -1) {
                                    selectedOrder = OrderDrive.getOrderByIndex(orderIndex);
                                    double paymentAmount = OrderDrive.getOrderAmountById(orderId);
                                    System.out.println("\t\t|                                                                                                                 |");
                                    System.out.println("\t\t|   4. Payment Amount                            : RM" + paymentAmount);
                                    validOrderId = true; // Valid ID found, exit loop
                                } else {
                                    // If ID is invalid or not found, prompt user to re-enter
                                    System.out.println("\t\t|       -- Order ID not found! Please enter a valid ID! --                                                        |");
                                    System.out.println("\t\t|                                                                                                                 |");
                                    System.out.print("\t\t|      Re-enter Order ID                         : ");
                                }
                            }
                            
                            // Read payment method
                            validPaymentMethod = false;
                            String paymentMethod;
                            System.out.println("\t\t|                                                                                                                 |");
                            System.out.print("\t\t|   5. Enter Payment Method(TNG/BANK)            : ");
                                paymentMethod = scan.nextLine().toUpperCase(); // Read input and change to upper case

                                if ("TNG".equals(paymentMethod) || "BANK".equals(paymentMethod)) {
                                    validPaymentMethod = true; // Valid ID found, exit loop
                                } else {
                                    // If payment method is invalid, prompt user to re-enter
                                    System.out.println("\t\t|       -- Payment Method not support! Please enter a valid payment method! --                                    |");
                                    System.out.println("\t\t|                                                                                                                 |");
                                    System.out.print("\t\t|      Re-enter Payment Method                 : ");
                                }
                            

                            
                            System.out.println("\t\t                                                                                                                  |");
                            System.out.println("\t\t------------------------------------------------------------------------------------------------------------------");

                            // Create a new payment object and add it to the array
                            double paymentAmount = OrderDrive.getOrderAmountById(orderId);
                            processPaymentList[a] = new Payment(paymentDate, selectedOrder, paymentAmount, paymentMethod);

                            // Write to the file
                            writePayment.write(processPaymentList[a].toString() + "\n");
                            
                            //display the simple transaction
                            System.out.println("\t\t_____________________________________________________________");
                            System.out.println("\t\t| ID     : " + processPaymentList[a].paymentId);
                            System.out.println("\t\t| DATE   : " + processPaymentList[a].paymentDate);
                            System.out.println("\t\t| AMOUNT : RM" + processPaymentList[a].order.getOrderAmount());
                            System.out.println("\n\t\t| You have paid RM" + processPaymentList[a].order.getOrderAmount() + " by " + processPaymentList[a].paymentMethod);
                            System.out.println("\t\t| Thank You!|");
                            System.out.println("\t\t-------------------------------------------------------------");

                        }
                        System.out.println("\n\n\t\t   ** Paid successfully! **");

                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred!!! **");
                        e.printStackTrace();
                    }

                    viewPaymentHistory();
                    continueAddingPaym();
                } else {
                    System.out.println("\t\t       ---  Invalid input!!! Please enter number of payments (>0) -- \n");
                    validNumPaym = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input!!! Please enter number of payments (>0) -- ");
                validNumPaym = false;
            }
        } while (!validNumPaym);
    }

    //Method to continue process payment
    public static void continueAddingPaym() throws IOException {

        // Let user choose to continue process payment or back to payment menu
        System.out.println("\n\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Process Payment     |");
        System.out.println("\t\t\t\t| 2. Back to payment Menu         |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choice = Home.getValidChoice(2);

        switch (choice) {
            case 1:
                processPayment();
                break;
            case 2:
                paymentMenu();
                break;
        }
    }

    // Method to view payment history list
    public static void viewPaymentHistory() {

        Home.clearScreen();
        File paymList = new File("payment.txt");

        // Initialize the array for the display payment history list
        viewPaymentHistoryList = new Payment[MAX_PAYMENT];

        System.out.println("\n\n\t\t\t\t\t\t\t PAYMENTT HISTORY");
        System.out.println("\t\t\t\t\t\t\t--------------------");
        System.out.println("\t\t  _________________________________________________________________________________________");
        System.out.println("\t\t  |     ID     |       DATE        |   ORDER ID   |  Payment Amount(RM)  | Payment Method |");
        System.out.println("\t\t  -----------------------------------------------------------------------------------------");

        // Read the file again to show all payment history
        try (Scanner fileScanner = new Scanner(paymList)) {

            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String paymentData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (paymentData.isEmpty()) {
                    continue;
                }

                // Get the detail by separate using "|"
                String[] paymentDetails = paymentData.split("\\|");

                // Ensure the line has exactly 5 elements
                if (paymentDetails.length == 5) {
                    String paymentId = paymentDetails[0];
                    LocalDate paymentDate = LocalDate.parse(paymentDetails[1].trim());
                    String orderId = paymentDetails[2];
                    double paymentAmount = Double.parseDouble(paymentDetails[3].trim());
                    String paymentMethod = paymentDetails[4];
                    
                    // Create dummy Category and Supplier objects or retrieve from lists
                    Order order = OrderDrive.getOrderById(orderId);
                    paymentAmount = OrderDrive.getOrderAmountById(orderId);

                    // Create a new payment object and add it to the list
                    viewPaymentHistoryList[g] = new Payment(paymentDate, order, paymentAmount, paymentMethod);
                    viewPaymentHistoryList[g].setPaymentId(paymentId);

                    // Print the supplier information
                    System.out.printf("\t\t  |   %-9s|     %-12s  |    %-9s |       %-15.2f|      %-10s|\n", paymentId, paymentDate, orderId, paymentAmount, paymentMethod);
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Payment data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  -----------------------------------------------------------------------------------------");

    }

    // Method to let user choose to back to payment menu after view payment history
    public static void backToPaymentMenu() throws IOException {

        System.out.println("\n\n\t\t  Back to Payment Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceY = scan.next().toUpperCase().charAt(0);

                if (choiceY == 'Y') {
                    validChoiceY = true;
                    scan.nextLine();
                    Home.clearScreen();
                    paymentMenu();
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
    
        // Method to read the detail from the text file and populate processPaymentList
    public static void loadPaymentData() {
        if (processPaymentList == null) {
            processPaymentList = new Payment[MAX_PAYMENT]; // Initialize if null
        }

        try (Scanner fileScanner = new Scanner(new File("payment.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String paymentData = fileScanner.nextLine().trim();
                if (!paymentData.isEmpty()) {
                    String[] paymentDetails = paymentData.split("\\|");
                    if (paymentDetails.length == 5) {
                        if (index >= processPaymentList.length) {
                            System.out.println("\n\t\t  -- Error!!! Payment list is full! --");
                            break;
                        }

                        String paymentId = paymentDetails[0];
                        LocalDate paymentDate = LocalDate.parse(paymentDetails[1].trim());
                        String orderId = paymentDetails[2];
                        double paymentAmount = Double.parseDouble(paymentDetails[3].trim());
                        String paymentMethod = paymentDetails[4];
                        
                        // Create order objects using their ID and order amount
                        Order order = OrderDrive.getOrderById(orderId);
                        paymentAmount = OrderDrive.getOrderAmountById(orderId);
                        
                        processPaymentList[index] = new Payment(paymentDate, order, paymentAmount, paymentMethod);
                        processPaymentList[index].setPaymentId(paymentId);

                        index++;
                    } else {
                        System.out.println("\n\t\t  -- Error!!! payment data is missing or corrupted in the file! --");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t ** Can't find the file!!! **");
            e.printStackTrace();
        }
    }

    // Method to find the index of a payment by its ID
    public static int findPaymentIndexById(String id) {
        // Ensure payment data is loaded
        if (processPaymentList == null) {
            loadPaymentData(); // Load payment data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            return -1; // Invalid input
        }

        // Loop through the list to find the payment
        for (int i = 0; i < processPaymentList.length; i++) {
            // Check for null elements in the list and payment id found
            if (processPaymentList[i] != null && id.equals(processPaymentList[i].getPaymentId())) {
                return i;
            }
        }

        // Category not found
        return -1;
    }
    
    // Method to get a Paymet by its ID
    public static Payment getPaymentById(String id) {
        // Ensure Order data is loaded
        if (processPaymentList == null) {
            loadPaymentData(); // Load Payment data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("Invalid ID provided.");
            return null; // Invalid input
        }

        // Loop through the list to find the Payment
        for (int i = 0; i <processPaymentList.length; i++) {
            // Check for null elements in the list and Order id found
            if (processPaymentList[i] != null && id.equals(processPaymentList[i].getPaymentId())) {
                return processPaymentList[i]; // Return the found Payment
            }
        }

        // Payment not found
        System.out.println("Payment with ID " + id + " not found.");
        return null;
    }
    
    // Method to get the unit cost of a Payment by its ID
    public static double getPaymentAmountById(String id) {
        // Ensure product data is loaded
        if (processPaymentList == null) {
            loadPaymentData(); // Load Payment data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return -1; // Invalid input
        }

        // Loop through the list to find the Payment
        for (int i = 0; i < processPaymentList.length; i++) {
            // Check for null elements in the list and Payment id found
            if (processPaymentList[i] != null && id.equals(processPaymentList[i].getPaymentId())) {
                return processPaymentList[i].getPaymentAmount(); // Return the Payment amount
            }
        }

        // Payment not found
        System.out.println("\t\t\t    -- Payment with ID " + id + " not found.");
        return -1; // Indicating that the payment was not found
    }
    
    // Method to get a Order by its index
    public static Payment getPaymentByIndex(int index) {
        // Ensure Payment data is loaded
        if (processPaymentList == null) {
            loadPaymentData(); // Load Payment data if not already loaded
        }

        // Check if the index is valid
        if (index < 0 || index >= processPaymentList.length || processPaymentList[index] == null) {
            System.out.println("Invalid index provided.");
            return null; // Invalid input
        }

        // Return the Payment at the specified index
        return processPaymentList[index];
    }
}