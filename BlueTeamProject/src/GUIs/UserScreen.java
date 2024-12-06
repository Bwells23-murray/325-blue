package GUIs;
//TODO Jaz or Ethan, please make the normal user in login screen go here, and also make 
// this screen have jobHistory and Skills (the implementations are currently in managerScreen)
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UserScreen {

    private JFrame frame;

    // Constructor for initializing the UserScreen 
    public UserScreen() throws IOException {
        // Create the main frame
        frame = new JFrame("User Screen");
        frame.setSize(400, 300);  // Adjusted size for better visibility
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(ImageIO.read(new File("BlueTeamProject\\resources\\icon.png")));

        // Create a panel for the buttons with FlowLayout
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

        // Add "Job History" button
        JButton jobHistoryButton = new JButton("Job History");
        jobHistoryButton.setFont(new Font("Arial", Font.PLAIN, 16));
        jobHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open JobHistoryScreen and close the current screen
                try {
                    new HistoryScreen(frame);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(jobHistoryButton);

        // Add "Job Skills" button
        JButton jobSkillsButton = new JButton("Job Skills");
        jobSkillsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        jobSkillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SkillsScreen and close the current screen
                new SkillsScreen(frame);  // Pass the current frame to close it
            }
        });
        panel.add(jobSkillsButton);

        // Add the panel to the frame's content pane
        frame.getContentPane().add(panel);

        // Center the frame on the screen and make it visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to open the evaluation screen
    private void openEvaluationScreen() {
        // Create and display the EvaluationScreen
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.dispose();
                EvaluationScreen evaluationScreen = new EvaluationScreen(null, null, null, null);  // Correct constructor
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