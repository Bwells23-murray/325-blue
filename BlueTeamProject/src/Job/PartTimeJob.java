package Job;

import java.io.IOException;

public class PartTimeJob extends Job {

    private double hourlyWage;

    public PartTimeJob(String name, String pos, String start, String end, double wage) {
        // Calls super constructor for four strings
        super(name, pos, "partTime", start, end);
        hourlyWage = wage;
    }

    public PartTimeJob(String name, String pos, String start, double wage) {
        // Calls super constructor for three strings
        super(name, pos, "partTime", start);
        hourlyWage = wage;
    }

    public double calculateSalary(int hoursPerWeek) {
        // Calculates average salary based on the hourly wage, weeks at the job, and
        // hours worked per week
        return (hourlyWage * (52 * hoursPerWeek));
    }

    public double getWage() {
        return hourlyWage;
    }

    public void setWage(double wage) {
        hourlyWage = wage;
    }

    public void giveRaise(double amount) {
        hourlyWage += amount;
    }

    public void testAll() throws IOException {
        // Runs each method in both the super class (save some methods called by other
        // methods)
        // and each method from the subclass
        displayJobDetails();
        System.out.println("Calculate Salary: " + calculateSalary(20));
        System.out.println("Wage: " + hourlyWage);
        giveRaise(5);
        System.out.println("Raise of $1: $" + hourlyWage);
        assessJobSatisfaction();
        System.out.println("Has been at job for " + calculateJobDuration() + " months.");
        System.out.println("Performance survey average: " + calculatePerformance());
        System.out.println("Satisfaction survey average: " + calcJobSatisfaction());
    }
}
