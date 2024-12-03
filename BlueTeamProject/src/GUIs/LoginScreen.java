package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;

    public LoginScreen() {
        frame = new JFrame("Login");
        
        // Set the size and layout of the frame
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        
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
                
                if(usernameField.getText().trim().equals("BluAdmin") && passwordField.getText().trim().equals("1024"))
                {
                    new ManagerScreen(); // Open the selection screen
                    frame.dispose(); // Close the current frame
                    
                    
                } else if (usernameField.getText().trim().equals("Bluser") && passwordField.getText().trim().equals("9998"))
                {
                    new UserScreen(); // Open the selection screen
                    frame.dispose(); // Close the current frame
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }

            }
        });

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeScreen(); // Go back to HomeScreen
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
