package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    private JFrame frame;

    public HomeScreen() {
        frame = new JFrame("Home Screen");
        
        // Set the size and layout of the frame
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        
        // Create a panel for the top blue section
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(10, 24, 74)); // Dark navy blue
        topPanel.setPreferredSize(new Dimension(300, 75)); // 1/4 height
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add welcome label to the top panel
        JLabel welcomeLabel = new JLabel("BLU");
        welcomeLabel.setForeground(Color.WHITE); // White text
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        topPanel.add(Box.createVerticalGlue()); // Add space above the label
        topPanel.add(welcomeLabel);
        topPanel.add(Box.createVerticalGlue()); // Add space below the label

        // Create a panel for the middle section
        JPanel middlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw thicker gold lines
                g.setColor(Color.ORANGE); // Gold color
                g.fillRect(0, 0, getWidth(), 5); // Top line
                g.fillRect(0, getHeight() - 5, getWidth(), 5); // Bottom line
            }
        };
        middlePanel.setBackground(new Color(17, 103, 177)); // Lighter blue
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add vertical glue to push the button to the center
        middlePanel.add(Box.createVerticalGlue()); // Space above button

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        middlePanel.add(loginButton);
        
        middlePanel.add(Box.createVerticalGlue()); // Space below button

        // Add action listener to the button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginScreen();
            }
        });

        // Create a panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(10, 24, 74)); // Dark navy blue
        bottomPanel.setPreferredSize(new Dimension(300, 75)); // 1/4 height

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
