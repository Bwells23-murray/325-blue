package GUIs;

import User.Manager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EditEmployeeScreen {
    private JFrame frame;
    private Manager manager;
    private ManagerDisplayEmployeeScreen managerDisplayScreen;
    private JTextField empIDField, firstNameField, lastNameField, emailField;

    public EditEmployeeScreen(ManagerDisplayEmployeeScreen managerDisplayScreen, Manager manager,
                               String empID, String firstName, String lastName, String email, String username) {
        this.manager = manager;
        this.managerDisplayScreen = managerDisplayScreen;

        // Close the manager display screen before opening the edit employee screen
        managerDisplayScreen.frame.dispose();

        frame = new JFrame("Edit Employee");
        frame.setSize(400, 350);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // Create and add labels and text fields with borders
        frame.add(new JLabel("Employee ID:"));
        empIDField = new JTextField(empID);  // Pre-fill the employee ID
        empIDField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        empIDField.setEditable(false);  // ID should not be editable
        frame.add(empIDField);

        frame.add(new JLabel("First Name:"));
        firstNameField = new JTextField(firstName);  // Pre-fill the first name
        firstNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(firstNameField);

        frame.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(lastName);  // Pre-fill the last name
        lastNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(lastNameField);

        frame.add(new JLabel("Email:"));
        emailField = new JTextField(email);  // Pre-fill the email
        emailField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(emailField);

        // Create the Save button
        JButton saveButton = new JButton("Save");
        frame.add(saveButton);

        // Add action listener to the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you wish to save this?",
                        "Confirm Save",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    // Get values from the fields
                    String empID = empIDField.getText().trim();
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String email = emailField.getText().trim();

                    // Check for missing fields
                    if (empID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill out all fields.");
                        return;
                    }

                    try {
                        // Update employee information in the CSV file via the manager
                        updateEmployeeInCSV(empID, firstName, lastName, email);

                        // Display success message
                        JOptionPane.showMessageDialog(frame, "Employee updated: " + firstName + " " + lastName);

                        // Close the current window (EditEmployeeScreen)
                        frame.dispose();
                        // Reopen and show ManagerDisplayEmployeeScreen
                        managerDisplayScreen.reloadEmployeeData(); // Refresh data if needed
                        managerDisplayScreen.frame.setVisible(true); // Make it visible again
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error updating employee: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You clicked No.");
                }
            }
        });

        // Create and add the back button to return to ManagerDisplayEmployeeScreen
        JButton backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(16f)); // Optional font style
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window (EditEmployeeScreen)
                frame.dispose();
                // Reopen and show ManagerDisplayEmployeeScreen
                managerDisplayScreen.frame.setVisible(true); // Make it visible again
            }
        });

        frame.add(backButton);  // Add back button to the frame

        // Final frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }

    // Method to update the employee in the CSV file
    private void updateEmployeeInCSV(String empID, String firstName, String lastName, String email) throws IOException {
        String filePath = "BlueTeamProject\\output\\employees.csv";
        File file = new File(filePath);
        File tempFile = new File(filePath + ".temp");
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean employeeFound = false;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(empID)) { // Check if employee ID matches
                    // Update the employee details
                    line = empID + "," + firstName + "," + lastName + "," + email + "," + empID;
                    employeeFound = true;
                }
                writer.write(line + "\n");
            }

            if (!employeeFound) {
                throw new IOException("Employee ID not found.");
            }

            // Close the resources
            reader.close();
            writer.close();

            // Delete the original file and rename the temp file
            if (file.exists() && !file.delete()) {
                throw new IOException("Could not delete original file.");
            }

            if (!tempFile.renameTo(file)) {
                throw new IOException("Could not rename temp file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        }
    }
}
