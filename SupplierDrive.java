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

public class SupplierDrive {

    private static Supplier[] addSupplierList;
    private static Supplier[] viewSupplierList;
    private static Supplier[] modifySupplierList;
    private static final int MAX_SUPPLIER = 100;

    private static char choiceY;
    private static boolean validChoiceY = false;

    //Scanner
    private static final Scanner scan = new Scanner(System.in);

    // Supplier Menu
    public static void supplierMenu() {

        Home.clearScreen();

        //Create Supplier File
        try {
            File mySupp = new File("supplier.txt");
            if (mySupp.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + mySupp.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
            e.printStackTrace();
        }

        System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                         SUPPLIER MENU                         |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. ADD Supplier                                         |");
        System.out.println("\t\t|       2. VIEW Supplier                                        |");
        System.out.println("\t\t|       3. UPDATE Supplier Info                                 |");
        System.out.println("\t\t|       4. Back                                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        int choice = Home.getValidChoice(4);

        switch (choice) {
            case 1:
                addSupplier();
                break;

            case 2:
                viewSupplier();
                backToSupplierMenu();
                break;

            case 3:
                updateSupplierInfo();
                break;

            case 4:
                Home.homeMenu();
                break;
        }

    }

    // Method for adding suppliers
    public static void addSupplier() {

        //Add supplier quantity
        int numSupp;
        boolean validNumSupp;

        Home.clearScreen();

        // Read the txt file to get the last supplier ID, which is used to create a new ID
        try (BufferedReader readSupplier = new BufferedReader(new FileReader("supplier.txt"))) {
            String suppLastLine = "", line;
            while ((line = readSupplier.readLine()) != null) {
                suppLastLine = line;
            }
            if (!suppLastLine.isEmpty()) {
                // Extract the numeric part of the last supplier ID and increment it
                String lastSupplierId = suppLastLine.split("\\|")[0];
                if (lastSupplierId.startsWith("SP") && lastSupplierId.length() > 2) {
                    Supplier.nextSuppCount = Integer.parseInt(lastSupplierId.substring(2)) + 1;
                } else {
                    System.out.println("\n\n\t\t   ** Invalid supplier ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading supplier IDs! **");
            e.printStackTrace();
        }

        // DO-WHILE loop handles invalid number of supplier entered
        do {
            // Let the user enter the number of suppliers to add
            System.out.print("\n\t\tEnter the number of suppliers to add : ");

            try {
                numSupp = scan.nextInt();
                scan.nextLine();

                if (numSupp > 0) {
                    validNumSupp = true;

                    // Initialize the array with the size entered
                    addSupplierList = new Supplier[numSupp];

                    System.out.println("\n\n\t\t===========================================================================");
                    System.out.println("\t\t|                             ADD SUPPLIER                                |");
                    System.out.println("\t\t===========================================================================");

                    try {
                        // Open file in append mode
                        FileWriter writeSupplier = new FileWriter("supplier.txt", true);

                        for (int a = 0; a < addSupplierList.length; a++) {

                            // The supplier id will be generated by using "SP" + suppliercount (eg : SP1002)
                            String supplierId = "SP" + Supplier.getNextSuppCount();
                            System.out.println("\t\t|                                                                         |");
                            System.out.printf("\t\t|  Supplier %d :                                                           |\n", a + 1);
                            System.out.println("\t\t| ----------------                                                        |");
                            System.out.printf("\t\t|   1. Supplier ID                               : %s\n", supplierId);
                            System.out.println("\t\t                                                                          |");

                            System.out.print("\t\t|   2. Enter Name                                : ");
                            String supplierName = scan.nextLine();
                            System.out.println("\t\t                                                                          |");

                            System.out.print("\t\t|   3. Enter Contact Number (Eg: 0123456789)     : ");
                            String supplierPhoneNum;
                            // Validate the phone number to make sure it is 10-digits numbers
                            while (true) {
                                supplierPhoneNum = scan.nextLine();
                                // Phone Num must be 10 digits
                                if (supplierPhoneNum.matches("\\d{10}")) {
                                    break; // Exit the loop if valid
                                } else {
                                    System.out.println("\t\t|       -- Invalid phone number! Please enter a valid phone number! --    |");
                                    System.out.println("\t\t                                                                          |");
                                    System.out.print("\t\t|      Re-enter Contact Number (Eg: 0123456789)  : ");
                                }
                            }
                            System.out.println("\t\t                                                                          |");

                            System.out.print("\t\t|   4. Enter Email (Eg: abc@gmail.com)           : ");
                            String supplierEmail;
                            // Validate the email to make sure it contains '@gmail.com'
                            while (true) {
                                supplierEmail = scan.nextLine();

                                // Check if the email contains '@gmail.com'
                                if (!supplierEmail.matches("^[\\w.%+-]+@gmail\\.com$")) {
                                    System.out.println("\t\t|       -- Invalid email! Please enter a valid email! --                  |");
                                    System.out.print("\n\t\t|      Re-enter Email (Eg: abc@gmail.com)        : ");
                                } else {
                                    break;  // Exit the loop if the condition is met
                                }
                            }

                            System.out.println("\t\t|                                                                         |");
                            System.out.println("\t\t---------------------------------------------------------------------------");

                            // Create a new Supplier object and add it to array
                            addSupplierList[a] = new Supplier(supplierName, supplierPhoneNum, supplierEmail);

                            // Write to the file
                            writeSupplier.write(addSupplierList[a].toString() + "\n");
                        }

                        writeSupplier.close();
                        System.out.println("\n\n\t\t   ** Added successfully! **");

                        viewSupplier();

                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred!!! **");
                        e.printStackTrace();
                    }

                    continueAddingSupp();
                } else {
                    System.out.println("\t\t       ---  Invalid input!!! Please enter number of supplier (>0)-- \n");
                    validNumSupp = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input!!! Please enter number of supplier (>0)-- ");
                validNumSupp = false;
            }
        } while (!validNumSupp);

    }

    //Method to continue add supplier
    public static void continueAddingSupp() {

        // Let user choose to continue adding supplier or back to supplier menu
        System.out.println("\n\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Adding SUPPLIER     |");
        System.out.println("\t\t\t\t| 2. Back to Supplier Menu        |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choice = Home.getValidChoice(2);

        switch (choice) {
            case 1:
                addSupplier();
                break;
            case 2:
                supplierMenu();
                break;
        }
    }

    // Display Supplier List
    public static void viewSupplier() {

        Home.clearScreen();
        File suppList = new File("supplier.txt");

        // Initialize the array for the display supplier list
        viewSupplierList = new Supplier[MAX_SUPPLIER];

        System.out.println("\n\n\t\t\t\t\t\t\t SUPPLIER LIST");
        System.out.println("\t\t\t\t\t\t\t---------------");
        System.out.println("\t\t  _______________________________________________________________________________________________");
        System.out.println("\t\t  |       ID       |           NAME           |     Contact Num     |           Email           |");
        System.out.println("\t\t  -----------------------------------------------------------------------------------------------");

        // Read the file again to show all supplier details
        try (Scanner fileScanner = new Scanner(suppList)) {

            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String supplierData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (supplierData.isEmpty()) {
                    continue;
                }

                // Get the detail by separate using "|"
                String[] supplierDetails = supplierData.split("\\|");

                // Ensure the line has exactly 4 elements
                if (supplierDetails.length == 4) {
                    String supplierId = supplierDetails[0];
                    String supplierName = supplierDetails[1];
                    String supplierPhoneNum = supplierDetails[2];
                    String supplierEmail = supplierDetails[3];

                    // Create a new Supplier object and add it to the list
                    viewSupplierList[g] = new Supplier(supplierName, supplierPhoneNum, supplierEmail);
                    viewSupplierList[g].setSupplierId(supplierId);

                    // Print the supplier information
                    System.out.printf("\t\t  |     %-11s|     %-21s|     %-16s|     %-22s|\n", supplierId, supplierName, supplierPhoneNum, supplierEmail);
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Supplier data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  -----------------------------------------------------------------------------------------------");

    }

    // Method to let user return supplier menu in viewSupplier function
    public static void backToSupplierMenu() {

        System.out.println("\n\n\t\t  Back to Supplier Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceY = scan.next().toUpperCase().charAt(0);

                if (choiceY == 'Y') {
                    validChoiceY = true;
                    scan.nextLine();
                    Home.clearScreen();
                    supplierMenu();
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

    // Method to update supplier details
    public static void updateSupplierInfo() {

        char yesNoA;
        boolean validYesNoA;
        boolean continueModify;

        boolean validSuppId = false;
        int supplierIndex = -1;

        Home.clearScreen();

        // Read the detail from the text file and populate modifySupplierList
        loadSupplierData();

        // Display the supplier list and let the user select the supplier to modify by entering the supplier ID
        viewSupplier();

        System.out.print("\n\t\tEnter the ID of the supplier you want to modify (SPXXXX) : ");
        // DO-WHILE loop to make sure the ID matches
        do {
            String modifySId = scan.nextLine();

            // Check for empty input
            if (modifySId.isEmpty()) {
                System.out.println("\t\t\t     -- Supplier ID cannot be empty! --");
                System.out.print("\n\t\tRe-enter a valid ID (SPXXXX) : ");
                continue; // Prompt for ID again
            }

            // Find supplier index with the match id
            supplierIndex = findSupplierIndexById(modifySId);

            if (supplierIndex != -1) {
                validSuppId = true;

                Home.clearScreen();

                // Display the specified supplier's detail with match id
                displaySupplierDetails(modifySId, supplierIndex);

                System.out.println("\n\n\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("\t\t\t|  Modify                                             |");
                System.out.println("\t\t\t- ................................................... -");
                System.out.println("\t\t\t|          1. Supplier Name                           |");
                System.out.println("\t\t\t!          2. Supplier Contact Number                 |");
                System.out.println("\t\t\t|          3. Supplier Email Address                  |");
                System.out.println("\t\t\t|          4. Return Supplier Menu                    |");
                System.out.println("\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");

                int choice = Home.getValidChoice(4);

                switch (choice) {
                    case 1:

                        Home.clearScreen();

                        System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");
                        System.out.printf("\t\t\t ' Current Name : %-20s'\n", modifySupplierList[supplierIndex].getSupplierName());
                        System.out.println("\t\t\t '''''''''''''''''''''''''''''''''''''''");

                        System.out.println("\n\n\t\t\t Modify Supplier Name:");
                        System.out.println("\t\t\t-------------------------------");
                        System.out.print("\t\t\t Enter new name : ");
                        String modifySName = scan.nextLine();
                        // Confirm modification
                        if (Home.confirmModification(modifySupplierList[supplierIndex].getSupplierName(), modifySName, "name")) {
                            modifySupplierList[supplierIndex].setSupplierName(modifySName);
                            System.out.println("\t\t\t\t\t -- Modification successful! --");
                        }
                        break;

                    case 2:
                        Home.clearScreen();
                        System.out.println("\t\t\t ''''''''''''''''''''''''''''''''''''''''''''''''");
                        System.out.printf("\t\t\t ' Current Contact Number : %-20s'\n", modifySupplierList[supplierIndex].getSupplierPhoneNum());
                        System.out.println("\t\t\t ''''''''''''''''''''''''''''''''''''''''''''''''");

                        System.out.println("\n\n\t\t\t Modify Supplier Contact Number:");
                        System.out.println("\t\t\t--------------------------------------");
                        System.out.print("\t\t\t Enter new contact number (Eg: 0123456789) : ");
                        String modifySPhoneNum = scan.nextLine();
                        // Validate the contact number to make sure it is 10-digits
                        while (!modifySPhoneNum.matches("\\d{10}")) {
                            System.out.print("\t\t\t\t-- Invalid phone number! \n");
                            System.out.print("\t\t\t Re-enter contact number (0123456789)  : ");
                            modifySPhoneNum = scan.nextLine();
                        }
                        // Confirm modification
                        if (Home.confirmModification(modifySupplierList[supplierIndex].getSupplierPhoneNum(), modifySPhoneNum, "contact number")) {
                            modifySupplierList[supplierIndex].setSupplierPhoneNum(modifySPhoneNum);
                            System.out.println("\t\t\t\t\t -- Modification successful! --");
                        }
                        break;

                    case 3:
                        Home.clearScreen();
                        System.out.println("\t\t\t ''''''''''''''''''''''''''''''''''''''''''''''''");
                        System.out.printf("\t\t\t ' Current Gmail : %-29s'\n", modifySupplierList[supplierIndex].getSupplierEmail());
                        System.out.println("\t\t\t ''''''''''''''''''''''''''''''''''''''''''''''''");

                        System.out.println("\n\n\t\t\t Modify Supplier Gmail Address:");
                        System.out.println("\t\t\t--------------------------------------");
                        System.out.print("\t\t\t Enter new email (Eg: abc@gmail.com) : ");
                        String modifySEmail = scan.nextLine();
                        // Validate the email to make sure it contain '@gmail.com'
                        while (!modifySEmail.matches("^[\\w.%+-]+@gmail\\.com$")) {
                            System.out.println("\t\t\t    -- Invalid email! Please enter valid email! --");
                            System.out.print("\n\t\t\tRe-enter Email (Eg: abc@gmail.com)        : ");
                            modifySEmail = scan.nextLine();
                        }
                        // Confirm modification
                        if (Home.confirmModification(modifySupplierList[supplierIndex].getSupplierEmail(), modifySEmail, "email")) {
                            modifySupplierList[supplierIndex].setSupplierEmail(modifySEmail);
                            System.out.println("\t\t\t\t\t -- Modification successful! --");
                        }
                        break;

                    case 4:
                        supplierMenu();
                        break;
                }

                // Save the modified info to the text file
                try (FileWriter writeModifySupp = new FileWriter("supplier.txt")) {
                    for (Supplier supplier : modifySupplierList) {
                        if (supplier != null) {
                            writeModifySupp.write(supplier.toString() + "\n");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("\n\t\t-- An error occurred while saving! --");
                    e.printStackTrace();
                }

                Home.clearScreen();

                // Show the updated supplier list
                viewSupplier();

                // Make selection for continue modify or back to supplier menu
                continueModifySupp();

            } else {
                // If ID invalid or not found, let user re-enter
                System.out.println("\t\t\t     -- Supplier ID not found! --");
                System.out.print("\n\t\tRe-enter Valid ID (SPXXXX) : ");
            }
        } while (!validSuppId);
    }

    // Method to read the detail from the text file and populate modifySupplierList
    public static void loadSupplierData() {
        if (modifySupplierList == null) {
            modifySupplierList = new Supplier[MAX_SUPPLIER]; // Initialize if null
        }

        try (Scanner fileScanner = new Scanner(new File("supplier.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String supplierData = fileScanner.nextLine().trim();
                if (!supplierData.isEmpty()) {
                    String[] supplierDetails = supplierData.split("\\|");
                    if (supplierDetails.length == 4) {
                        if (index >= modifySupplierList.length) {
                            System.out.println("\n\t\t  -- Error!!! Supplier list is full! --");
                            break;
                        }
                        modifySupplierList[index] = new Supplier(supplierDetails[1], supplierDetails[2], supplierDetails[3]);
                        modifySupplierList[index].setSupplierId(supplierDetails[0]);
                        index++;
                    } else {
                        System.out.println("\n\t\t  -- Error!!! Supplier data is missing or corrupted in the file! --");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t ** Can't find the file!!! **");
            e.printStackTrace();
        }
    }

    // Method to find the index of a supplier by its ID
    public static int findSupplierIndexById(String id) {
        // Ensure supplier data is loaded
        if (modifySupplierList == null) {
            loadSupplierData(); // Load supplier data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            return -1; // Invalid input
        }

        // Loop through the list to find the supplier
        for (int i = 0; i < modifySupplierList.length; i++) {
            // Check for null elements in the list and supplier id found
            if (modifySupplierList[i] != null && id.equals(modifySupplierList[i].getSupplierId())) {
                return i;
            }
        }

        // Category not found
        return -1;
    }

    // Method to get a supplier by its ID
    public static Supplier getSupplierById(String id) {
        // Find the index of the supplier by ID
        int index = findSupplierIndexById(id);

        // Check if the supplier exists
        if (index != -1) {
            return modifySupplierList[index]; // Return the supplier if found
        } else {
            System.out.println("\n\t\t\t    -- Supplier not found! --");
            return null; // Return null if not found
        }
    }

    // Method to get a supplier by its index
    public static Supplier getSupplierByIndex(int index) {
        // Ensure supplier data is loaded
        if (modifySupplierList == null) {
            loadSupplierData(); // Load supplier data if not already loaded
        }

        // Check for valid index
        if (index >= 0 && index < modifySupplierList.length) {
            return modifySupplierList[index]; // Return the supplier if found
        } else {
            System.out.println("\n\t\t  -- Invalid index! --");
            return null; // Return null if index is invalid
        }
    }

    // Method to display supplier details with ID
    private static void displaySupplierDetails(String supplierId, int supplierIndex) {
        System.out.printf("\n\n\n\t\t\t\t\t\tDetails of Supplier with ID(%s)\n", supplierId);
        System.out.println("\t\t _______________________________________________________________________________________");
        System.out.println("\t\t |       ID       |           NAME           |     Contact Num     |       Email       |");
        System.out.println("\t\t ---------------------------------------------------------------------------------------");
        System.out.printf("\t\t |      %-10s|     %-21s|     %-16s|    %-15s|\n", modifySupplierList[supplierIndex].getSupplierId(),
                modifySupplierList[supplierIndex].getSupplierName(), modifySupplierList[supplierIndex].getSupplierPhoneNum(), modifySupplierList[supplierIndex].getSupplierEmail());
        System.out.println("\t\t ---------------------------------------------------------------------------------------");
    }

    // Method for continue modify the detail of supplier in modifySupplierInfo function
    private static void continueModifySupp() {

        System.out.println("\n\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| Choice                              |");
        System.out.println("\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Modify                  |");
        System.out.println("\t\t\t\t| 2. Back to Supplier Menu            |");
        System.out.println("\t\t\t\t---------------------------------------");

        int choice = Home.getValidChoice(4);

        switch (choice) {
            case 1:
                updateSupplierInfo();
                break;
            case 2:
                supplierMenu();
                break;
        }
    }

    // Method to print simple list of supplier(is used in Product.addProduct(), so the user can enter it when adding a supplier id)
    // Only list out the supplier id and name
    public static void showSupplierList() {

        File suppList = new File("supplier.txt");

        // Initialize the array for the display category list
        Supplier[] supplierList = new Supplier[MAX_SUPPLIER];

        System.out.println("\t\t|                             SUPPLIER LIST");
        System.out.println("\t\t             ..............................................               |");
        System.out.println("\t\t|            :    Supplier ID    :          Name          : ");
        System.out.println("\t\t             :............................................:               |");

        // Read the file to show supplier's id and name
        try (Scanner fileScanner = new Scanner(suppList)) {
            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String supplierData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (supplierData.isEmpty()) {
                    continue;
                }

                // Split the supplier details by the delimiter "|"
                String[] supplierDetails = supplierData.split("\\|");

                // Ensure the line has exactly 2 elements
                if (supplierDetails.length == 4) {
                    String supplierId = supplierDetails[0];
                    String supplierName = supplierDetails[1];
                    String supplierPhoneNum = supplierDetails[2];
                    String supplierEmail = supplierDetails[3];

                    // Create a new supplier object using the supplierName
                    supplierList[g] = new Supplier(supplierName, supplierPhoneNum, supplierEmail);
                    supplierList[g].setSupplierId(supplierId);

                    // Print the supplier information using simpleSuppierList()
                    System.out.print(simpleSuppierList(supplierList[g]));
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Supplier data is missing or corrupted in the file! --");
                }

            }
            System.out.println("\t\t|            :............................................:               |");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }

    }

    //Use to print simple list of supplier( use in showSupplierList())
    public static String simpleSuppierList(Supplier supplier) {
        // Define column widths
        int idWidth = 11;
        int nameWidth = 17;

        // Format the string to align columns
        return String.format("\t\t|            :       %-" + idWidth + "s:        %-" + nameWidth + "s:               |\n", supplier.getSupplierId(), supplier.getSupplierName());
    }

    // Method to print simple list of supplier(is used in Product.updateProduct(), so the user can enter it when adding a supplier id) - in table format
    public static void showSuppTable() {

        File suppList = new File("supplier.txt");

        // Initialize the array for the display supplier list
        Supplier[] supplierList = new Supplier[MAX_SUPPLIER];

        System.out.println("\n\n\t\t\t                SUPPLIER LIST");
        System.out.println("\t\t      ..............................................");
        System.out.println("\t\t      :    SUPPLIER ID    :          Name          :");
        System.out.println("\t\t      :............................................:");

        // Read the file to show all Supplier details
        try (Scanner fileScanner = new Scanner(suppList)) {
            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String supplierData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (supplierData.isEmpty()) {
                    continue;
                }

                // Split the supplier details by the delimiter "|"
                String[] supplierDetails = supplierData.split("\\|");

                // Ensure the line has exactly 2 elements
                if (supplierDetails.length == 4) {
                    String supplierId = supplierDetails[0];
                    String supplierName = supplierDetails[1];
                    String supplierPhoneNum = supplierDetails[2];
                    String supplierEmail = supplierDetails[3];

                    // Create a new supplier object
                    supplierList[g] = new Supplier(supplierName, supplierPhoneNum, supplierEmail);
                    supplierList[g].setSupplierId(supplierId);

                    // Print the category information
                    System.out.printf("\t\t      :       %-11s :        %-15s :\n", supplierList[g].getSupplierId(), supplierList[g].getSupplierName());
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Supplier data is missing or corrupted in the file! --");
                }

            }
            System.out.println("\t\t      :............................................:");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }

    }
    
 

}
