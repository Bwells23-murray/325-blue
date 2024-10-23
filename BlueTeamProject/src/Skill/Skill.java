package Skill;

public abstract class Skill {
    private String skillName, skillType;
    private int skillLevel;

    // #region Accessors
    public String getName() {
        return skillName;
    }

    public String getType() {
        return skillType;
    }

    public int getLevel() {
        return skillLevel;
    }

    // #region Mutators
    public void setName(String name) {
        skillName = name;
    }

    public void setType(String type) {
        skillType = type;
    }

    public void setLevel(int level) {
        skillLevel = level;
    }

    // #region Constructors
    public Skill(String name, String type, int level) {
        skillName = name;
        skillType = type;
        skillLevel = level;
    }

    public Skill() {
    } // Blank constructor if needed

    // #region Text Output - Prints skill info to command line
    public void identifySkill() {
        System.out.println(getType() + " of " + getName() + " has a level of " + getLevel() + ".");
    }

}
