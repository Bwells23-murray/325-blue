package GUIs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SelectionScreen {

    private JFrame frame;

    public SelectionScreen() {
        frame = new JFrame("Selection Screen");
        frame.setSize(400, 300); // Set size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        frame.setLayout(new BorderLayout());


        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Grid layout for 2 buttons with space
        buttonPanel.setBackground(new Color(173, 216, 230)); // Light blue background

        // Create "Manager" and "User" buttons
        JButton managerButton = new JButton("Manager");
        JButton userButton = new JButton("User");

        // Set button properties (same size, color, etc.)
        managerButton.setPreferredSize(new Dimension(200, 40));
        managerButton.setBackground(new Color(30, 60, 90)); // Darker blue
        managerButton.setForeground(Color.WHITE);
        managerButton.setFocusPainted(false);

        userButton.setPreferredSize(new Dimension(200, 40));
        userButton.setBackground(new Color(30, 60, 90)); // Darker blue
        userButton.setForeground(Color.WHITE);
        userButton.setFocusPainted(false);

        // Add buttons to the panel
        buttonPanel.add(managerButton);
        buttonPanel.add(userButton);

        // ActionListener for "Manager" button
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the selection screen
                frame.dispose();
                // Open ManagerScreen
                new ManagerScreen();
            }
        });

        // ActionListener for "User" button
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the selection screen
                frame.dispose();
                // Open UserScreen
                UserScreen userScreen = new UserScreen();
                userScreen.display();
            }
        });

        // Add button panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Final frame settings
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new SelectionScreen(); // Start the selection screen
    }
}

