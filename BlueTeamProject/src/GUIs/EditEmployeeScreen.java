package GUIs;

//TODO ethan, Please make this work for editing specific employees, the csv file isn't working for me 
import User.Manager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EditEmployeeScreen {
    private JFrame frame;
    private Manager manager;
    private ManagerDisplayEmployeeScreen managerDisplayScreen;  // Store reference to the manager display screen
    private JTextField empIDField, firstNameField, lastNameField, emailField;

    public EditEmployeeScreen(ManagerDisplayEmployeeScreen managerDisplayScreen, Manager manager) {
        this.manager = manager;
        this.managerDisplayScreen = managerDisplayScreen;  // Pass the display screen to refresh later
        // Close the manager display screen before opening the edit employee screen
        managerDisplayScreen.frame.dispose(); // Close the manager screen before opening edit screen
        
        frame = new JFrame("Edit Employee");
        // Set the size and layout of the frame
        frame.setSize(400, 350);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setLayout(new GridLayout(7, 2, 10, 10));  // Increased grid rows to fit the back button

        // Create and add labels and text fields with borders
        frame.add(new JLabel("Employee ID:"));
        empIDField = new JTextField();
        empIDField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(empIDField);

        frame.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        firstNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(firstNameField);

        frame.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        lastNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2));
        frame.add(lastNameField);

        frame.add(new JLabel("Email:"));
        emailField = new JTextField();
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
                        // Update employee information in the database
                        manager.editEmployee(empID, firstName, 2); // Edit first name
                        manager.editEmployee(empID, lastName, 3); // Edit last name
                        manager.editEmployee(empID, email, 4);   // Edit email

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

    public static void main(String[] args) {
        // Assuming we already have a Manager instance
        Manager manager = new Manager();
        // Create an instance of ManagerDisplayEmployeeScreen and pass it to EditEmployeeScreen
        ManagerDisplayEmployeeScreen managerDisplayScreen = new ManagerDisplayEmployeeScreen();
        managerDisplayScreen.frame.setVisible(true);  // Make sure the frame is visible
        new EditEmployeeScreen(managerDisplayScreen, manager);  // Pass the instance to EditEmployeeScreen
    }
}
