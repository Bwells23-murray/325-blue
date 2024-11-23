package GUIs;

import Skill.Skill; // Correct import for the Skill class
import java.util.ArrayList;
import java.util.List;

public class SkillManager {
    private List<Skill> skillsList; // List to store skills

    // Constructor to initialize the skills list
    public SkillManager() {
        skillsList = new ArrayList<>();
    }

    // Method to add a skill to the list
    public void addSkill(Skill skill) {
        skillsList.add(skill);
    }

    // Method to retrieve all skills
    public List<Skill> getSkills() {
        return skillsList;
    }
}
