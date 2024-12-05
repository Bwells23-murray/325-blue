package GUIs;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ManagerDisplayEmployeeScreen {

    private JFrame frame;
    private JPanel employeePanel;

    public ManagerDisplayEmployeeScreen() {
        frame = new JFrame("View All Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create "Create Employee" button
        JButton createEmployeeButton = new JButton("Create Employee");
        createEmployeeButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Action listener to open the CreateEmployeeScreen when clicked
        createEmployeeButton.addActionListener(e -> openCreateEmployeeScreen());

        // Add the button to the top of the frame (NORTH)
        frame.add(createEmployeeButton, BorderLayout.NORTH);

        // Create a JPanel to hold employee data with buttons
        employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));  // Stacks employee data vertically

        // Create JScrollPane to make the employee panel scrollable
        JScrollPane scrollPane = new JScrollPane(employeePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load employee data and display it in the JPanel with "Edit" buttons
        loadEmployeeData();

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadEmployeeData() {
        try (Scanner scn = new Scanner(new File("325-blue\\BlueTeamProject\\output\\employees.csv"))) {
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] employeeData = line.split(",");

                if (employeeData.length >= 5) {
                    String empID = employeeData[0];
                    String firstName = employeeData[1];
                    String lastName = employeeData[2];
                    String email = employeeData[3];
                    String username = employeeData[4];

                    // Create a panel for this employee with their details and an "Edit" button
                    JPanel employeePanelRow = new JPanel();
                    employeePanelRow.setLayout(new BoxLayout(employeePanelRow, BoxLayout.X_AXIS)); // Horizontal layout

                    // Add employee info to the row
                    JTextArea employeeInfo = new JTextArea("ID: " + empID + " | Name: " + firstName + " " + lastName + " | Email: " + email + " | Username: " + username);
                    employeeInfo.setEditable(false);
                    employeeInfo.setFont(new Font("Arial", Font.PLAIN, 14));

                    // Create "Edit" button for this employee
                    JButton editButton = new JButton("Edit");
                    editButton.setFont(new Font("Arial", Font.PLAIN, 12));

                    // Action listener for the Edit button
                    editButton.addActionListener(e -> openEditEmployeeScreen(empID, firstName, lastName, email, username));

                    // Add employee info and "Edit" button to the panel
                    employeePanelRow.add(employeeInfo);
                    employeePanelRow.add(Box.createHorizontalStrut(10)); // Space between info and button
                    employeePanelRow.add(editButton);

                    // Add the employee panel to the main panel
                    employeePanel.add(employeePanelRow);
                    employeePanel.add(Box.createVerticalStrut(5)); // Space between employee entries
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Employee data file not found.");
        }
    }

    // Method to open the CreateEmployeeScreen
    private void openCreateEmployeeScreen() {
        new CreateEmployeeScreen();
    }

    // Method to open the EditEmployeeScreen for the selected employee
    private void openEditEmployeeScreen(String empID, String firstName, String lastName, String email, String username) {
        new EditEmployeeScreen();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManagerDisplayEmployeeScreen();
            }
        });
    }
}
