package Job;

import java.io.IOException;

public class FullTimeJob extends Job {

    private double salary;

    public FullTimeJob(String name, String pos, String start, String end, double salary) {
        // Calls super constructor for four strings
        super(name, pos, "fullTime", start, end);
        this.salary = salary;
    }

    public FullTimeJob(String name, String pos, String start) {
        // Calls super constructor for three strings (ongoing jobs)
        super(name, pos, "fullTime", start);
    }

    public void assessJobLoyalty() throws IOException {
        // A specific survey method that assesses how happy an employee is in their
        // position and, if they are willing
        // to disclose it, if they are lookin for other employement opportunities
        String[] answers = new String[3];
        answers[0] = String.valueOf(askScaleQuestion("how happy are you with your current work environment?"));
        answers[1] = askOpenQuestion(
                "Is there anything that needs immediate attention in order to improve your work environment?");
        answers[2] = String.valueOf(askScaleQuestion("how likely are you to search elsewhere for employment?"));
        writeResponses(answers, "loyalty survey");
    }

    // Accessor and mutator for salary
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int findRaises() {
        // Assuming a salary increase every year, this returns the expected number of
        // raises
        return (int) calculateJobDuration() / 12;
    }

    public void testAll() throws IOException {
        // Runs each method in both the super class (save some methods called by other
        // methods)
        // and each method from the subclass
        displayJobDetails();
        System.out.println("Salary: " + getSalary());
        System.out.println("Raises: " + findRaises());
        assessJobLoyalty();
        assessJobSatisfaction();
        System.out.println("Has been at job for " + calculateJobDuration() + " months.");
        System.out.println("Performance survey average: " + calculatePerformance());
        System.out.println("Satisfaction survey average: " + calcJobSatisfaction());
    }
}