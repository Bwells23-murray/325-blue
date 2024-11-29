package User;

import java.io.*;
import java.util.*;

import Skill.*;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String emailAddress;
    protected String employeeID;

    protected String password;  // Add a field for password
    public File FILEPATH = new File("BlueTeamProject\\src\\employees.csv");

    // Constructor with parameters

    public File FILEPATH = new File("325-blue\\BlueTeamProject\\src\\employees.csv");

    public boolean login() {
        
            Scanner scnr = new Scanner(System.in);

            System.out.println("Enter username: ");
            String username = scnr.nextLine();

            System.out.println("Enter password: ");
            String password = encryptPassword(scnr.nextLine().trim());

            // Check if the username and password match the stored credentials
            if (this.username.equals(username) && this.password.equals(password)) {
                System.out.println("Login successful for " + firstName + " " + lastName);
                scnr.close();
                //Set parameters to parameters from database
                return true;
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                scnr.close();
                return false;
            }
        }


    public void signOut() {
    }


    public User(String empID, String fName, String lName, String email, String uName, String pass) {
        if (empID == null || empID.isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        }
        this.employeeID = empID;
        this.firstName = fName != null ? fName : "Unknown";
        this.lastName = lName != null ? lName : "Unknown";
        this.emailAddress = email != null ? email : "NoEmailProvided";
        this.username = uName != null ? uName : "NoUsername";
        this.password = encryptPassword(pass);  // Store only encrypted password
    }

    // Default Constructor
    public User() {}

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

    // Mutators
    public void setFirstName(String name) {
        firstName = name;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        }
        this.employeeID = id;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
        this.emailAddress = email;
    }

    @Override
    public String toString() {
        return (firstName != null ? firstName : "Unknown") + " " +
               (lastName != null ? lastName : "Unknown") + " (" +
               (employeeID != null ? employeeID : "No ID") + ")";
    }


    // Password Encryption
    private String encryptPassword(String password) {
        int operator = getEncryptionOperator(employeeID);
        StringBuilder encrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            encrypted.append((char) (c * operator));

        }
        return encrypted.toString();
    }

    private String decryptPassword(String password) {
        int operator = getEncryptionOperator(employeeID);
        StringBuilder decrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            decrypted.append((char) (c / operator));
        }
        return decrypted.toString();
    }

    private int getEncryptionOperator(String key) {
        return (key != null && !key.isEmpty()) ? (int) key.charAt(0) + 3 : 5;
    }

    // Write User to Database
    protected void writeToDatabase(String empID, String fName, String lName) throws IOException {
        if (!inDatabase(empID)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))) {
                String newRow = empID + "," + fName + "," + lName + ",null,null,null,null,null\n";
                writer.write(newRow);
            }
        } else {
            System.err.println("ID already in Database. Try a different ID or edit the user.");
        }
    }

    protected void writeToDatabase(String empID, String fName, String lName, String email, String uName, String pass) {
        try {
            if (!inDatabase(empID)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))) {
                    String newRow = empID + "," + fName + "," + lName + "," + email + "," + uName + "," +
                                    encryptPassword(pass) + ",null,null\n";
                    writer.write(newRow);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("ID already in Database. Try a different ID or edit the user.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeToDatabase(String empID, String fName, String lName, String email, String uName, String pass, EmployeeJob[] jobs, Skill[] skills) 
    {
        try {
            if (!inDatabase(empID)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))) {
                    String newRow = empID + "," + fName + "," + lName + "," + email + "," + uName + "," +
                                    encryptPassword(pass) + "," + arrayToCSV(jobs) + "," + arrayToCSV(skills) + "\n";
                    writer.write(newRow);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String arrayToCSV(Object[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : array) {
            sb.append(obj.toString()).append(",");
        }
        return sb.toString();
    }

    protected boolean inDatabase(String key) throws IOException {
        try (Scanner scn = new Scanner(FILEPATH)) {
            while (scn.hasNextLine()) {
                String[] values = scn.nextLine().split(",");
                for (String value : values) {
                    if (value.trim().equals(key)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Creating a new one.");
            try {
                FILEPATH.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    protected static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}