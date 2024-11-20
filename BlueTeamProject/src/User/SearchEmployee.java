package User;
import java.util.*;

public class SearchEmployee {
    private List<Employee> employees;

    public SearchEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> searchByNameOrID(String searchQuery) {
        List<Employee> results = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp != null && (emp.getID().equalsIgnoreCase(searchQuery) ||
                (emp.getFirstName() + " " + emp.getLastName()).equalsIgnoreCase(searchQuery))) {
                results.add(emp);
            }
        }
        return results;
    }
}