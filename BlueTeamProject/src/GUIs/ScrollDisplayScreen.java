package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.*;


public class ScrollDisplayScreen {

    private JFrame frame;
    private JTextArea textArea;

    public ScrollDisplayScreen() throws IOException {
        frame = new JFrame("View All Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(ImageIO.read(new File("BlueTeamProject\\resources\\icon.png")));

        // Create JTextArea to display employees' information
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create JScrollPane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load employee data and display it in the JTextArea
        loadEmployeeData();

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadEmployeeData() {
        try (Scanner scn = new Scanner(new File("BlueTeamProject\\output\\employees.csv"))) {
            StringBuilder employeeInfo = new StringBuilder();

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] employeeData = line.split(",");
                
                if (employeeData.length >= 5) {
                    String empID = employeeData[0];
                    String firstName = employeeData[1];
                    String lastName = employeeData[2];
                    String email = employeeData[3];
                    String username = employeeData[4];

                    // Append the employee info to the JTextArea
                    employeeInfo.append("Employee ID: ").append(empID).append("\n");
                    employeeInfo.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
                    employeeInfo.append("Email: ").append(email).append("\n");
                    employeeInfo.append("Username: ").append(username).append("\n");
                    employeeInfo.append("--------------------------------------------------\n");
                }
            }

            // Set the content of the JTextArea with the employee information
            textArea.setText(employeeInfo.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            textArea.setText("Error: Employee data file not found.");
        }
    }

    public static void main(String[] args) {
        // Instantiate the ViewAllEmployees class to run the program
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ScrollDisplayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}


