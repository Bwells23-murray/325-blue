package GUIs;

import User.EmployeeJob;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class HistoryScreen {
    private JFrame frame;
    private JTextArea historyArea;
    private JobHistoryManager jobManager;

    public HistoryScreen(JFrame previousFrame) throws IOException {
        // Close the previous frame
        if (previousFrame != null) {
            previousFrame.dispose();
        }

        // Initialize the job manager
        jobManager = new JobHistoryManager();

        frame = new JFrame("Job History Screen");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(new Color(240, 230, 140));
        frame.setIconImage(ImageIO.read(new File("325-blue\\BlueTeamProject\\resources\\icon.png")));

        // Title label
        JLabel historyLabel = new JLabel("Job History", SwingConstants.CENTER);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(historyLabel, BorderLayout.NORTH);

        // Job history display area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBackground(new Color(173, 216, 230)); // Light blue background for the text area
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 139), 2); // Dark blue border
        historyArea.setBorder(border);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(140, 190, 230)); // Set background color for button panel

        JButton addButton = new JButton("Add Job");
        JButton backButton = new JButton("Back");

        // Add job button
        addButton.addActionListener(e -> openAddJobDialog());

        // Back button
        backButton.addActionListener(e -> {
            new ManagerScreen(); // Return to the ManagerScreen
            frame.dispose();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        refreshJobHistory();
    }

    private void refreshJobHistory() {
        // Update the job history display
        StringBuilder jobList = new StringBuilder("Current Job History:\n");
        List<EmployeeJob> jobs = jobManager.getJobs();
        for (EmployeeJob job : jobs) {
            jobList.append(job.viewJobInfo()).append("\n");
        }
        historyArea.setText(jobList.toString());
    }

    private void openAddJobDialog() {
        // Open a dialog to add a new job
        JDialog dialog = new JDialog(frame, "Add Job", true);
        dialog.setSize(300, 400);
        dialog.setLayout(new GridLayout(7, 2));

        // Set background color for dialog components
        dialog.getContentPane().setBackground(new Color(173, 216, 230));

        // Input fields for job details
        JTextField companyNameField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField startDateField = new JTextField("MMDDYYYY");
        JTextField endDateField = new JTextField("MMDDYYYY");
        JTextField salaryField = new JTextField();

        // Create custom borders for the text fields
        Border textFieldBorder = BorderFactory.createLineBorder(new Color(0, 0, 139), 2); // Darker blue border
        companyNameField.setBackground(new Color(173, 216, 230));
        companyNameField.setBorder(textFieldBorder);

        positionField.setBackground(new Color(173, 216, 230));
        positionField.setBorder(textFieldBorder);

        startDateField.setBackground(new Color(173, 216, 230));
        startDateField.setBorder(textFieldBorder);

        endDateField.setBackground(new Color(173, 216, 230));
        endDateField.setBorder(textFieldBorder);

        salaryField.setBackground(new Color(173, 216, 230));
        salaryField.setBorder(textFieldBorder);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Add fields to the dialog
        dialog.add(new JLabel("Description:"));
        dialog.add(companyNameField);
        dialog.add(new JLabel("Title:"));
        dialog.add(positionField);
        dialog.add(new JLabel("Start Date:"));
        dialog.add(startDateField);
        dialog.add(new JLabel("End Date:"));
        dialog.add(endDateField);
        dialog.add(new JLabel("Salary/Wage:"));
        dialog.add(salaryField);

        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            try {
                String companyName = companyNameField.getText();
                String position = positionField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                EmployeeJob job = new EmployeeJob(position, companyName, startDate, endDate, salary);

                jobManager.addJob(job);
                refreshJobHistory();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error saving job: " + ex.getMessage());
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
