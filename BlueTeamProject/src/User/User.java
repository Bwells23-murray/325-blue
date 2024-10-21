package User;

import java.util.Scanner;
import java.io.*;
import Job.*;
import Skill.*;

public abstract class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String employeeID;

    public void logIn() {
        // Promt user for username or employee ID
        // Prompt user for password
        // Search database with username/employee ID as key
        // Decrypt stored password, check if it matches given password
        // If both are true, log the user in

    }

    public void signOut() {
    }

    public User(String empID, String fName, String lName, String email, String uName, String pass) {
        employeeID = empID;
        firstName = fName;
        lastName = lName;
        emailAddress = email;
        username = uName;
        password = encryptPassword(pass); // User's actual password should never be stored unencrypted
    }

    public User() {
    }
    // Accessors

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return decryptPassword(password);
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String name) {
    }

    public void setLastName(String name) {
    }

    public void setId(String id) {
    }

    public void setEmail(String email) {
    }

    public String toString() {
        return "";
    }

    private String encryptPassword(String password) {
        int operator = Integer.parseInt(String.valueOf(employeeID.charAt(0))); // Uses the employee's ID to encrypt the
                                                                               // password
        String output = null;
        int currentCharacterValue;
        for (int i = 0; i < password.length(); i++) {
            currentCharacterValue = password.charAt(i);
            output += currentCharacterValue + operator;
        }
        return output;
    }

    private String decryptPassword(String password) {
        int operator = Integer.parseInt(String.valueOf(employeeID.charAt(0))); // Uses the employee's ID to encrypt the
                                                                               // password
        String output = null;
        int currentCharacterValue;
        for (int i = 0; i < password.length(); i++) {
            currentCharacterValue = password.charAt(i);
            output += currentCharacterValue - operator;
        }
        return output;
    }

    // toDatabase giving only name
    protected void writeToDatabase(String empID, String fName, String lName) {
        if (!inDatabase(empID)) { // If they aren' already in the database, add them to it
            try (BufferedWriter writer = new BufferedWriter(
                    // Creates new FileWriter using the CSV file.
                    new FileWriter("325-blue\\BlueTeamProject\\src\\employees.csv", true))) {
                // Creates new row in CSV file with the parameters recieved by the constructor
                String newRow = empID + ", " + fName + ", " + lName + "null, null, null, null\n";
                writer.write(newRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // toDatabase giving name and login information
    protected void writeToDatabase(String empID, String fName, String lName,
            String email, String uName, String pass) {
        if (!inDatabase(empID)) { // If they aren' already in the database, add them to it
            try (BufferedWriter writer = new BufferedWriter(
                    // Creates new FileWriter using the CSV file.
                    new FileWriter("325-blue\\BlueTeamProject\\src\\employees.csv", true))) {
                // Creates new row in CSV file with the parameters recieved by the constructor
                String newRow = empID + ", " + fName + ", " + lName + ", " + email + ", " + uName + ", " + pass
                        + ", null, null\n";
                writer.write(newRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // toDatabase giving name, login information, and user logged job history and
    // skills
    protected void writeToDatabase(String empID, String fName, String lName,
            String email, String uName, String pass,
            Job[] jobs, Skill[] skills) {
        if (!inDatabase(empID)) { // If they aren' already in the database, add them to it
            try (BufferedWriter writer = new BufferedWriter(
                    // Creates new FileWriter using the CSV file.
                    new FileWriter("325-blue\\BlueTeamProject\\src\\employees.csv", true))) {
                // Creates new row in CSV file with the parameters recieved by the constructor
                String newRow = empID + ", " + fName + ", " + lName + ", " + email + ", " + uName + ", " + pass + ", "
                        + jobs + ", " + skills + "\n";
                writer.write(newRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static boolean inDatabase(String key) // Looks for any value in the csv file. Could be employee number,
                                                    // username, or email. Returns true if found, false if it doesn't
                                                    // exist
    {
        Scanner scn = new Scanner("325-blue\\BlueTeamProject\\src\\employees.csv");
        scn.useDelimiter(", "); // Each value will be treated as the scanner's line since each value is
                                // seperated by a comma and a space
        boolean found = false;
        String currentValue = scn.next();
        while (scn.hasNext()) { // Checks if there is a next value
            currentValue = scn.next();
            if (currentValue.equals(key)) // Checks if the current value is equal to the key
                found = true;
        }
        scn.close();
        return found;
    }
}
