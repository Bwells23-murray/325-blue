package User;
import java.util.*;

public class FilterEmployees {
    private List<Employee> employees;

    public FilterEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> filterAlphabetically() {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(Comparator.comparing(emp -> emp.getFirstName() + " " + emp.getLastName()));
        return sortedList;
    }
}
