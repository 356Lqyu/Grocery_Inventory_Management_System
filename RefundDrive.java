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

public class RefundDrive {
    private static Refund[] addRefundList;
    private static Refund[] viewRefundList;
    private static Refund[] cancelRefundList;
    
    public static char choiceR;
    static boolean validChoiceR = false;
    private static boolean validChoice;
    private static final int MAX_REFUND = 100;
    private static final LocalDate refundDate = LocalDate.now();
    
    //Scanner
    private static final Scanner scan = new Scanner(System.in);
    
    public static void refundMenu() throws IOException {

        Home.clearScreen();

        try {
            File myCat = new File("refund.txt");
            if (myCat.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + myCat.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
            e.printStackTrace();
        }

        System.out.println("\n\n");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                          REFUND MENU                          |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. ADD Refund                                           |");
        System.out.println("\t\t|       2. VIEW Refund                                          |");
        System.out.println("\t\t|       3. CANCEL Refund                                        |");
        System.out.println("\t\t|       4. Back                                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // DO-WHILE loop handles incorrect input selections
        int choiceR = Home.getValidChoice(4);

        switch (choiceR) {
            case 1:
                addRefund();
                break;
            case 2:
                viewRefund();
                backToRefundMenu();
                break;
            case 3:
                cancelRefund();
                break;
            case 4:
                Home.homeMenu();
                break;
        }
    }
    
    public static void addRefund() throws IOException{ 
        int numRef;
        boolean validNumRef;
        boolean validQuantity;
        boolean validPaymentId = false;
        int paymentIndex = -1;
        
        double refundAmount = 0;
        String paymentId = ""; // Ensure paymentId is declared and initialized

        Home.clearScreen();

        // Read the text file to get the last product ID, then increment it
        try (BufferedReader readRefund = new BufferedReader(new FileReader("refund.txt"))) {
            String refLastLine = "";
            String line;
            while ((line = readRefund.readLine()) != null) {
                refLastLine = line;
            }
            if (!refLastLine.isEmpty()) {
                String lastRefundId = refLastLine.split("\\|")[0];
                if (lastRefundId.startsWith("RF") && lastRefundId.length() > 1) {

                    try {
                        Refund.nextRefundCount = Integer.parseInt(lastRefundId.substring(2)) + 1;
                    } catch (NumberFormatException e) {
                        System.out.println("\n\n\t\t   ** Invalid Payment ID format in the file! **");
                        e.printStackTrace();
                    }

                    
                } else {
                    System.out.println("\n\n\t\t   ** Invalid Refund ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading refund IDs! **");
            e.printStackTrace();
        }

        do {
            // Let the user enter the number of refunds to add
            System.out.print("\n\t\tEnter the number of refunds to make: ");
            try {
                numRef = scan.nextInt();
                scan.nextLine();
                if (numRef > 0) {
                    validNumRef = true;

                    // Initialize the array with the size entered
                    addRefundList = new Refund[numRef];

                    System.out.println("\n\n");    
                    System.out.println("\t\t============================================================================================");
                    System.out.println("\t\t|                                      ADD REFUND                                          |");
                    System.out.println("\t\t============================================================================================");

                    try (FileWriter writeRefund = new FileWriter("refund.txt", true)) {
                        for (int a = 0; a < addRefundList.length; a++) {
                            
                            
                            String refundId = "RF" + Refund.getNextRefundCount();
                  
                            System.out.println("\t\t|                                                                                          |");
                            System.out.printf("\t\t|  Refund %d :                                                                              |\n", a + 1);
                            System.out.println("\t\t| ----------------                                                                         |");
                            System.out.printf("\t\t|   1. Refund ID                                 : %s\n", refundId);
                            System.out.println("\t\t                                                                                           |");
                            
                            System.out.printf("\t\t|   2. Refund Date                               : " + refundDate.toString());
                            System.out.println("\n\t\t                                                                                           |");

                            //Show payment history
                            PaymentDrive.viewPaymentHistory();
                            System.out.println("\t\t                                                                                           |");
                            // Consume the leftover newline
                            scan.nextLine(); 

                            // Validate payment ID
                            validPaymentId = false; 
                            Payment selectedPayment = null;
                            System.out.print("\t\t|   3. Enter Payment ID                          : ");
                            while (!validPaymentId) {
                                paymentId = scan.nextLine(); // Read input
                                
                            
                            
                                paymentIndex = PaymentDrive.findPaymentIndexById(paymentId);

                                if (paymentIndex != -1) {
                                    selectedPayment = PaymentDrive.getPaymentByIndex(paymentIndex);
                                    System.out.println("\t\t                                                                                           |");
                                 
                                    System.out.println("\t\t|   4. Refund Amount                             : RM" + refundAmount);
                                    validPaymentId = true; // Valid ID found, exit loop
                                } else {
                                    // If ID is invalid or not found, prompt user to re-enter
                                    System.out.println("\t\t|       -- Payment ID not found! Please enter a valid ID! --                               |");
                                    System.out.println("\t\t                                                                                           |");
  
                                    System.out.print("\t\t|      Re-enter Payment ID                        : ");
                                }
                            }
                            
                            System.out.println("\t\t                                                                                           |");

                           
                            System.out.print("\t\t|   5. Enter Reason for Refund                   : ");
                            String reason = scan.nextLine();
                            System.out.println("\t\t                                                                                           |");
                        
                        System.out.println("\t\t                                                                                           |");
                        System.out.println("\t\t--------------------------------------------------------------------------------------------");

                        // Create a new payment object and add it to the array
                        addRefundList[a] = new Refund(refundDate, selectedPayment, reason, refundAmount);
                        
                        // Write to the file
                        writeRefund.write(addRefundList[a].toString() + "\n");
                        
                        //display the simple transaction
                            System.out.println("\t\t_____________________________________________________________");
                            System.out.println("\t\t| ID     : " + addRefundList[a].refundId);
                            System.out.println("\t\t| DATE   : " + addRefundList[a].refundDate);
                            System.out.println("\t\t| AMOUNT : RM" + addRefundList[a].refundAmount);
                            System.out.println("\n\t\t| The amount have refunded into your previous payment method.");
                            System.out.println("\t\t| Thank You!|");
                            System.out.println("\t\t-------------------------------------------------------------");

                        

                        }
                        System.out.println("\n\n\t\t   ** Refund request successfully! **");

                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred!!! **");
                        e.printStackTrace();
                    }

                    viewRefund();
                    continueAddingRef();
                    
                } else {
                    System.out.println("\t\t       ---  Invalid input!!! Please enter number of refunds (>0) -- \n");
                    validNumRef = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input!!! Please enter number of refunds (>0) -- ");
                validNumRef = false;
            }
        } while (!validNumRef);
    }
    
    //Method to continue add refund
    public static void continueAddingRef() throws IOException {

        // Let user choose to continue adding refund or back to refund menu
        System.out.println("\n\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Adding refund       |");
        System.out.println("\t\t\t\t| 2. Back to refund Menu          |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choice = Home.getValidChoice(2);

        switch (choice) {
            case 1:
                addRefund();
                break;
            case 2:
                refundMenu();
                break;
        }
    }
    
    public static void viewRefund(){
        Home.clearScreen();
        File refList = new File("refund.txt");

        // Initialize the array for the display refund list
        viewRefundList = new Refund[MAX_REFUND];

        System.out.println("\n\n\t\t\t\t\t\t\t\t REFUND LIST");
        System.out.println("\t\t\t\t\t\t\t\t---------------");
        System.out.println("\t\t  ___________________________________________________________________________________________________________");
        System.out.println("\t\t  |     ID     |        DATE         |   PAYMENT ID   |             Reason             |  Refund Amount(RM) |");
        System.out.println("\t\t  -----------------------------------------------------------------------------------------------------------");

        // Read the file again to show all refund details
        try (Scanner fileScanner = new Scanner(refList)) {

            int f = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String refundData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (refundData.isEmpty()) {
                    continue;
                }

                // Get the detail by separate using "|"
                String[] refundDetails = refundData.split("\\|");

                // Ensure the line has exactly 5 elements
                if (refundDetails.length == 5) {
                    String refundId = refundDetails[0];
                    LocalDate refundDate = LocalDate.parse(refundDetails[1].trim());
                    String paymentId = refundDetails[2];
                    double refundAmount = Double.parseDouble(refundDetails[3].trim());
                    String reason = refundDetails[4];
                    
                    
                    Payment payment = PaymentDrive.getPaymentById(paymentId);
                    refundAmount = PaymentDrive.getPaymentAmountById(paymentId);

                    // Create a new Refund object and add it to the list
                    viewRefundList[f] = new Refund(refundDate, payment, reason, refundAmount);
                    viewRefundList[f].setRefundId(refundId);

                    // Print the supplier information
                    System.out.printf("\t\t  |   %-9s|     %-16s|     %-11s|            %-19s |         %-11f|\n", refundId, refundDate, paymentId, reason, refundAmount);
                    f++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Product data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  -----------------------------------------------------------------------------------------------------------");

    }
    
    public static void backToRefundMenu() throws IOException {

        System.out.println("\n\n\t\t  Back to Refund Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceR = scan.next().toUpperCase().charAt(0);

                if (choiceR == 'Y') {
                    validChoiceR = true;
                    scan.nextLine();
                    Home.clearScreen();
                    refundMenu();
                } else {
                    validChoiceR = false;
                    System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoiceR = false;

                System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)...\n\n");

            }
        } while (!validChoiceR);

    }
    
    public static void cancelRefund() throws IOException{
        boolean validRefundId = false;
        int r = 0; // Tracks the number of refunds loaded from the file

        cancelRefundList = new Refund[MAX_REFUND];

        File delRefList = new File("refund.txt");

        // Read the file again to show all refund details
        try (Scanner fileScanner = new Scanner(delRefList)) {

            while (fileScanner.hasNextLine()) {
                // Read the next line (remove any leading and trailing whitespace characters)
                String refundData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (refundData.isEmpty()) {
                    continue;
                }

                // Get the details by splitting using "|"
                String[] refundDetails = refundData.split("\\|");

                // Ensure the line has exactly 5 elements
                if (refundDetails.length == 5) {
                    String refundId = refundDetails[0];
                    LocalDate refundDate = LocalDate.parse(refundDetails[1].trim());
                    String paymentId = refundDetails[2]; 
                    double refundAmount = Double.parseDouble(refundDetails[3].trim());
                    String reason = refundDetails[4];
                    
                    // Create Product objects using their ID and unit cost
                    Payment payment = PaymentDrive.getPaymentById(paymentId);
                    refundAmount = PaymentDrive.getPaymentAmountById(paymentId);

                    // Create a new Refund object and add it to the list
                    cancelRefundList[r] = new Refund(refundDate, payment, reason, refundAmount);
                    cancelRefundList[r].setRefundId(refundId);
                    r++; // Increment the product count
                } else {
                    System.out.println("\n\t\t  -- Error!!! Refund data is missing or corrupted in the file! --");
                }
            }

            // View all products after loading from the file
            viewRefund();

            System.out.print("\t\t  Enter the refund ID that you want to remove (RFXXXX) : ");

            do {
                String cancelRId = scan.nextLine();

                for (int o = 0; o < r; o++) {
                    if (cancelRId.equals(cancelRefundList[o].getRefundId())) {
                        validRefundId = true;

                        Home.clearScreen();
                        System.out.printf("\n\n\n\t\t\t\t\t\t   Details of Refund with ID(%s)\n", cancelRId);

                        System.out.println("\t\t  ___________________________________________________________________________________________________________");
                        System.out.println("\t\t  |     ID     |        DATE         |   PAYMENT ID   |             Reason             |  Refund Amount(RM) |");
                        System.out.println("\t\t  -----------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\t  |   %-9s|     %-16s|     %-11s|            %-19s |         %-11f|\n",
                                cancelRefundList[o].getRefundId(), cancelRefundList[o].getRefundDate(),
                                cancelRefundList[o].getPayment().getPaymentId(), cancelRefundList[o].getReason(),
                                cancelRefundList[o].getRefundAmount());
                        System.out.println("\t\t  ------------------------------------------------------------------------------------------------------------");

                        System.out.printf("\t\t  Are you sure you want to cancel the refund with ID(%s)? ", cancelRId);
                        boolean validChoiceR = false;
                        char choiceR;

                        do {
                            System.out.print("(Y/N) : ");
                            choiceR = scan.next().toUpperCase().charAt(0);

                            if (choiceR == 'Y' || choiceR == 'N') {
                                validChoiceR = true;
                                scan.nextLine();

                                if (choiceR == 'Y') {
                                    // Shift the product in the array to remove the chosen product
                                    for (int p = o; p < r - 1; p++) {
                                        cancelRefundList[p] = cancelRefundList[p + 1];
                                    }
                                    cancelRefundList[r - 1] = null; // Clear the last entry
                                    r--; // Reduce the count of refunds

                                    // Update the file after cancelation
                                    try (FileWriter writeProduct = new FileWriter("refund.txt")) {
                                        for (int i = 0; i < r; i++) {
                                            Refund refund = cancelRefundList[i];
                                            if (refund != null) {
                                                writeProduct.write(refund.toString() + "\n");
                                            }
                                        }
                                        System.out.println("\t\t\t\t\t -- Refund file updated successfully! --");
                                    } catch (IOException e) {
                                        System.out.println("\t\t\t\t\t -- An error occurred while updating the refund file. --");
                                        e.printStackTrace();
                                    }

                                    System.out.println("\t\t\t\t\t -- Refund cancelled successfully! --");

                                    
                                } else {
                                    System.out.println("\t\t\t\t\t -- Refund cancellation unseccessful! --");
                                }
                                
                                // Show updated product list
                                viewRefund();

                                // Let user make selection to return Product Menu
                                backToRefundMenu();
                            } else {
                                System.out.println("\t\t\t        --- Invalid choice! Please enter Y or N...");
                            }
                        } while (!validChoiceR);

                        break; // Exit loop after handling the ID
                    }
                }

                if (!validRefundId) {
                    System.out.println("\t\t\t     -- Refund ID not found! --");
                    System.out.print("\n\t\t  Re-enter Valid ID (RFXXXX) : ");
                }
            } while (!validRefundId);

            
                                    
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
    }
    
}