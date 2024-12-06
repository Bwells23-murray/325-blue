package GUIs;

import User.Manager;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

//TODO Ethan please connect this to where it can read the job Skill array for the specific user 
//     from the csv file, this screen is accessed by a button in the scroll screen
public class ViewSkillsScreen {


    private JFrame frame;
    private JTextArea textArea;

    public ViewSkillsScreen() throws IOException {
        frame = new JFrame("View Skills");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(ImageIO.read(new File("BlueTeamProject\\resources\\icon.png")));

        // Create JTextArea to display history' information
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create JScrollPane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load  data and display it in the JTextArea
        //loadSkillsData(); /idk how this will be implemented

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadSkillData() {
        //insert Skill loading data please 
    }
    public static void main(String[] args) {
        // Instantiate the ViewSkillsScreen class to run the program
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ViewSkillsScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

