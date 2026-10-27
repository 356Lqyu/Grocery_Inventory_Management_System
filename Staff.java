/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Staff extends User {
    private String permission;

    // Constructor
    public Staff(String userId, String userPassword, String userName, String userPhone, String userEmail, String permission) {
        super(userId, userPassword, userName, userPhone, userEmail);
        this.permission = permission;
    }

    // Staff Section
    public static void staffSection(Scanner scanner) {
        boolean staffMenu = true;
        while (staffMenu) {
            Home.clearScreen();

            System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\t\t|                          STAFF SECTION                        |");
            System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\t\t|       1. Login                                                |");
            System.out.println("\t\t|       2. Back                                                 |");
            System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("\t\tChoose an option: ");

            int staffOption = getUserInput(scanner);

            switch (staffOption) {
                case 1:
                    staffLogin(scanner);
                    break;
                case 2:
                    System.out.println("\t\t     -- Exiting staff section.");
                    UserMain.signInPage();
                    staffMenu = false;
                    break;
                default:
                    System.out.println("\t\t     -- Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void staffLogin(Scanner scanner) {
        
        
        Home.clearScreen();
        boolean foundStaff = false;

        System.out.println("\n\n\t\t                         STAFF LOGIN                      ");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

        
        do {
            System.out.print("\n\t\t   1.  Enter User ID (Eg: ST123)    : ");
            String userId = scanner.nextLine();

            System.out.print("\n\t\t   2.  Enter User Password          : ");
            String password = scanner.nextLine();

            // Use ConcreteUserDriver to handle login
            User loggedInUser = new ConcreteUserDriver().login(userId, password);
            if (loggedInUser != null) {
                staffMenu(loggedInUser, scanner);
            }
        } while (!foundStaff);
    }
    
    
    private static void staffMenu(User loggedInUser, Scanner scanner){
        
        System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                     STAFF MENU                  |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. My Account                             |");
        System.out.println("\t\t|       2. Inventory System                       |");
        System.out.println("\t\t|       3. Logout                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("\t\tChoose an option: ");
        
        
        boolean w = true;
        int choice;
        while(w){
        
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        myAccountMenu(loggedInUser , scanner);
                        break;
                    case 2:
                        Home.homeMenu();
                        break;
                    case 3:
                        new ConcreteUserDriver().logout(); // Use ConcreteUserDriver.logout
                        scanner.nextLine(); // Consume newline
                        UserMain.signInPage();
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
    
    

    private static void myAccountMenu(User loggedInUser, Scanner scanner) {
        boolean userMenu = true;
        while (userMenu) {
            
            System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\t\t|                     MY ACCOUNT                  |");
            System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\t\t|       1. View Profile                           |");
            System.out.println("\t\t|       2. Update Profile                         |");
            System.out.println("\t\t|       3. Back                                   |");
            System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("\t\tChoose an option: ");
        

            int userOption = getUserInput(scanner);

            switch (userOption) {
                case 1:
                    System.out.println(new ConcreteUserDriver().viewProfile(loggedInUser));
                    break;
                case 2:
                    updateProfile(loggedInUser, scanner);
                    break;
                case 3:
                    staffMenu(loggedInUser, scanner);
                    break;
                default:
                    System.out.println("\t\t -- Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    
    
    
   
    private static void updateProfile(User loggedInUser, Scanner scanner) {
        System.out.println("\n\t\tWhat would you like to update?");
        
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                   UPDATE PROFILE                |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. Password                               |");
        System.out.println("\t\t|       2. Phone                                  |");
        System.out.println("\t\t|       3. Email                                  |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("\t\tChoose an option: ");

        int updateOption = getUserInput(scanner);

        switch (updateOption) {
            case 1:
                System.out.print("\n\t\tEnter new password: ");
                String newPassword = scanner.nextLine();
                loggedInUser.setUserPassword(newPassword);
                break;
            case 2:
                System.out.print("\n\t\tEnter new phone: ");
                String newPhone = scanner.nextLine();
                loggedInUser.setUserPhone(newPhone);
                break;
            case 3:
                System.out.print("\n\t\tEnter new email: ");
                String newEmail = scanner.nextLine();
                loggedInUser.setUserEmail(newEmail);
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        new ConcreteUserDriver().updateProfile(loggedInUser); // Use ConcreteUserDriver.updateProfile
        System.out.println("\t\t    -- Profile updated successfully.");
    }

    private static int getUserInput(Scanner scanner) {
        int option = -1;
        try {
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            System.out.println("\t\t\t -- Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
        return option;
    }
}