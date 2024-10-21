package User;

import java.util.Scanner;
import java.io.*;

public class Manager extends User {

    private int lastIndex = 0;
    Employee[] employees = new Employee[20];

    public void createEmployee(String empID, String fName, String lName) {
        writeToDatabase(empID, fName, lName);
    }

    public void editEmployee(String id) {
    }

    public void deleteEmployee(String id) {
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

    public Employee getEmpInfo(String id) {
        return null;
    }

}
