package User;

import java.util.*;

import Skill.Skill;

public class ViewEmployeeDetails {
    private List<Employee> employees;

    public ViewEmployeeDetails(List<Employee> employees) {
        this.employees = employees;
    }

    public void displayEmployeeDetails(String empID) {
        SearchEmployee searchEmployee = new SearchEmployee(employees);
        List<Employee> results = searchEmployee.searchByNameOrID(empID);

        if (results.isEmpty()) {
            System.out.println("Employee not found.");
            return;
        }
        Employee emp = results.get(0); 
        System.out.println("Employee ID: " + emp.getID());
        System.out.println("Name: " + emp.getFirstName() + " " + emp.getLastName());
        System.out.println("Email: " + emp.getEmail());
        System.out.println("Username: " + emp.getUsername());

        System.out.println("Job History:");
        for (EmployeeJob job : emp.getPreviousJobs()) { 
            System.out.println(job.viewJobInfo());
        }

        // Display skills 
        System.out.println("Skills:");
        for (Skill skill : emp.getSkills()) { 
            System.out.println("- " + skill.getName());
        }
    }
}
