package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserScreen {

    private JFrame frame;

    // Constructor for initializing the UserScreen 
    public UserScreen() {
        // Create the main frame
        frame = new JFrame("User Screen");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel for the button
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create and add a button to open the EvaluationScreen
        JButton openEvaluationButton = new JButton("Start Evaluation");
        openEvaluationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEvaluationScreen();  // Opens the EvaluationScreen when clicked
            }
        });
        panel.add(openEvaluationButton);

        // Set up frame layout (needs to be tinkered with, very ugly)
        frame.getContentPane().add(panel);
    }

    // Method to open the evaluation screen
    private void openEvaluationScreen() {
        // Create and display the EvaluationScreen
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.dispose();
                EvaluationScreen evaluationScreen = new EvaluationScreen(null, null, null, null);  // Correct constructor
                evaluationScreen.startEvaluation();  // Start the evaluation process
            }
        });
    }

    // Method to show the UserScreen GUI
    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the UserScreen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserScreen userScreen = new UserScreen();
                userScreen.display();
            }
        });
    }
}
