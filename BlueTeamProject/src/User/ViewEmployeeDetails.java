package User;

import java.util.List;

public class ViewEmployeeDetails {
    private List<Employee> employees;

    // Constructor to initialize the employee list
    public ViewEmployeeDetails(List<Employee> employees) {
        this.employees = employees;
    }

    // Method to display employee details based on their ID
    public void displayEmployeeDetails(String empID) {
        // Search for the employee using their ID
        Employee foundEmployee = employees.stream()
            .filter(emp -> emp.getID().equals(empID))
            .findFirst()
            .orElse(null);

        if (foundEmployee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Display basic employee details
        System.out.println("Employee ID: " + foundEmployee.getID());
        System.out.println("Name: " + foundEmployee.getFirstName() + " " + foundEmployee.getLastName());
        System.out.println("Email: " + foundEmployee.getEmail());
        System.out.println("Username: " + foundEmployee.getUsername());

        // Display job history
        System.out.println("\nJob History:");
        if (foundEmployee.getJobHistory().isEmpty()) {
            System.out.println("No job history available for this employee.");
        } else {
            for (EmployeeJob job : foundEmployee.getJobHistory()) {
                System.out.println("- Title: " + job.getTitle());
                System.out.println("  Description: " + job.getDescription());
                System.out.println("  Start Date: " + job.getStartDate());
                System.out.println("  End Date: " + job.getEndDate());
                System.out.println("  Salary: $" + job.getSalary());
            }
        }

        // Display skills
        System.out.println("\nSkills:");
        if (foundEmployee.getSkills() == null || foundEmployee.getSkills().isEmpty()) {
            System.out.println("No skills available for this employee.");
        } else {
            for (Employee skill : foundEmployee.getSkills()) {
                System.out.println("- " + skill.getFirstName());
            }
        }
    }
}
