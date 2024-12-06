package GUIs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EvaluationScreen {

    private String managerName;
    private String employeeName;
    private String managerID;
    private String employeeID;
    private LocalDate dateOfEval;

    public EvaluationScreen(String managerName, String employeeName, String managerID, String employeeID) {
        this.managerName = managerName;
        this.employeeName = employeeName;
        this.managerID = managerID;
        this.employeeID = employeeID;
    }

    public EvaluationScreen() {}
    
    public void startEvaluation() throws IOException {

        // Create the evaluation window (frame)
        JFrame evaluationFrame = new JFrame("Evaluation Screen");
        evaluationFrame.setSize(600, 600);
        evaluationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        evaluationFrame.setIconImage(ImageIO.read(new File("BlueTeamProject\\resources\\icon.png")));

        // Set layout and background color to match ManagerScreen
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(173, 216, 230));  // Setting the background color of the panel

        // Date input
        JTextField dateField = new JTextField(10);
        dateField.setText(LocalDate.now().toString());
        dateField.setEditable(false); //Date shouldn't be able to be changed to prevent inconsistencies
        JPanel datePanel = new JPanel();
        datePanel.setBackground(new Color(173, 216, 230));  // Ensuring background color for each panel
        datePanel.add(new JLabel("Date of Evaluation:"));
        datePanel.add(dateField);
        panel.add(datePanel);

        // Questions and text areas
        JTextArea[] questions = new JTextArea[]{
            new JTextArea("Over the last two weeks, did you notice any feelings of positivity or negativity while performing specific job tasks?"),
            new JTextArea("If you could do one task at work all day, which task would you choose and why?"),
            new JTextArea("Are there any tasks you perform in your job that you feel you are really good at, and if so, what are they?"),
            new JTextArea("Are there any tasks in your job you dread having to do, and if so, what about them makes you dread them?"),
            new JTextArea("Are there any tasks in your job you look forward to doing, and if so, what are they and why do you look forward to them?"),
            new JTextArea("Additional comments/suggestions:")
        };
        JTextField[] answerFields = new JTextField[questions.length];

        for (int i = 0; i < questions.length; i++) {
            JPanel questionPanel = new JPanel();
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
            questionPanel.setBackground(new Color(173, 216, 230));  // Setting background color for each question panel
            questionPanel.add(new JLabel(questions[i].getText()));
            answerFields[i] = new JTextField(50);
            questionPanel.add(answerFields[i]);
            panel.add(questionPanel);
        }

        // Submit button
        JButton submitButton = new JButton("Submit Evaluation");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));  // Matching font style
        submitButton.setBackground(new Color(173, 216, 230));  // Optional: Add background color to button for consistency
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect answers and save to file
                StringBuilder answers = new StringBuilder();
                for (int i = 0; i < answerFields.length; i++) {
                    answers.append("Answer ").append(i + 1).append(": ").append(answerFields[i].getText()).append("\n");
                }

                // Parse date
                try {
                    dateOfEval = LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(evaluationFrame, "Invalid date format. Please use yyyy-MM-dd.");
                    return;
                }

                saveToFile(answers.toString());
                JOptionPane.showMessageDialog(evaluationFrame, "Evaluation Submitted and Saved!");
                evaluationFrame.dispose();  // Close the evaluation window
            }
        });
        panel.add(submitButton);

        // Back button to return to UserScreen
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));  // Matching font style
        backButton.setBackground(new Color(173, 216, 230));  // Optional: Add background color to button for consistency
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and return to UserScreen
                evaluationFrame.dispose();
                try {
                    new UserScreen();  // Initialize and display the UserScreen
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(backButton);

        // Set the content pane and make the frame visible
        evaluationFrame.setContentPane(panel);
        evaluationFrame.setLocationRelativeTo(null);
        evaluationFrame.setVisible(true);
    }

    private void saveToFile(String answers) {
        String file = "325-blue\\BlueTeamProject\\output\\sprint_evaluation_" + employeeName + "_" + dateOfEval + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(dateOfEval + "\n");
            writer.write("Manager name: " + managerName + " | ID: " + managerID + "\n");
            writer.write("Employee name: " + employeeName + " | ID: " + employeeID + "\n");
            writer.write("Employee's answers:\n");
            writer.write(answers);

            System.out.println("Save was successful: " + file);
        } catch (IOException e) {
            System.out.println("Error while trying to save the file, try again.");
            e.printStackTrace();
        }
    }
}
