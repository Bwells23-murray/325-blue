package GUIs;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UserScreen {

    private JFrame frame;

    // Constructor for initializing the UserScreen 
    public UserScreen() throws IOException {
        // Create the main frame
        frame = new JFrame("User Screen");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(ImageIO.read(new File("325-blue\\BlueTeamProject\\resources\\icon.png")));

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
                EvaluationScreen evaluationScreen = new EvaluationScreen();  // Correct constructor
                try {
                    evaluationScreen.startEvaluation();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }  // Start the evaluation process
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
                UserScreen userScreen;
                try {
                    userScreen = new UserScreen();
                    userScreen.display();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
    }
}
