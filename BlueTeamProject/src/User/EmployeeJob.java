public class EmployeeJob
 {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private double salary;

    // Constructor with validation
    public EmployeeJob(String title, String description, String startDate, String endDate, double salary) 
    {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    // Getters and Setters with validation

    public String getTitle()
     {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getSalary() {
        return salary;
    }

    // Method to display job information
    public String viewJobInfo() 
    {
        return "Title: " + title + "\n" +
               "Description: " + description + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
               "Salary: $" + salary;
    }
}
