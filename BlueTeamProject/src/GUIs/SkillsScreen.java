package GUIs;

import Skill.*;
import java.awt.*;
import javax.swing.*;

public class SkillsScreen {
    private JFrame frame;
    private JTextArea skillsArea;
    private SkillManager skillManager;

    public SkillsScreen(JFrame previousFrame) {
        // Close the previous frame
        if (previousFrame != null) {
            previousFrame.dispose();
        }

        // Initialize the skill manager
        skillManager = new SkillManager();

        frame = new JFrame("Skills Screen");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 230, 140));

        // Title label
        JLabel skillsLabel = new JLabel("Skills", SwingConstants.CENTER);
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(skillsLabel, BorderLayout.NORTH);

        // Skills display area
        skillsArea = new JTextArea();
        skillsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(skillsArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Skill");
        JButton backButton = new JButton("Back");

        // Add skill button
        addButton.addActionListener(e -> openAddSkillDialog());

        // Back button
        backButton.addActionListener(e -> {
            new ManagerScreen(); // Return to the ManagerScreen
            frame.dispose();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        refreshSkills();
    }

    private void refreshSkills() {
        // Update the skills display
        StringBuilder skillList = new StringBuilder("Current Skills:\n");
        for (Skill skill : skillManager.getSkills()) {
            skillList.append(skill.toString()).append("\n");
        }
        skillsArea.setText(skillList.toString());
    }

    private void openAddSkillDialog() {
        // Open a dialog to add a new skill
        JDialog dialog = new JDialog(frame, "Add Skill", true);
        dialog.setSize(300, 400);
        dialog.setLayout(new GridLayout(7, 2));

        // Input fields for skill details
        JTextField skillNameField = new JTextField();
        JTextField skillLevelField = new JTextField("1 - 10");
        JTextField skillUsefulnessField = new JTextField("1 - 10");

        JLabel typeLabel = new JLabel("Skill Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Gift", "Hard Skill", "Soft Skill", "Virtue"});

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Add fields to the dialog
        dialog.add(new JLabel("Skill Name:"));
        dialog.add(skillNameField);
        dialog.add(new JLabel("Skill Level:"));
        dialog.add(skillLevelField);
        dialog.add(new JLabel("Skill Usefulness:"));
        dialog.add(skillUsefulnessField);
        dialog.add(typeLabel);
        dialog.add(typeComboBox);
        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            try {
                String skillName = skillNameField.getText();
                int skillLevel = Integer.parseInt(skillLevelField.getText());
                int skillUsefulness = Integer.parseInt(skillUsefulnessField.getText());
                String type = (String) typeComboBox.getSelectedItem();

                if (skillLevel < 1 || skillLevel > 10 ) {
                    throw new NumberFormatException("Values must be between 1 and 10.");
                }

                Skill skill;
                if (type.equals("Gift")) {
                    skill = new Gift(skillName, skillLevel, skillUsefulness);
                } else if (type.equals("Hard Skill")) {
                    skill = new HardSkill(skillName, skillLevel, skillUsefulness);
                } else if (type.equals("Soft Skill")) {
                    skill = new SoftSkill(skillName, skillLevel, skillUsefulness);
                } else {
                    skill = new Virtue(skillName, skillLevel, skillUsefulness);
                }

                skillManager.addSkill(skill);
                refreshSkills();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error saving skill: " + ex.getMessage());
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
