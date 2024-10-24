package User;

import java.util.Scanner;
import java.io.*;
import Job.*;
import Skill.*;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String emailAddress;
    protected String employeeID;

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
        int operator = getEncryptionOperator(employeeID); // Uses the employee's ID to encrypt the
                                                                               // password
        String output = "";
        int currentCharacterValue;
        for (int i = 0; i < password.length(); i++) {
            currentCharacterValue = password.charAt(i);
            output += (char)(currentCharacterValue * operator);
        }
        return output;
    }

    private String decryptPassword(String password) {
        int operator = getEncryptionOperator(employeeID); // Uses the employee's ID to encrypt the
                                                                               // password
        String output = "";
        int currentCharacterValue;
        for (int i = 0; i < password.length(); i++) {
            currentCharacterValue = password.charAt(i);
            output += (char)(currentCharacterValue / operator);
        }
        return output;
    }

    private int getEncryptionOperator(String key){
        if(key != null)
            return (int)((key.charAt(0))) + 3;
        else
            return 5;
    }

    // toDatabase giving only name
    protected void writeToDatabase(String empID, String fName, String lName) {
        if (!inDatabase(empID)) { // If they aren't already in the database, add them to it
            try (BufferedWriter writer = new BufferedWriter(
                    // Creates new FileWriter using the CSV file.
                    new FileWriter("325-blue\\BlueTeamProject\\src\\employees.csv", true))) {
                // Creates new row in CSV file with the parameters recieved by the constructor
                String newRow = "\n" + empID + "," + fName + "," + lName + ",null,null,null,null,null";
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
                String newRow = "\n" + empID + "," + fName + "," + lName + "," + email + "," + uName + "," + encryptPassword(pass)
                        + ",null,null\n";
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
                String newRow = "\n" + empID + "," + fName + "," + lName + "," + email + "," + uName + "," + encryptPassword(pass) + ","
                        + jobs + "," + skills + "\n";
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
        File csv = new File("325-blue\\BlueTeamProject\\src\\employees.csv");
        Scanner scn;
        try {
            scn = new Scanner(csv);
            scn.useDelimiter(","); // Each value will be treated as the scanner's line since each value is
            // seperated by a comma and a space
            

            while (scn.hasNextLine()) { // Checks if there is a next line
                String line = scn.nextLine();
                String[] values = line.split(","); //Splits the entire CSV line into an array of strings of each individual field
                for(String value : values){ //For each of the stringgs in the array,
                    //value = value.trim(); //Remove whitespace,
                    if(value.equals(key)){ //Check if its equal to they key
                        scn.close();
                        return true; //If it is, return true
                    }
                }
            }
            scn.close();
            return false;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

}
