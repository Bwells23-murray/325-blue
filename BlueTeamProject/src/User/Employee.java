package User;

import Skill.*;
import Job.*;
import java.util.ArrayList;


public class Employee extends User {

    private ArrayList<Job> previousJobs = new ArrayList<Job>();
    // For both of these array lists, they will store their values in the CSV file
    // using their toString method
    private ArrayList<Skill> skills = new ArrayList<Skill>();

    public Employee(String empID, String first, String last, String email, String user, String pass) {
        super(empID, first, last, email, user, pass);
        writeToDatabase(empID, first, last, email, user, pass);
    }

    public void answerSurvey(int surveyNumber) {
    }

    public void addPreviousJob(Job job) {
        previousJobs.add(job);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public String toString() {
        return firstName + " " + lastName + " " + employeeID;
    }

}
