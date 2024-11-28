package User;

import java.util.Scanner;
import Skill.*;
import java.io.*;

public class Manager extends User {

    Employee[] employees = new Employee[20];

    public void createEmployee(String empID, String fName, String lName) throws IOException {
        writeToDatabase(empID, fName, lName);
    }

    public void createEmployee(String empID, String fName, String lName, String email, String uName, String pass,
            EmployeeJob[] jobs, Skill[] skills) {
        writeToDatabase(empID, fName, lName, email, uName, pass, jobs, skills);
    }

    public void editEmployee(String id, String newValue) throws IOException { // Prompts the user to choose the value
                                                                              // they wish to edit
        if (!inDatabase(id))
            throw new RuntimeException("Given ID not in database");
        Scanner scn = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println(
                    "What would you like to edit? \n1) Employee ID \n2) First Name \n3) Last Name \n4) Email Address \n5,) Username \n6) Password \n7)Job History \n8) Skills");
            int response = Integer.parseInt(scn.nextLine());
            if (response < 1 || response > 8)
                System.out.println("Invalid response, please try again");
            else
                editEmployee(id, newValue, response);
            valid = true;
            scn.close(); // Send information to other method, wrap up current method
        }
    }

    public void editEmployee(String id, String newValue, int csvIndex) throws IOException { // Doesn't prompt the user
                                                                                            // to choose a value to edit
        if ((!inDatabase(id)) || (csvIndex > 7)) {
            System.out.println("Invalid input: ID not in database or CSV index out of bounds.");
            throw new RuntimeException("Invalid input");
        }

        // Define a temporary file with a unique name
        File originalFile = new File("325-blue\\BlueTeamProject\\src\\employees.csv");  // Path to the original file
        File tempFile = new File(originalFile.getParent(), "tempFile.csv");  // Temp file in the same directory
        System.out.println("Temporary file created: " + tempFile.getAbsolutePath());

        // Initialize reader and writer
        BufferedReader buffRead = new BufferedReader(new FileReader(originalFile));
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(tempFile));

        try {
            String line;
            boolean isUpdated = false; // Flag to check if any line was updated

            // Read each line from the original file
            while ((line = buffRead.readLine()) != null) {
                System.out.println("Reading line: " + line);

                // Check if line contains the specified ID
                if (line.contains(id)) {
                    System.out.println("ID found in line. Updating line...");
                    String[] lineArr = line.split(",");
                    lineArr[csvIndex - 1] = newValue; // Update the specified index with the new value
                    line = String.join(",", lineArr); // Re-join the array into a line
                    isUpdated = true; // Mark that we've made an update
                    System.out.println("Updated line: " + line);
                }

                // Write the (updated or unchanged) line to the temporary file
                buffWrite.write(line);
                buffWrite.write(System.lineSeparator());
            }

            if (!isUpdated) {
                System.out.println("No lines were updated; check if the ID and index are correct.");
            } else {
                System.out.println("All lines processed and written to temporary file.");
            }

            buffWrite.flush(); // Ensure all data is written to the temporary file
        } catch (IOException e) {
            System.out.println("IOException occurred while reading/writing: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure resources are closed properly
            try {
                buffRead.close();
                buffWrite.close();
                System.out.println("Resources closed.");
            } catch (IOException e) {
                System.out.println("IOException occurred while closing resources: " + e.getMessage());
            }
        }

        // Delete the original file and rename the temp file to the original filename
        try {
            if (originalFile.delete()) {
                System.out.println("Original file deleted successfully.");
                if (tempFile.renameTo(originalFile)) {
                    System.out.println("Temporary file renamed to original file name successfully.");
                } else {
                    throw new IOException("Could not rename temporary file to original filename.");
                }
            } else {
                throw new IOException("Could not delete the original file.");
            }
        } catch (IOException e) {
            System.out.println("IOException occurred during file deletion/renaming: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String id) throws IOException {
        // Verifies that an Employee with the given ID is in the database
        // Copies database file line by line, unless the line contains the given ID
        if (!inDatabase(id))
            throw new RuntimeException("Given ID not in database");

        File initFile = FILEPATH;
        File tempFile = new File(FILEPATH + ".tmp");
        BufferedReader buffRead = new BufferedReader(new FileReader(initFile));
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(tempFile));

        String line;

        try {
            while ((line = buffRead.readLine()) != null) {
                if (!line.contains(id)) {
                    buffWrite.write(line + "\n");
                    buffWrite.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            buffRead.close();
            buffWrite.close();
            if (!initFile.delete())
                System.out.println("Original file not deleted properly");
            if (!tempFile.renameTo(initFile))
                System.out.println("New file not renamed properly");
        }

    }

    public String getEmployee(String id) { // TODO Return array of employees information

        String output = "";

        try {
            Scanner scn = new Scanner(FILEPATH);
            scn.useDelimiter(","); // Adjust according to your CSV formatting

            while (scn.hasNext()) {
                String currentValue = scn.next().trim(); // Read the next value and trim whitespace
                if (currentValue.contains(id)) {
                    String[] splitLine = scn.nextLine().split(","); // Splits the line up at the commas to an array
                    output += currentValue;
                    for (int i = 0; i < splitLine.length; i++) { // For each item in the array,
                        if (i != splitLine.length - 1) { // If it isn't the last item,
                            output += splitLine[i] + ","; // Concat. it to the output string with a comma
                        } else {
                            output += splitLine[i]; // If it is the last item, concat. it without a comma at the end
                        }
                    }
                    break; // Exit loop after finding the employee
                }
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle file not found exception
        }
        return output;
    }

    public void viewSurveyResults(int surveyNumber) {
    }

    public void createTeam() {
    }

    public void editTeam(Employee[] team) {
    }

    public void deleteTeam(Employee[] team) {
    }

    public void createJobListing(Object job) {
    }

    public void displayEmployee(String id) {
    }

    public void displaySomeEmployees(Employee[] emps) {
    }

    public void saveEmployeeInfo(String id) {
    }
}
