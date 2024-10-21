package Skill;

public class SoftSkill extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public SoftSkill(String name, String type, int level) {
        super(name, type, level);
    }

    public SoftSkill(String name, int level) {
        super(name, "Soft Skill", level);
    }

    public SoftSkill() {
    }
}
