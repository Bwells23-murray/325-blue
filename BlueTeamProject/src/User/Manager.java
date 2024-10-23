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
    public void createEmployee(String empID, String fName, String lName, String email, String uName, String pass, Job[] jobs, Skill[] skills) {
        writeToDatabase(empID, fName, lName, email, uName, pass, jobs, skills);
    }

    public void editEmployee(String id) {
    }

    public void deleteEmployee(String id) {
    }

    public String getEmployee(String id) {

        String output = "";

        try {
            Scanner scn = new Scanner(new File("325-blue/BlueTeamProject/src/employees.csv"));
            scn.useDelimiter(","); // Adjust according to your CSV formatting

            while (scn.hasNext()) {
                String currentValue = scn.next().trim(); // Read the next value and trim whitespace
                if (currentValue.contains(id)) {
                    String[] splitLine = scn.nextLine().split(",");
                    output += currentValue;
                    for (int i = 0; i < splitLine.length; i++) {
                        if (i != splitLine.length - 1) {
                            output += splitLine[i] + ",";
                        } else {
                            output += splitLine[i];
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
