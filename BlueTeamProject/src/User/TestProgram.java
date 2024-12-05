package User;
import java.io.IOException;

public class TestProgram {

    public static void main(String[] args) throws IOException {
        try {
            Manager m = new Manager();
            /*Tries to create several employees, delete one of them, and edit one of their values.
            Output should be:
            0142,Jorge,Matthew,null,null,null,null,null
            9889,Bobby,ybboB,null,null,null,null,null
            7462,Josephine,Clark,null,null,null,null,null
            6327,Cantthink,Ofmoore,null,null,null,null,null
            */
            m.testFile();
            m.createEmployee("0143", "Daviditto", "Matthew");
            m.createEmployee("3256", "Jim", "Jimminy");
            m.createEmployee("9889", "Bobby", "ybboB");
            m.createEmployee("7462", "Josephine", "Clark");
            m.createEmployee("6327", "Cantthink", "Ofmoore");
            //m.deleteEmployee("3256");
            m.editEmployee("0143", "Jorge", 3);
            m.editEmployee("9889", "Jefferson", 2);
            m.createEmployee("1789", "George", "Washington");
            m.editEmployee("1789", "President George", 2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Test program finished");
        }



      
    }
}
