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
import java.io.*;

public class UserMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        signInPage();
    }

    // Sign In Page
    public static void signInPage() {
        System.out.println("     GGGGGG   RRRRRRR    OOOOO    CCCCCC EEEEEEEE RRRRRRR  YY     YY       IIIIIIII NN    NN VV       VV EEEEEEEE NN    NN TTTTTTTT   OOOOO   RRRRRRR   YY     YY ");
        System.out.println("     GG       RR    RR  OO   OO  CC      EE       RR    RR  YY   YY           II    NNN   NN  VV     VV  EE       NNN   NN    TT     OO   OO  RR    RR   YY   YY  ");
        System.out.println("     GG GGGG  RRRRRRR  OO     OO CC      EEEEEE   RRRRRRR     Y YY            II    NN N  NN   VV   VV   EEEEEEE  NN N  NN    TT    OO     OO RRRRRRR      Y YY   ");
        System.out.println("     GG   GG  RR  RR    OO   OO  CC      EE       RR  RR      YYY             II    NN  N NN    VV VV    EE       NN  N NN    TT     OO   OO  RR  RR       YYY    ");
        System.out.println("     GGGGGGG  RR   RRR   OOOOO    CCCCCC EEEEEEEE RR   RRR   YYY           IIIIIIII NN   NNN     VVV     EEEEEEEE NN   NNN    TT      OOOOO   RR   RRR    YYY     \n\n");

        System.out.println("   MM    MM    AAA    NN    NN    AAA    GGGGGG  EEEEEEEE MM    MM  EEEEEEEE NN    NN TTTTTTTT         SSSSSS YY     YY   SSSSSS  TTTTTTTT EEEEEEEE MM    MM ");
        System.out.println("   MMM  MMM   AA AA   NNN   NN   AA AA   GG      EE       MMM  MMM  EE       NNN   NN    TT          SS        YY   YY  SS           TT    EE       MMM  MMM ");
        System.out.println("   MM MM MM  AAAAAAA  NN N  NN  AAAAAAA  GG GGGG EEEEEEE  MM MM MM  EEEEEEE  NN N  NN    TT           SSSSSSS    Y YY    SSSSSSS     TT    EEEEEEE  MM MM MM ");
        System.out.println("   MM    MM AA     AA NN  N NN AA     AA GG   GG EE       MM    MM  EE       NN  N NN    TT                 SS   YYY           SS    TT    EE       MM    MM ");
        System.out.println("   MM    MM AA     AA NN   NNN AA     AA GGGGGGG EEEEEEEE MM    MM  EEEEEEEE NN   NNN    TT           SSSSSSS   YYY      SSSSSSS     TT    EEEEEEEE MM    MM ");

        boolean exit = false;

        System.out.println("\n\n\n\t\t\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\t\t\t|   User                                     |");
        System.out.println("\t\t\t\t\t\t----------------------------------------------");
        System.out.println("\t\t\t\t\t\t|                1. Admin                    |");
        System.out.println("\t\t\t\t\t\t|                2. Staff                    |");
        System.out.println("\t\t\t\t\t\t|                3. Exit                     |");
        System.out.println("\t\t\t\t\t\t==============================================");
        System.out.print("\t\t\t\t\t\tChoose an option: ");

        while (!exit) {

            int option = getUserInput(3);

            switch (option) {
                case 1:
                    handleAdminSection(scanner); // Admin Section
                    break;
                case 2:
                    Staff.staffSection(scanner); // Use Staff class for staff section
                    break;
                case 3:
                    exit = true;
                    System.out.println("\t\t\t\t\t\t\t -- Exiting the application.");
                    break;
            }
        }

        scanner.close();
    }



    // Admin section handler
    public static void handleAdminSection(Scanner scanner) {
        Administrator admin = new Administrator();
        boolean v = false;

        admin.adminSectionMenu(); // Display initial menu (Register, Login, Exit)

        while (!v) {

            int adminOption = getUserInput(3);

            switch (adminOption) {
                case 1:
                    admin.registerAdmin(); // Register a new admin
                    break;
                case 2:
                    // Handle login and post-login admin functions (Add/Delete user)
                    admin.adminLogin(scanner);
                    break;
                case 3:
                    signInPage();
                    break;

            }
        }

    }
    
    
    //Method to get a valid user choice (For numerical values)
    public static int getUserInput(int maxChoice) {
        boolean validChoice = false;
        int choice = 0;

        do {
            
            try {
                choice = scanner.nextInt();


                if (choice >= 1 && choice <= maxChoice) {
                    validChoice = true;
                } else {
           
                    System.out.println("\t\t\t\t\t\t\t -- Invalid choice!!! Please enter (1-" + maxChoice + ")... \n\n");
                    System.out.print("\t\t\t\t\t\tChoose an option: ");
                }
            } catch (InputMismatchException e) {
                scanner.next(); // consume the invalid input
                System.out.println("\t\t\t\t\t\t\t -- Invalid choice!!! Please enter (1-" + maxChoice + ")...\n\n");
                System.out.print("\t\t\t\t\t\tChoose an option: ");
            }
        } while (!validChoice);

        return choice;
    }
}
