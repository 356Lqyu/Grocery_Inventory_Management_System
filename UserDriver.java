/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */


import java.io.*;
public abstract class UserDriver {
    
    // File path for storing user data
    protected static final String USER_FILE_PATH = "user.txt";

    // Abstract method for login
    public abstract User login(String userId, String password);

    // Abstract method for updating profile
    public abstract void updateProfile(User user);

    // Method to view user profile
    public static String viewProfile(User user) {
        
        System.out.println("\n\n\n\t\t\t\t\tUSER PROFILE");
        String header = String.format("+-----------------+----------------------+-----------------+---------------------------+");
        String format = "| %-15s | %-20s | %-15s | %-25s |";

        return String.join("\n",
            header,
            String.format(format, "User ID", "Name", "Phone", "Email"),
            header,
            String.format(format, user.getUserId(), user.getUserName(), user.getUserPhone(), user.getUserEmail()),
            header
        );
    }

    // Method to logout
    public static void logout() {
        System.out.println("\n\t\t -- Logged out successfully.");
    }
}