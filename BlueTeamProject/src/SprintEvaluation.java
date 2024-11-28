import User.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SprintEvaluation {

    // Properties
    private String managerName;
    private String employeeName;
    private String managerID;
    private String employeeID;
    private String answers;
    private LocalDate dateOfEval;

    //temporary file save we can adjust later if we need to
    public void saveToFile() {
        String file = "sprint_evaluation_" + employeeName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(dateOfEval + "\n");
            writer.write("Manager name: " + managerName + " | ID: " + managerID + "\n");
            writer.write("Employee name: " + employeeName + " | ID: " + employeeID + "\n");
            writer.write("Employee's answers:\n");
            writer.write(answers);
            writer.write("\n");

            System.out.println("Save was successful: " + file);
        } catch (IOException e) {
            System.out.println("Error while trying to save the file, try again.");
            e.printStackTrace();
        }
    }

    // Log in - Moved to user. 
    // public boolean login(User user) {
    //     Scanner scnr = new Scanner(System.in);

    //     System.out.println("Enter username: ");
    //     String username = scnr.nextLine();

    //     System.out.println("Enter password: ");
    //     String password = scnr.nextLine();

    //     // Check if the username and password match the stored credentials
    //     if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
    //         System.out.println("Login successful for " + user.getFirstName() + " " + user.getLastName());
    //         scnr.close();
    //         return true;
    //     } else {
    //         System.out.println("Login failed. Incorrect username or password.");
    //         scnr.close();
    //         return false;
    //     }
    // }

    // Evaluation process
    public void evaluation(User manager, User employee) {
        Scanner scnr = new Scanner(System.in);

        // Perform login for the manager
        System.out.println("Manager Login:");
        if (!(manager.login())) {
            System.out.println("Manager login failed. Cannot proceed with the evaluation.");
            scnr.close();
            return;
        }

        // Perform login for the employee
        System.out.println("Employee Login:");
        if (!(employee.login())) {
            System.out.println("Employee login failed. Cannot proceed with the evaluation.");
            scnr.close();
            return;
        }

        // After both are logged in, retrieve their names and IDs from the objects
        managerName = manager.getFirstName() + " " + manager.getLastName();
        managerID = manager.getID();
        employeeName = employee.getFirstName() + " " + employee.getLastName();
        employeeID = employee.getID();

        // gets the users input for the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Enter today's date (yyyy-MM-dd)");
        String dateString = scnr.nextLine();
        dateOfEval = LocalDate.parse(dateString, formatter);
        System.out.println(dateOfEval);

        //prints manager and employee info
        System.out.println("Manager name: " + managerName + " ID: " + managerID);
        System.out.println("Employee name: " + employeeName + " ID: " + employeeID);

        // Evaluation questions and answers
        System.out.println("Over the last two weeks, did you notice any feelings of positivity or negativity while performing specific job tasks and if so, how would you describe those feelings?");
        answers = "Answer 1: " + scnr.nextLine() + "\n";

        System.out.println("If you could do one task at work all day which task would you choose and why?");
        answers += "Answer 2: " + scnr.nextLine() + "\n";

        System.out.println("Are there any tasks you perform in your job that you feel you are really good at and if so, what are they?");
        answers += "Answer 3: " + scnr.nextLine() + "\n";

        System.out.println("Are there any tasks in your job you dread having to do and if so, what are they and what about them makes you dread them?");
        answers += "Answer 4: " + scnr.nextLine() + "\n";

        System.out.println("Are there any tasks in your job you look forward to doing and if so, what are they and why do you look forward to them?");
        answers += "Answer 5: " + scnr.nextLine() + "\n";

        System.out.println("Additional comments/suggestions: ");
        answers += "Answer 6: " + scnr.nextLine() + "\n";

        //saves to a file
        saveToFile();
        scnr.close();
    }
}
