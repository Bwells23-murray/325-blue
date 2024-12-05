package GUIs;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ManagerDisplayEmployeeScreen {

    static JFrame frame;
    private JPanel employeePanel;
    private JTextField searchField;  // Text field for searching
    private JButton searchButton;    // Button to trigger search

    public ManagerDisplayEmployeeScreen() {
        frame = new JFrame("View All Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a JPanel to hold search functionality
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the search JTextField
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(new JLabel("Search by Name or ID: "));
        searchPanel.add(searchField);

        // Create the search JButton
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(searchButton);

        // Add the search panel to the top of the frame (NORTH)
        frame.add(searchPanel, BorderLayout.NORTH);

        // Create a JPanel to hold employee data with buttons
        employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS)); // Stacks employee data vertically

        // Create JScrollPane to make the employee panel scrollable
        JScrollPane scrollPane = new JScrollPane(employeePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load employee data and display it in the JPanel with "Edit" buttons
        loadEmployeeData();

        // Create "Create Employee" button and add it to the bottom (SOUTH)
        JButton createEmployeeButton = new JButton("Create Employee");
        createEmployeeButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Action listener to open the CreateEmployeeScreen when clicked
        createEmployeeButton.addActionListener(e -> openCreateEmployeeScreen());

        // Add the button to the bottom of the frame (SOUTH)
        frame.add(createEmployeeButton, BorderLayout.SOUTH);

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

                    // Create a panel for this employee with their details and buttons
                    JPanel employeePanelRow = new JPanel();
                    employeePanelRow.setLayout(new BoxLayout(employeePanelRow, BoxLayout.X_AXIS)); // Horizontal layout

                    // Add employee info to the row
                    JTextArea employeeInfo = new JTextArea("ID: " + empID + " | Name: " + firstName + " " + lastName + " | Email: " + email + " | Username: " + username);
                    employeeInfo.setEditable(false);
                    employeeInfo.setFont(new Font("Arial", Font.PLAIN, 14));

                    // Create "Edit", "History", "Skills", and "Evaluations" buttons for this employee
                    JButton editButton = new JButton("Edit");
                    editButton.setFont(new Font("Arial", Font.PLAIN, 12));
                    editButton.addActionListener(e -> openEditEmployeeScreen(empID, firstName, lastName, email, username));

                    JButton historyButton = new JButton("History");
                    historyButton.setFont(new Font("Arial", Font.PLAIN, 12));
                    historyButton.addActionListener(e -> openHistoryScreen(empID));

                    JButton skillsButton = new JButton("Skills");
                    skillsButton.setFont(new Font("Arial", Font.PLAIN, 12));
                    skillsButton.addActionListener(e -> openSkillsScreen(empID));

                    JButton evaluationsButton = new JButton("Evaluations");
                    evaluationsButton.setFont(new Font("Arial", Font.PLAIN, 12));
                    evaluationsButton.addActionListener(e -> openEvaluationsScreen(empID));

                    // Add employee info and the buttons to the panel
                    employeePanelRow.add(employeeInfo);
                    employeePanelRow.add(Box.createHorizontalStrut(10)); // Space between info and button
                    employeePanelRow.add(editButton);
                    employeePanelRow.add(Box.createHorizontalStrut(5)); // Space between buttons
                    employeePanelRow.add(historyButton);
                    employeePanelRow.add(Box.createHorizontalStrut(5)); // Space between buttons
                    employeePanelRow.add(skillsButton);
                    employeePanelRow.add(Box.createHorizontalStrut(5)); // Space between buttons
                    employeePanelRow.add(evaluationsButton);

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

    public void reloadEmployeeData(){
        loadEmployeeData();
    }

    // Method to perform search based on the entered query
    private void performSearch() {
        String searchQuery = searchField.getText().trim();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search query.");
            return;
        }

        // Placeholder for the search action (you need to implement search logic)
        JOptionPane.showMessageDialog(frame, "Searching for: " + searchQuery);
    }

    // Method to open the CreateEmployeeScreen
    private void openCreateEmployeeScreen() {
        new CreateEmployeeScreen(null);
    }

    // Method to open the EditEmployeeScreen for the selected employee
    private void openEditEmployeeScreen(String empID, String firstName, String lastName, String email, String username) {
        new EditEmployeeScreen();
    }

    // Placeholder methods for the "History", "Skills", and "Evaluations" screens
    private void openHistoryScreen(String empID) {
        JOptionPane.showMessageDialog(frame, "Opening History for employee ID: " + empID);
    }

    private void openSkillsScreen(String empID) {
        JOptionPane.showMessageDialog(frame, "Opening Skills for employee ID: " + empID);
    }

    private void openEvaluationsScreen(String empID) {
        JOptionPane.showMessageDialog(frame, "Opening Evaluations for employee ID: " + empID);
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
