package User;

import Skill.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Employee extends User {

    private ArrayList<EmployeeJob> previousJobs = new ArrayList<EmployeeJob>();
    // For both of these array lists, they will store their values in the CSV file
    // using their toString method
    private ArrayList<Skill> skills = new ArrayList<Skill>();

    public Employee(String empID, String first, String last, String email, String user, String pass) {
        super(empID, first, last, email, user, pass);
        writeToDatabase(empID, first, last, email, user, pass);
    }

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



    public void addPreviousJob(EmployeeJob job) {
        previousJobs.add(job);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }
    public ArrayList<EmployeeJob> getPreviousJobs() {
        return previousJobs;
    }

    
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public String toString() {
        return firstName + " " + lastName + " " + employeeID;
    }

}
