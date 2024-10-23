package Job;

import java.io.IOException;

public class ContractJob extends Job {

    private double jobPayement;

    public ContractJob(String name, String pos, String start, String end, double pay) {
        // calls super constructor for four strings
        super(name, pos, "contract", start, end);
        jobPayement = pay;
    }

    public ContractJob(String name, String pos, String start, double pay) {
        // calls super constructor for three strings (for ongoing jobs)
        super(name, pos, "contract", start);
        jobPayement = pay;

    }

    public boolean renewContract() throws IOException { // This method is intended to be used by a manager user. If they
                                                        // recieve a high
        // enough score (average of 8 or higher), then it will return true.
        int[] responses = new int[2];
        responses[0] = calculatePerformance();
        responses[1] = calcJobSatisfaction();
        if (getArrayAverage(responses) >= 8)
            return true;
        return false;
        // (not realistic I know, but it's more of an abstract representation of the
        // concept)
    }

    // Accessor and mutator for payment
    public double getPayment() {
        return jobPayement;
    }

    public void setPayment(double payment) {
        jobPayement = payment;
    }

    public boolean determineJustCompensation(double hoursWorked) {
        // Determines if you were payed a fair amount based on hours worked and the
        // payement recieved
        // Returns true if you were payed above Kentucky's minimum wage
        return ((jobPayement / hoursWorked) >= (hoursWorked * 7.25));
    }

    public void testAll() throws IOException {
        // Runs each method in both the super class (save some methods called by other
        // methods)
        // and each method from the subclass
        displayJobDetails();
        System.out.println("Contract renewal due: " + renewContract());
        System.out.println("Just Compensation: " + determineJustCompensation(15));
        assessJobSatisfaction();
        System.out.println("Has been at job for " + calculateJobDuration() + " months.");
    }
}
