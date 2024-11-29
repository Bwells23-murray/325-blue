package User;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Employee extends User 
{

    private ArrayList<EmployeeJob> jobHistory = new ArrayList<>();
    private static final String FILEPATH = "employee_jobs.csv"; // Ensure this file exists


    public Employee(String empID, String first, String last, String email, String user, String pass) 
    {

    private ArrayList<EmployeeJob> previousJobs = new ArrayList<EmployeeJob>();
    // For both of these array lists, they will store their values in the CSV file
    // using their toString method
    private ArrayList<Skill> skills = new ArrayList<Skill>();

    public Employee(String empID, String first, String last, String email, String user, String pass) {
    }


    // Add a job to the employee's job history
    public void addJob(EmployeeJob job) 
    {
        jobHistory.add(job);
        saveAllJobsToDatabase();
    }

    // View all jobs for this employee
    public List<EmployeeJob> getJobHistory() 
    {
        return new ArrayList<>(jobHistory); // Return a copy to avoid external modifications
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

    // Edit an existing job
    public void editJob(int index, EmployeeJob updatedJob) 
    {
        if (index >= 0 && index < jobHistory.size()) {
            jobHistory.set(index, updatedJob);
            saveAllJobsToDatabase(); // Save all jobs to the file
        } else {
            System.out.println("Invalid job index.");
        }
    }


    // Load job history from the database
    private void loadJobsFromDatabase() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts[0].equals(employeeID)) { // Check if this line matches the employee ID
                    EmployeeJob job = EmployeeJob.fromCSV(line);
                    jobHistory.add(job);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Starting with an empty job history.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    // Save all jobs to the database for this employee
    private void saveAllJobsToDatabase() {
        List<String> allLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(employeeID)) { // Skip previous entries for this employee
                    allLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the database file.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))) {
            for (String line : allLines) {
                writer.write(line + "\n");
            }

            for (EmployeeJob job : jobHistory) {
                String newRow = employeeID + "," + job.toCSV();
                writer.write(newRow + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return employeeID + "," + firstName + "," + lastName + "," + emailAddress + "," + username;
    }

    public List<Employee> getSkills() {
        // Just put this here becasuse if we need skills we n eed a methods but i wasnt sure ):
        throw new UnsupportedOperationException("Unimplemented method 'getSkills'");
    }
}
