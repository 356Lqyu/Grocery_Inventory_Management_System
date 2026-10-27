/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */


import static assignment.Home.scan;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Administrator extends User {

    // File paths
    private static final String ADMIN_FILE_PATH = "admin.txt";
    private static final String USER_FILE_PATH = "user.txt";

    
    private static final Scanner scan = new Scanner(System.in);
    
    // Constructor
    public Administrator() {
        super("", "", "", "", "");
    }

    public Administrator(String userId, String userPassword, String userName, String userPhone, String userEmail) {
        super(userId, userPassword, userName, userPhone, userEmail);
    }
    
    
    
    // Initial admin menu ( login success , go adminMenu)
    public static void adminSectionMenu() {
        
        Home.clearScreen();
        
        System.out.println("\n\n\t\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t\t\t\t\t|                      ADMINISTRATOR SECTION                    |");
        System.out.println("\t\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t\t\t\t\t|       1. Register Admin                                       |");
        System.out.println("\t\t\t\t\t\t|       2. Login                                                |");
        System.out.println("\t\t\t\t\t\t|       3. Back                                                 |");
        System.out.println("\t\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("\t\t\t\t\t\tChoose an option: ");
    }
    
    
    
    // Handle the admin login and display the admin menu
    public static void adminLogin(Scanner scanner) {
        
        Home.clearScreen();
        boolean foundUser = false;
        
        // Consume the leftover newline
        scanner.nextLine(); 

        System.out.println("\n\n\t\t                       ADMINISTRATOR LOGIN                      ");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

        
        do{
        System.out.print("\t\t   1.  Enter Admin ID (Eg: AD123)    : ");
        String adminId = scanner.nextLine();
 
        System.out.print("\n\t\t   2.  Enter Admin Password          : ");
        String adminPassword = scanner.nextLine();

        if (isValidAdmin(adminId, adminPassword)) {
            boolean isAdminLoggedIn = true;
            while (isAdminLoggedIn) {
                adminMenu(scanner);
                
            }
        } else {
            System.out.println("\n\t\t\t -- Invalid admin credentials! Please try again\n\n");
        }
        }while(!foundUser);
    }
    
    
    
    
    // Admin Main Menu
    public static void adminMenu(Scanner scanner){
        
        Home.clearScreen();
        
        System.out.println("\n\n     GGGGGG   RRRRRRR    OOOOO    CCCCCC EEEEEEEE RRRRRRR  YY     YY       IIIIIIII NN    NN VV       VV EEEEEEEE NN    NN TTTTTTTT   OOOOO   RRRRRRR   YY     YY ");
        System.out.println("     GG       RR    RR  OO   OO  CC      EE       RR    RR  YY   YY           II    NNN   NN  VV     VV  EE       NNN   NN    TT     OO   OO  RR    RR   YY   YY  ");
        System.out.println("     GG GGGG  RRRRRRR  OO     OO CC      EEEEEE   RRRRRRR     Y YY            II    NN N  NN   VV   VV   EEEEEEE  NN N  NN    TT    OO     OO RRRRRRR      Y YY   ");
        System.out.println("     GG   GG  RR  RR    OO   OO  CC      EE       RR  RR      YYY             II    NN  N NN    VV VV    EE       NN  N NN    TT     OO   OO  RR  RR       YYY    ");
        System.out.println("     GGGGGGG  RR   RRR   OOOOO    CCCCCC EEEEEEEE RR   RRR   YYY           IIIIIIII NN   NNN     VVV     EEEEEEEE NN   NNN    TT      OOOOO   RR   RRR    YYY     \n\n");
        

        System.out.println("   MM    MM    AAA    NN    NN    AAA    GGGGGG  EEEEEEEE MM    MM  EEEEEEEE NN    NN TTTTTTTT         SSSSSS YY     YY   SSSSSS  TTTTTTTT EEEEEEEE MM    MM ");
        System.out.println("   MMM  MMM   AA AA   NNN   NN   AA AA   GG      EE       MMM  MMM  EE       NNN   NN    TT          SS        YY   YY  SS           TT    EE       MMM  MMM ");
        System.out.println("   MM MM MM  AAAAAAA  NN N  NN  AAAAAAA  GG GGGG EEEEEEE  MM MM MM  EEEEEEE  NN N  NN    TT           SSSSSSS    Y YY    SSSSSSS     TT    EEEEEEE  MM MM MM ");
        System.out.println("   MM    MM AA     AA NN  N NN AA     AA GG   GG EE       MM    MM  EE       NN  N NN    TT                 SS   YYY           SS    TT    EE       MM    MM ");
        System.out.println("   MM    MM AA     AA NN   NNN AA     AA GGGGGGG EEEEEEEE MM    MM  EEEEEEEE NN   NNN    TT           SSSSSSS   YYY      SSSSSSS     TT    EEEEEEEE MM    MM ");
        
        

        System.out.println("\n\n\n\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t\t\t\t|                     ADMINISTRATOR MENU                    |");
        System.out.println("\t\t\t\t\t- ......................................................... -");
        System.out.println("\t\t\t\t\t|             1. USER                                       |");
        System.out.println("\t\t\t\t\t|             2. REPORT                                     |");
        System.out.println("\t\t\t\t\t|             3. LOGOUT                                     |");
        System.out.println("\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        
        int option;
        boolean l = false;

        do {
            System.out.print("\t\t\t\t\tChoose an option: ");
            try {
                option = scan.nextInt();
                scan.nextLine();

                if (option >= 1 && option <= 3) {
                    l = true;

                    switch (option) {
                        case 1:
                            userSectionMenu(scanner);
                            break;
                        case 2:
                            Report.generateReport();
                            break;
                        case 3:
                            UserMain.handleAdminSection(scanner);
                            break;
                        default:
                            l = false;
                    }
                    
                } else {
                    System.out.println("\t\t\t\t\t\t\t---  Invalid choice!!! Please enter (1-3)... \n\n");
                }

            } catch (InputMismatchException e) {
                 scan.next(); 
                System.out.println("\t\t\t\t\t\t\t---  Invalid input. Please enter a number...\n\n");
 

            }

        } while (!l);
        
        
        
        
    }


    
    
    // Post-login admin menu
    public static void userSectionMenu(Scanner scanner) {
        
        Home.clearScreen();
        
        System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                   USER MANAGEMENT               |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. ADD User                               |");
        System.out.println("\t\t|       2. DELETE User                            |");
        System.out.println("\t\t|       3. Back                                   |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("\t\tChoose an option : ");
        
        boolean l = true;
        int choice;
        while(l){
        
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStaff(scanner);
                        break;
                    case 2:
                        deleteUser(scanner);
                        break;
                    case 3:
                        adminMenu(scanner);
                        break;
                    default:
                        System.out.println("\t\t    -- Invalid option. Please try again.");
                        System.out.print("\n\t\tChoose an option: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\t    -- Invalid option. Please try again.");
                scanner.nextLine(); // Clear the invalid input
                System.out.print("\n\t\tChoose an option: ");
            }

        }
    }
    
    
    
    
    
    // Register a new admin and save details
    public static void registerAdmin() {
        
        Scanner scanner = new Scanner(System.in);
        
        Home.clearScreen();
        
        boolean validAdminId = false;
        String adminId ="";
        String adminPassword = "";
        String adminName = "";
        
        System.out.println("\n\n\t\t=============================================================================");
        System.out.println("\t\t|                         Administrator Registration                        |");
        System.out.println("\t\t=============================================================================");
        System.out.println("\t\t|                                                                           |");
        System.out.print("\t\t|  1.Enter Admin ID (Eg: AD123)                     : ");

        
        while (!validAdminId) {
            adminId = scanner.next();

            // Validation for admin ID (id must start with "AD" followed by exactly 3 digits)
            if (adminId.matches("^AD\\d{3}$")) {
                validAdminId = true; // Admin ID is valid
            } else {
                
                System.out.println("\t\t|         -- Invalid Admin ID! Please enter again...                        |");
                System.out.println("\t\t|                                                                           |");
                System.out.print  ("\t\t|   Re-enter Admin ID (Eg: AD123)                   : ");
               
            }
        }
        
    
    
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        System.out.print("\t\t|  2.Enter Password                                 : ");
        adminPassword = scanner.next();
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        
        // Consume the leftover newline
        scanner.nextLine(); 
    
        
        System.out.print("\t\t|  3.Enter Name                                     : ");
        adminName = scanner.nextLine();
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        System.out.print("\t\t|  4.Enter Contact Number (Eg: 0123456789)          :  ");

        String adminPhone;
        
        while (true) {
            adminPhone = scanner.nextLine();
            // Phone Num must be 10 digits
            if (adminPhone.matches("\\d{10}")) {
                break; // Exit the loop if valid
            } else {
                System.out.println("\t\t|         -- Invalid phone number! Please enter a valid phone number! --    |");
                System.out.println("\t\t|                                                                           |");
                System.out.print  ("\t\t|    Re-enter Contact Number (Eg: 0123456789)       : ");
            }
        }
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");

        System.out.print("\t\t|  5.Enter Email (Eg: abc@gmail.com)                : ");
        String adminEmail;
        // Validate the email to make sure it contains '@gmail.com'
        while (true) {
            adminEmail = scanner.nextLine();

            // Check if the email contains '@gmail.com'
            if (!adminEmail.matches("^[\\w.%+-]+@gmail\\.com$")) {
                System.out.println("\t\t|         -- Invalid email! Please enter a valid email! --                  |");
                System.out.println("\t\t|                                                                           |");
                System.out.print("\n\t\t|   Re-enter Email (Eg: abc@gmail.com)              : ");
            } else {
                break;  // Exit the loop if the condition is met
            }
        }
                            
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t-----------------------------------------------------------------------------");
                            
        Administrator newAdmin = new Administrator(adminId, adminPassword, adminName, adminPhone, adminEmail);
        saveAdminDetails(newAdmin); // Save admin details to file
        
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            bw.write(newAdmin.toFileFormat());
            bw.newLine();
            System.out.println("\n\t\tUser registered successfully.");
        } catch (IOException e) {
            System.out.println("\n\t\tAn error occurred while adding user: " + e.getMessage());
        }
        
        adminLogin(scanner);
 
    }
    
    
    
    //Add staff function
    public static void addStaff(Scanner scanner) {
        
        Home.clearScreen();
        
        boolean validStaffId = false;
        String staffId = "";
        
        
        System.out.println("\n\n\t\t=============================================================================");
        System.out.println("\t\t|                                 ADD STAFF                                 |");
        System.out.println("\t\t=============================================================================");
        System.out.println("\t\t|                                                                           |");
        System.out.print("\t\t|  1.Enter Staff ID (Eg: ST123)                     : ");

        
        while (!validStaffId) {
             staffId = scanner.nextLine();

            // Validation for staff ID (id must start with "ST" followed by exactly 3 digits)
            if (staffId.matches("^ST\\d{3}$")) {
                validStaffId = true; // Staff ID is valid
            } else {
                System.out.println("\t\t|         -- Invalid Staff ID! Please enter again...                        |");
                System.out.println("\t\t|                                                                           |");
                System.out.print  ("\t\t|   Re-enter Staff ID (Eg: ST123)                   : ");
            }
        }
        
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        System.out.print("\t\t|  2.Enter Password                                 : ");
        String staffPassword = scanner.nextLine();
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        
        
        
        System.out.print("\t\t|  3.Enter Name                                     : ");
        String staffName = scanner.nextLine();
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        
        
        
        
        System.out.print("\t\t|  4.Enter Contact Number (Eg: 0123456789)          :  ");

        String staffPhone;
        
        while (true) {
            staffPhone = scanner.nextLine();
            // Phone Num must be 10 digits
            if (staffPhone.matches("\\d{10}")) {
                break; // Exit the loop if valid
            } else {
                System.out.println("\t\t|         -- Invalid phone number! Please enter a valid phone number! --    |");
                System.out.println("\t\t|                                                                           |");
                System.out.print  ("\t\t|    Re-enter Contact Number (Eg: 0123456789)       : ");
            }
        }
        
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");

        System.out.print("\t\t|  5.Enter Email (Eg: abc@gmail.com)                : ");
        String staffEmail;
        // Validate the email to make sure it contains '@gmail.com'
        while (true) {
            staffEmail = scanner.nextLine();

            // Check if the email contains '@gmail.com'
            if (!staffEmail.matches("^[\\w.%+-]+@gmail\\.com$")) {
                System.out.println("\t\t|         -- Invalid email! Please enter a valid email! --                  |");
                System.out.println("\t\t|                                                                           |");
                System.out.print("\n\t\t|   Re-enter Email (Eg: abc@gmail.com)              : ");
            } else {
                break;  // Exit the loop if the condition is met
            }
        }
                            
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t|                                                                           |");
        System.out.println("\t\t-----------------------------------------------------------------------------");
        
        
        
        User newUser = new User(staffId, staffPassword, staffName, staffPhone, staffEmail);
        addU(newUser);
    }
    
    

    // Store staff details to file
    public static void addU(User user) {
        
        
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            bw.write(user.toFileFormat());
            bw.newLine();
            System.out.println("\n\t\tUser registered successfully.");
        } catch (IOException e) {
            System.out.println("\n\t\tAn error occurred while adding user: " + e.getMessage());
        }
        
        BackAdminMenu();
    }
    
    
    
    
    // Delete user function
    public static void deleteUser(Scanner scanner) {
        
        Home.clearScreen();
         
        listUsers();
        
        System.out.println("\n\n\t\tDELETE USER");
        System.out.println("\t\t--------------------------");
        System.out.print("\t\t Enter User ID to delete (Eg: ST123) : ");
        String userIdToDelete = scanner.nextLine();
        deleteU(userIdToDelete);
    }
    
    
    
    
    // Delete user detail for file
    public static void deleteU(String userId) {
        File originalFile = new File(USER_FILE_PATH);
        File tempFile = new File("tempUser.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean userDeleted = false;

            while ((line = br.readLine()) != null) {
                if (!line.startsWith(userId + "|")) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    userDeleted = true;
                }
            }

            if (userDeleted) {
                bw.flush();

                // Check if the original file is actually there
                if (originalFile.exists()) {
                    System.out.println("\t\t\t   -- Attempting to delete the original file...");
                    if (!originalFile.delete()) {
                        System.out.println("\t\t\t   -- Could not delete the original file.");
                        BackAdminMenu();
                    }
                }

                // Rename the temporary file
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("\t\t\t   -- Could not rename the temporary file.");
                } else {
                    System.out.println("\t\t\t   -- User deleted successfully.");
                }
            } else {
                System.out.println("\t\t\t   -- User not found.");
            }
        } catch (IOException e) {
            System.out.println("\t\t\t   -- An error occurred while deleting user: " + e.getMessage());
        }
        
        
        listUsers();
        BackAdminMenu();
    }
    
    
    
    private static void BackAdminMenu(){
        char choiceY;
        boolean validChoice;
        System.out.println("\n\n\t\t  Back to Administrator Menu?(Y)");

        do {
            System.out.print("\t\t  : ");
            

            try {
                choiceY = scan.next().toUpperCase().charAt(0);
                

                if (choiceY == 'Y') {
                    validChoice = true;
                    scan.nextLine();
                    Home.clearScreen();
                    adminMenu(scan);
                } else {
                    validChoice = false;
                    System.out.print("\t\t     ---  Invalid choice!!! Please enter (Y)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoice = false;
                System.out.print("\t\t     ---  Invalid choice!!! Please enter (Y)...\n\n");
            }
            
        } while (!validChoice);
    }

    // Save admin details to admin.txt
    private static void saveAdminDetails(Administrator admin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE_PATH, true))) {
            bw.write(admin.toFileFormat());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while saving admin details: " + e.getMessage());
        }
    }

    

    // Find admin id
    private static boolean isValidAdmin(String adminId, String adminPassword) {
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5 && parts[0].equals(adminId) && parts[1].equals(adminPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t -- An error occurred while reading the file: " + e.getMessage());
        }
        return false;
    }

    
    //Display a list of staff for delete staff function
    public static void listUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            System.out.println("\n\n\t\t+-----------------+----------------------+-----------------+---------------------------+");
            System.out.printf("\t\t| %-15s | %-20s | %-15s | %-25s |%n", "User ID", "Name", "Phone", "Email");
            System.out.println("\t\t+-----------------+----------------------+-----------------+---------------------------+");

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    System.out.printf("\t\t| %-15s | %-20s | %-15s | %-25s |\n", parts[0], parts[2], parts[3], parts[4]);
                }
            }

            System.out.println("\t\t+-----------------+----------------------+-----------------+---------------------------+");
        } catch (IOException e) {
            System.out.println("\t\t\t -- An error occurred while listing users: " + e.getMessage());
        }
    }
}