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

public class ConcreteUserDriver extends UserDriver {

    @Override
    public User login(String userId, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5 && parts[0].equals(userId) && parts[1].equals(password)) {
                    return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("\t\t\t -- An error occurred while reading the file: " + e.getMessage());
        }
        System.out.println("\t\t\t -- Login failed! Incorrect user ID or password.");
        return null;
    }

@Override
public void updateProfile(User user) {
    File tempFile = new File("temp_user.txt");
    try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = br.readLine()) != null) {
            if (!line.startsWith(user.getUserId() + "|")) {
                bw.write(line);
                bw.newLine();
            } else {
                bw.write(user.toFileFormat());
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while updating user profile: " + e.getMessage());
    }

    // Attempt to replace old file with the updated file
    File originalFile = new File(USER_FILE_PATH);
    if (!originalFile.delete()) {
        System.out.println("Could not delete the original user file.");
    }
    if (!tempFile.renameTo(originalFile)) {
        System.out.println("Could not rename the temporary file. Check if the original file is still open or if you have permission issues.");
    } else {
        System.out.println(" ");
    }
}

}