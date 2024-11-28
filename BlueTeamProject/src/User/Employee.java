package User;

import Skill.*;
import java.util.ArrayList;


public class Employee extends User {

    private ArrayList<EmployeeJob> previousJobs = new ArrayList<EmployeeJob>();
    // For both of these array lists, they will store their values in the CSV file
    // using their toString method
    private ArrayList<Skill> skills = new ArrayList<Skill>();

    public Employee(String empID, String first, String last, String email, String user, String pass) {
        super(empID, first, last, email, user, pass);
        writeToDatabase(empID, first, last, email, user, pass);
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
