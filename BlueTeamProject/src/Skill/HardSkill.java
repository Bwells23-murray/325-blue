package Skill;

public class HardSkill extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public HardSkill(String name, String type, int level, int usefullness) {
        super(name, type, level, usefullness);
    }

    public HardSkill(String name, int level, int usefullness) {
        super(name, "Hard Skill", level, usefullness);
    }

    public HardSkill() {
    }
}
