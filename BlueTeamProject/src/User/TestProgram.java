package User;
import java.io.IOException;

import Job.*;
import Skill.*;

public class TestProgram {

    public static void main(String[] args) throws IOException {
        try {
            Manager m = new Manager();
            m.createEmployee("0142", "Dave", "Matthew");
            m.createEmployee("3256", "Jim", "Jimminy");
            m.createEmployee("9889", "Bobby", "ybboB");
            m.createEmployee("7462", "Josephine", "Clark");
            m.createEmployee("6327", "Cantthink", "Ofmoore");
            m.deleteEmployee("3256");
            m.editEmployee("0142", "Matthews", 3);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("Test program finished");
        }



      
    }
}
