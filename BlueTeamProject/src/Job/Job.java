package Job;

import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.io.FileWriter;

public abstract class Job {

    private String companyName, position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobType;

    public Job(String name, String pos, String type, String start, String end) {
        // Expects three regular text strings and two strings representing dates in the
        // MM/DD/YY format
        companyName = name;
        position = pos;
        startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("MMddyyyy"));
        // These two lines recieve a string in a given time format (here using
        // MM/DD/YYYY) and parse them into
        // a LocalDate object.
        endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("MMddyyyy"));
        jobType = type;
    }

    public Job(String name, String pos, String type, String start) {
        // Constructor for a current job, so the four strings represent the job Name,
        // Position, Start Date, and Type
        companyName = name;
        position = pos;
        startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("MMddyyyy"));
        // This line recieve a string in a given time format (here using MM/DD/YYYY) and
        // parses it into a LocalDate object.

        // If no end date is given (i.e job is ongoing), the current date is taken
        endDate = LocalDate.now();

        jobType = type;
    }

    public Job() {
    } // Re-implements default constructor

    public void displayJobDetails() {
        System.out.println("Company: " + companyName + "\nJob Position: " + position + "\nStart Date: "
                + startDate + "\nEnd Date: " + endDate);
    }

    public int calculateJobDuration() {
        // Returns the number of months at the selected job
        Period duration = startDate.until(endDate); // .until returns a period of time stored as years/months/days
        // Turns the period into an double that represents the number of months in the
        // period then returns the double
        return (int) ((duration.getYears() * 12) + (duration.getMonths()) + (Math.round(duration.getDays() / 30.467)));
    }

    public void assessJobSatisfaction() throws IOException {
        // Asks the user questions about the job then saves them as a csv file
        Object[] answers = new Object[4];
        answers[0] = askScaleQuestion("how would you rate your performance during this last sprint?");
        answers[1] = askScaleQuestion("how would you rate your team's performance during this last sprint?");
        answers[2] = askOpenQuestion("List any questions, comments, or concers you have about last sprint.");
        answers[3] = askOpenQuestion("list any questions, comments, or concers you have about last sprint.", 10);
        writeResponses(answers, "satisfaction survey");
    }

    public int calcJobSatisfaction() throws IOException {
        // Asks questions about job satisfaction, then averages the responses and
        // returns the average
        int[] answers = new int[3];
        answers[0] = askScaleQuestion("how happy are you with your work environment?");
        answers[1] = askScaleQuestion("how happy are you with your team?");
        answers[2] = askScaleQuestion("how meaningful is your work to you?");

        writeResponses(answers, position + " satisfaction");
        return getArrayAverage(answers);
    }

    public int calculatePerformance() throws IOException { // This method is intended to be used by a manager
                                                           // user
        int[] responses = new int[4];
        responses[0] = askScaleQuestion("how happy were you with the outcome of the project?");
        responses[1] = askScaleQuestion("how flexible was the worker during there time here?");
        responses[2] = askScaleQuestion("how likely would you be to hire this person again?");
        writeResponses(responses, position + " performance");
        return getArrayAverage(responses);
    }

    public void writeResponses(Object[] arr, String type) {
        String csvOutput = "";
        for (int i = 0; i < arr.length; i++) { // Prints all answers to console
            System.out.println(arr[i]);
            if (i != arr.length - 1) // If it isn't the last answer, concat. it with a comma
                csvOutput += arr[i] + ",";
            else
                csvOutput += arr[i]; // if it is last, just concat. it
        }
        try {
            FileWriter surveyOutput = new FileWriter(position + " " + type + ".csv");
            surveyOutput.write(csvOutput);
            surveyOutput.close();
            System.out.println("File output successful");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void writeResponses(int[] arr, String type) { // Same method but takes int as parameter
        String csvOutput = "";
        for (int i = 0; i < arr.length; i++) { // Prints all answers to console
            System.out.println(arr[i]);
            if (i != arr.length - 1) // If it isn't the last answer, concat. it with a comma
                csvOutput += arr[i] + ",";
            else
                csvOutput += arr[i]; // if it is last, just concat. it
        }
        try {
            FileWriter surveyOutput = new FileWriter(position + " " + type + ".csv");
            surveyOutput.write(csvOutput);
            surveyOutput.close();
            System.out.println("File output successful");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public int getArrayAverage(int[] arr) {
        int average = 0;
        for (int i = 0; i < arr.length; i++) {
            average += arr[i];
        }
        return average / arr.length;
    }

    public int askScaleQuestion(String prompt) throws IOException {
        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);
        // Takes a scanner object and a prompt string to ask a question on a scale from
        // 1-10
        System.out.println("On a scale from 1 to 10, " + prompt);
        int response = Integer.parseInt(scn.nextLine()); // Parses so the new line character causes no problems
        if ((response > 10) || (response < 1)) {
            throw new IOException("Response was out of bounds");
        }
        return response;
    }

    public String askOpenQuestion(String prompt) {
        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);
        // Prompts the user to answer a question using their own words.
        System.out.println(prompt);
        return scn.nextLine();
    }

    public String askOpenQuestion(String prompt, int minimum) throws IOException {
        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);

        // Allows manager to set a minimum number of characters for the response
        System.out.println("In at least " + minimum + " characters, " + prompt);
        String response = scn.nextLine();
        if (response.length() < minimum) {
            throw new IOException("Response didn't meet the minimum amount of characters. Please use " +
                    (minimum - response.length()) + " more characters");
        }
        return response;
    }

    public String toString() { // toString for any misc. needs
        return "[" + companyName + ", " + position + ", " + startDate + ", " + endDate + "]";
    }

    public void testAll() throws IOException {
    } // Creating abstract method to be implemented in subclasses
    // Accessors

    public String getType() {
        return jobType;
    }

    public String getName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getEndDate() {
        return String.valueOf(endDate);
    }

    public String getStartDate() {
        return String.valueOf(startDate);
    }

    // Mutators
    public void setName(String name) {
        companyName = name;
    }

    public void setPosition(String pos) {
        position = pos;
    }

    public void setStarDate(String start) {
        startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("MMddyyyy")); //
    }

    public void setEndDate(String end) {
        endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("MMddyyyy"));
    }

}