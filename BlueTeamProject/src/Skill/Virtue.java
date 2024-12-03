package Skill;

public class Virtue extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public Virtue(String name, String type, int level, int usefullness) {
        super(name, type, level, usefullness);
    }

    public Virtue(String name, int level, int usefullness) {
        super(name, "Virtue", level, usefullness);
    }

    public Virtue() {
    }
}