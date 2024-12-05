package GUIs;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CreateEmployeeScreen {
    private JFrame frame;

    public CreateEmployeeScreen() {
        frame = new JFrame("Create Employee");

        // Set the size and layout of the frame
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
        frame.setLayout(new GridLayout(7, 2, 10, 10)); // Grid layout for labels and text fields
        
        // Create and add labels and text fields with borders
        frame.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        firstNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2)); // Darker blue border
        frame.add(firstNameField);

        frame.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        lastNameField.setBorder(new LineBorder(new Color(30, 60, 90), 2)); // Darker blue border
        frame.add(lastNameField);

        frame.add(new JLabel("Job History:"));
        JTextArea jobHistoryArea = new JTextArea(3, 20);
        jobHistoryArea.setBorder(new LineBorder(new Color(30, 60, 90), 2)); // Darker blue border
        frame.add(jobHistoryArea);

        frame.add(new JLabel("Skills:"));
        JTextArea skillsArea = new JTextArea(3, 20);
        skillsArea.setBorder(new LineBorder(new Color(30, 60, 90), 2)); // Darker blue border :} i like blue
        frame.add(skillsArea);

        // Create the save button
        JButton saveButton = new JButton("Save");
        frame.add(saveButton);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, 
                        "Are you sure you wish to save this?", 
                        "Confirm Save", 
                        JOptionPane.YES_NO_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "You clicked Yes.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You clicked No.");
                }
            }
        });

        // Final frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}
