package User;

import java.util.Scanner;
import Job.*;
import Skill.*;
import java.io.*;

public class Manager extends User {

    Employee[] employees = new Employee[20];

    public void createEmployee(String empID, String fName, String lName) {
        writeToDatabase(empID, fName, lName);
    }

    public void createEmployee(String empID, String fName, String lName, String email, String uName, String pass,
            Job[] jobs, Skill[] skills) {
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
        if ((!inDatabase(id)) || (csvIndex > 7))
            throw new RuntimeException("Invalid input");
        File csvFile = new File("325-blue\\BlueTeamProject\\src\\employees.csv");
        File tempFile = new File(csvFile.getAbsolutePath() + ".tmp");
        BufferedReader buffRead = new BufferedReader(new FileReader(csvFile));
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(tempFile));

        String line;
        try {
            while ((line = buffRead.readLine()) != null) {
                if (line.contains(id)) {
                    String[] lineArr = line.split(",");
                    lineArr[csvIndex - 1] = newValue;
                    line = String.join(",", lineArr);
                }
                buffWrite.write(line);
                buffWrite.newLine();
            }
            if (csvFile.delete()) {
                tempFile.renameTo(csvFile);
            } else {
                throw new IOException("Could not delete original file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            buffRead.close();
            buffWrite.close();
        }
    }

    public void deleteEmployee(String id) throws IOException {
        // Verifies that an Employee with the given ID is in the database
        // Copies database file line by line, unless the line contains the given ID
        if (!inDatabase(id))
            throw new RuntimeException("Given ID not in database");

        File initFile = new File("325-blue\\BlueTeamProject\\src\\employees.csv");
        File tempFile = new File(initFile.getAbsolutePath() + ".tmp");
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
            Scanner scn = new Scanner(new File("325-blue/BlueTeamProject/src/employees.csv"));
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
