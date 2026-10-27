/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException; 


public class Home {

    public int choice;
    
    
    //Scanner
    public static final Scanner scan = new Scanner(System.in);
    
    
    //Clean Screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    
    //Method to get a valid user choice (For numerical values)
    public static int getValidChoice(int maxChoice) {
        boolean validChoice = false;
        int choice = 0;

        do {
            System.out.print("\t\t\t Enter Choice (1-" + maxChoice + ") : ");
            try {
                choice = scan.nextInt();
                scan.nextLine();

                if (choice >= 1 && choice <= maxChoice) {
                    validChoice = true;
                } else {
                    System.out.println("\t\t\t\t        ---  Invalid choice!!! Please enter (1-" + maxChoice + ")... \n");
                }
            } catch (InputMismatchException e) {
                scan.next(); // consume the invalid input
                System.out.println("\t\t\t\t        ---  Invalid choice!!! Please enter (1-" + maxChoice + ")...\n");
            }
        } while (!validChoice);

        return choice;
    }
    
    
    // Method for modification confirmation
    public static boolean confirmModification(String oldValue, String newValue, String fieldName) {
        System.out.printf("\n\t\t\t Are you sure you want to change the %s from '%s' --> '%s'?\n", fieldName, oldValue, newValue);
        System.out.print("\t\t\t Enter (Y/N)     : ");
        char yesNo;
        do {
            yesNo = scan.next().toUpperCase().charAt(0);
            scan.nextLine();  // Consume newline
            if (yesNo == 'Y') {
                System.out.println("\t\t\t\t\t -- Modification successful! --");
                return true;
            } else if (yesNo == 'N') {
                System.out.println("\t\t\t\t\t -- Modification canceled! --");
                return false;
            } else {
                System.out.print("\t\t\t\t\t -- Invalid choice! \n\n\t\t\t Re-enter (Y/N) : ");
            }
        } while (true);
    }
    
    
    
    public static void homeMenu(){
        
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
        
        

        System.out.println("\n\n\n\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t\t\t\t|                           MAIN MENU                           |");
        System.out.println("\t\t\t\t\t- ............................................................. -");
        System.out.println("\t\t\t\t\t|             1. PRODUCT                                        |");
        System.out.println("\t\t\t\t\t|                                         5. PAYMENT            |");
        System.out.println("\t\t\t\t\t!             2. CATEGORY                                       |");
        System.out.println("\t\t\t\t\t|                                         6. REFUND             |");
        System.out.println("\t\t\t\t\t|             3. SUPPLIER                                       |");
        System.out.println("\t\t\t\t\t|                                         7. LOGOUT             |");
        System.out.println("\t\t\t\t\t|             4. ORDER                                          |");
        System.out.println("\t\t\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        

        boolean validChoiceA = false;
        int choiceA = 0;

        do {
            System.out.print("\t\t\t\t\t Enter Choice (1-8) : ");
            try {
                choiceA = scan.nextInt();
                scan.nextLine();

                if (choiceA >= 1 && choiceA <= 8) {
                    validChoiceA = true;
                } else {
                    System.out.println("\t\t\t\t\t\t        ---  Invalid choice!!! Please enter (1-8)... \n\n");
                }
            } catch (InputMismatchException e) {
                scan.next(); // consume the invalid input
                System.out.println("\t\t\t\t\t\t        ---  Invalid choice!!! Please enter (1-8)...\n\n");
            }
        } while (!validChoiceA);
        

        switch(choiceA){
                
            case 1:
                clearScreen();
                ProductDrive.productMenu();
                break;
            case 2:
                clearScreen();
                CategoryDrive.categoryMenu();
                break;
            case 3:
                clearScreen();
                SupplierDrive.supplierMenu();
                break;
            case 4:
                clearScreen();
                try {
                    OrderDrive.orderMenu();  
                } catch (IOException e) {
                    System.out.println("\n\t\t  -- Error while opening Order Menu! --");
                    e.printStackTrace();
                }
                break;
            case 5:
                clearScreen();
                try {
                    PaymentDrive.paymentMenu(); 
                } catch (IOException e) {
                    System.out.println("\n\t\t  -- Error while opening Order Menu! --");
                    e.printStackTrace();
                }
                break;
            case 6:
                clearScreen();
                try {
                    RefundDrive.refundMenu();  
                } catch (IOException e) {
                    System.out.println("\n\t\t  -- Error while opening Order Menu! --");
                    e.printStackTrace();
                }
                break;
            case 7:
                clearScreen();
                System.out.println("\n\t\t  -- Program is exiting...");
                UserMain.signInPage();
                break;
        }
        
    }

    
}
