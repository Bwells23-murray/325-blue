package GUIs;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CreateEmployeeScreen {
    private JFrame frame;
    private ManagerDisplayEmployeeScreen managerDisplayScreen;  // Store reference to the manager display screen

    public CreateEmployeeScreen(ManagerDisplayEmployeeScreen managerDisplayScreen) {
        this.managerDisplayScreen = managerDisplayScreen;  // Pass the display screen to refresh later

        // Close the manager display screen before opening the create employee screen
        managerDisplayScreen.frame.dispose(); // This closes the manager screen before opening the create screen

        frame = new JFrame("Create Employee");

        // Set the size and layout of the frame
        frame.setSize(400, 350);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Create and add labels and text fields with borders
        frame.add(new JLabel("Employee ID:"));
        JTextField empIDField = new JTextField();
        empIDField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(empIDField);

        frame.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        firstNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(firstNameField);

        frame.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        lastNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(lastNameField);

        frame.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        emailField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(emailField);

        // Create the save button
        JButton saveButton = new JButton("Save");
        frame.add(saveButton);

        // Add action listener to the save button
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

                    // Save the employee to the CSV file
                    try {
                        saveEmployeeToCSV(empID, firstName, lastName, email);

                        // Display success message
                        JOptionPane.showMessageDialog(frame, "Employee created: " + firstName + " " + lastName);

                        // Close the current window (CreateEmployeeScreen)
                        frame.dispose();

                        // Reopen and show ManagerDisplayEmployeeScreen
                        managerDisplayScreen.reloadEmployeeData(); // Refresh data if needed
                        managerDisplayScreen.frame.setVisible(true); // Make it visible again
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error creating employee: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You clicked No.");
                }
            }
        });

        // Final frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }

    // Method to save the employee to the specified CSV path
    private void saveEmployeeToCSV(String empID, String firstName, String lastName, String email) throws IOException {
        String username = empID;  // Assuming empID is used as username
        String employeeData = empID + "," + firstName + "," + lastName + "," + email + "," + username + "\n";

        // Define the full path to the CSV file
        String filePath = "325-blue\\BlueTeamProject\\output\\employees.csv";

        // Append the new employee data to the CSV file at the specified path
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(employeeData); // Append to the file
        }
    }

    public static void main(String[] args) {
        // Create an instance of ManagerDisplayEmployeeScreen and pass it to CreateEmployeeScreen
        ManagerDisplayEmployeeScreen managerDisplayScreen = new ManagerDisplayEmployeeScreen();
        managerDisplayScreen.frame.setVisible(true);  // Make sure the frame is visible
        new CreateEmployeeScreen(managerDisplayScreen);  // Pass the instance to CreateEmployeeScreen
    }
}
