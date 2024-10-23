package Skill;

public class Virtue extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public Virtue(String name, String type, int level) {
        super(name, type, level);
    }

    public Virtue(String name, int level) {
        super(name, "Virtue", level);
    }

    public Virtue() {
    }
}