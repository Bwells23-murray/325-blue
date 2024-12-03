package Skill;

public class SoftSkill extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public SoftSkill(String name, String type, int level, int usefullness) {
        super(name, type, level, usefullness);
    }

    public SoftSkill(String name, int level, int usefullness) {
        super(name, "Soft Skill", level, usefullness);
    }

    public SoftSkill() {
    }
}
