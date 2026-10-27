/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class User {
    // Attributes
    private String userId;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userEmail;

    // Constructor
    public User(String userId, String userPassword, String userName, String userPhone, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public void setUserPhone(String phone) {
        this.userPhone = phone;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    // Convert user details to a formatted string for saving to a file
    public String toFileFormat() {
        return String.join("|", userId, userPassword, userName, userPhone, userEmail);
    }

    // Override toString method to provide a meaningful representation
    @Override
    public String toString() {
        return String.format("User ID: %s%nName: %s%nPhone: %s%nEmail: %s", userId, userName, userPhone, userEmail);
    }
}