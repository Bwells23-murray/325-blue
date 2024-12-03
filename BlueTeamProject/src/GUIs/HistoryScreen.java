package GUIs;

import User.EmployeeJob;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class HistoryScreen {
    private JFrame frame;
    private JTextArea historyArea;
    private JobHistoryManager jobManager;

    public HistoryScreen(JFrame previousFrame) {
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

        // Title label
        JLabel historyLabel = new JLabel("Job History", SwingConstants.CENTER);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(historyLabel, BorderLayout.NORTH);

        // Job history display area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Job");
        JButton backButton = new JButton("Back");

        // Add job button
        addButton.addActionListener(e -> openAddJobDialog());

        // Back button
        backButton.addActionListener(e -> {
            new ManagerScreen(); // Return to the ManagerScreen (make sure to define this class)
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

        // Input fields for job details
        JTextField companyNameField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField startDateField = new JTextField("MMDDYYYY");
        JTextField endDateField = new JTextField("MMDDYYYY");
        JTextField salaryField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Add fields to the dialog
        dialog.add(new JLabel("Company Name:"));
        dialog.add(companyNameField);
        dialog.add(new JLabel("Position:"));
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


