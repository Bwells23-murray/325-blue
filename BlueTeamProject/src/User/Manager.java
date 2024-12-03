package User;


import java.util.Arrays;
import Skill.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Manager extends User {

    Employee[] employees = new Employee[20];
    protected File database = new File("325-blue\\BlueTeamProject\\output\\employees.csv");
    
    public void testFile(){
        System.out.println(database.getAbsolutePath());
        System.out.println(database.getParentFile());
        System.out.println(database);
    }

    // Create a new employee and write to the database
    public void createEmployee(String empID, String fName, String lName) throws IOException {

        if (inDatabase(empID)) {
            throw new RuntimeException("Employee ID already exists in the database: " + empID);
        }

        writeToDatabase(empID, fName, lName);
    }

    public void createEmployee(String empID, String fName, String lName, String email, String uName, String pass,
            EmployeeJob[] jobs, Skill[] skills) throws IOException {
        writeToDatabase(empID, fName, lName, email, uName, pass, jobs, skills);

        String employeeData = empID + "," + fName + "," + lName + ",,,,,";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(database, true))) {
            writer.write(employeeData);
            writer.newLine();
            System.out.println("Employee created: " + employeeData);
        }
    }

    // Edit an existing employee
    public void editEmployee(String id, String newValue, int csvIndex) throws IOException {
        if (!inDatabase(id)) {
            throw new RuntimeException("Employee ID not found in the database: " + id);
        }
        if (csvIndex < 1 || csvIndex > 7) {
            throw new IllegalArgumentException("Invalid index for editing. Index must be between 1 and 7.");
        }


        // Define a temporary file with a unique name
        File originalFile = database;  // Path to the original file
        File tempFile = new File(originalFile.getParent(), "tempFile.csv");  // Temp file in the same directory
        System.out.println("Temporary file created: " + tempFile.getAbsolutePath());
        Boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(database));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(id)) {
                    fields[csvIndex - 1] = newValue;
                    updated = true;
                    System.out.println("Updated line: " + Arrays.toString(fields));
                }
                writer.write(String.join(",", fields));
                writer.newLine();
            }
        }

        if (!updated) {
            System.out.println("No matching ID found for update.");
        } else {
            replaceOriginalFile(tempFile);
            System.out.println("Employee record updated successfully.");
        }
    }

    // Delete an employee from the database
    public void deleteEmployee(String id) throws IOException {
        if (!inDatabase(id)) {
            throw new RuntimeException("Employee ID not found in the database: " + id);
        }

        System.out.println(database.getParent());
        File tempFile = new File(database.getParent(), "tempFile.csv");
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(database));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(id + ",")) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    deleted = true;
                    System.out.println("Deleted line: " + line);
                }
            }
        }

        if (!deleted) {
            System.out.println("No matching ID found for deletion.");
        } else {
            replaceOriginalFile(tempFile);
            System.out.println("Employee record deleted successfully.");
        }
    }



    public boolean inDatabase(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(id + ",")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Added this return statement to handle when no ID is found
    }
    

    // Helper method to replace the original file with a temp file
    private void replaceOriginalFile(File tempFile) throws IOException {
        if (database.delete() && tempFile.renameTo(database)) {
            System.out.println("File updated successfully.");
        } else {
            throw new IOException("Failed to update the original file.");
        }
    }

    // Get employee information
    public String[] getEmployee(String id) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(id + ",")) {
                    return line.split(",");
                }
            }
        }
        throw new RuntimeException("Employee ID not found: " + id);
    }
}
