package User;

public class EmployeeJob 
{

    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private double salary;

    public EmployeeJob(String title, String description, String startDate, String endDate, double salary) 
    {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    // Getters
    public String getTitle() 
    {
        return title;
    }

    public String getDescription() 
    {
        return description;
    }

    public String getStartDate() 
    {
        return startDate;
    }

    public String getEndDate() 
    {
        return endDate;
    }

    public double getSalary() 
    {
        return salary;
    }

    // Convert job details to CSV format
    public String toCSV() 
    {
        return title + "," + description + "," + startDate + "," + endDate + "," + salary;
    }

    // Display job information
    public String viewJobInfo() 
    {
        return "Title: " + title + "\n" +
               "Description: " + description + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
               "Salary: $" + salary;
    }

    // Convert CSV line to EmployeeJob object
    public static EmployeeJob fromCSV(String line) 
    {
        String[] fields = line.split(",");
        String title = fields[1];
        String description = fields[2];
        String startDate = fields[3];
        String endDate = fields[4];
        double salary = Double.parseDouble(fields[5]);
        return new EmployeeJob(title, description, startDate, endDate, salary);
    }
}
