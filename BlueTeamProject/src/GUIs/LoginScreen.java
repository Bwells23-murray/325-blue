package GUIs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LoginScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;

    public LoginScreen() throws IOException {
        frame = new JFrame("Login");

        // Set the size and layout of the frame
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(ImageIO.read(new File("BlueTeamProject\\resources\\icon.png")));

        // Create a panel for the top section
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(173, 216, 230)); // Light blue
        topPanel.setPreferredSize(new Dimension(300, 75)); // Top height
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add title label
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setForeground(Color.BLACK); // Black text
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        topPanel.add(Box.createVerticalGlue()); // Space above the label
        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalGlue()); // Space below the label

        // Create a panel for the middle section
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(173, 216, 230)); // Light blue
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add username field
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(new Color(173, 216, 230)); // Light blue
        usernameField = new JTextField(15);
        usernamePanel.add(new JLabel("Username: "));
        usernamePanel.add(usernameField);
        middlePanel.add(usernamePanel);

        // Add password field
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(173, 216, 230)); // Light blue
        passwordField = new JPasswordField(15);
        textPanel.add(new JLabel("Password: "));
        textPanel.add(passwordField);
        middlePanel.add(textPanel);

        // Add vertical glue to center the button
        middlePanel.add(Box.createVerticalGlue()); // Space above button

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        middlePanel.add(loginButton);

        // Add back to home screen button
        JButton backButton = new JButton("Back to Home");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        middlePanel.add(backButton);

        middlePanel.add(Box.createVerticalGlue()); // Space below buttons

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                if (username.equals("BluAdmin") && password.equals("1024")) {
                        new ManagerDisplayEmployeeScreen();
                   

                    frame.dispose(); // Close the current frame
                } /*else if (username.equals("Bluser") && password.equals("9998")){
                     {
                        EvaluationScreen evaluationScreen = new EvaluationScreen(null, null, null, null); // Correct
                        
                        try {
                            evaluationScreen.startEvaluation();
                            frame.dispose();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }        
                    }
                    } */ else {                                                                         // constructor
                        try {
                            Scanner scn = new Scanner(new File("BlueTeamProject\\output\\employees.csv"));
                            boolean isValidUser = false;
                            // Validate credentials
                            while (scn.hasNextLine()) {
                                String line = scn.nextLine();
                                String[] employeeDetails = line.split(","); // Assuming CSV format: username,password
                                if (employeeDetails.length >= 2) {
                                    String fileUsername = employeeDetails[1].trim();
                                    String filePassword = employeeDetails[0].trim();

                                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                                        isValidUser = true;
                                        break;
                                    }
                                }
                            }

                            if (isValidUser) 
                            {
                                new UserScreen();
                                frame.dispose();
                                scn.close();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                                scn.close();
                            }
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(frame, "Error: Employee file not found.");
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(frame, "An error occurred.");
                            ex.printStackTrace();
                        }
                    } 
                        
                    
                }

            }
        );

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new HomeScreen();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // Go back to HomeScreen
            }
        });

        // Create a panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(173, 216, 230)); // Light blue
        bottomPanel.setPreferredSize(new Dimension(300, 75)); // Bottom height

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Final frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}
