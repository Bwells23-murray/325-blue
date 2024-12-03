package Skill;

public class Gift extends Skill {
    // Constructors for 3 arguments, 2 arguments, and no arguments
    public Gift(String name, String type, int level, int usefullness) {
        super(name, type, level, usefullness);
    }

    public Gift(String name, int level, int usefullness) {
        super(name, "Gift", level, usefullness);
    }

    public Gift() {
    }
}