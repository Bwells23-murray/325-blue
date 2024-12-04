package GUIs;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManagerScreen {
    private JFrame frame;

    public ManagerScreen() throws IOException{
        frame = new JFrame("Manager Screen");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        // Add welcome label
        JLabel managerLabel = new JLabel("Welcome to the Manager Screen", SwingConstants.CENTER);
        managerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(managerLabel, BorderLayout.CENTER);

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
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // Pass the current frame to close it
            }
        });
        frame.add(jobHistoryButton, BorderLayout.SOUTH);

        JButton jobSkillsButton = new JButton("Job Skills");
        jobSkillsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        jobSkillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open JobHistoryScreen and close the current screen
                new SkillsScreen(frame); // Pass the current frame to close it
            }
        });
        frame.add(jobSkillsButton, BorderLayout.CENTER);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
