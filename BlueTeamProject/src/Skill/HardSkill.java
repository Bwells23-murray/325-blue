package Skill;

public class HardSkill extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public HardSkill(String name, String type, int level) {
        super(name, type, level);
    }

    public HardSkill(String name, int level) {
        super(name, "Hard Skill", level);
    }

    public HardSkill() {
    }
}
