package User;
import Job.*;
import Skill.*;

public class TestProgram {

    public static void main(String[] args) {
        Manager m = new Manager();
        Job[] jobsJobs = new Job[0];
        Skill[] skills = new Skill[0];
        m.createEmployee("0234", "Ethan", "Sexton");
        m.createEmployee("0236", "Ron", "Stantler");
        m.createEmployee("1022", "Skyler", "Kehm");

        m.createEmployee("4478", "Jobs", "Steven", "email@email.url", "jsteven", "il0v3myjob22", jobsJobs, skills);
        Employee sky = new Employee("1022", "Skyler", "Kehm","test@test.test", "skehm", "myl4rve");
        System.out.println(m.getEmployee("0234"));
        System.out.println(m.getEmployee("0236"));
        System.out.println(m.getEmployee("1022"));
        System.out.println(sky);
    }
}
