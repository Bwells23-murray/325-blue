package User;

import java.util.*;

public class FilterEmployees 
{
    private List<User> users;

    // Constructor to initialize the list of users
    public FilterEmployees(List<User> users)
    {
        this.users = users;
    }

    // Method to filter users alphabetically by first name, then last name
    public List<User> filterAlphabetically() 
    {
        List<User> sortedList = new ArrayList<>(users); // Create a copy to avoid modifying the original list
        sortedList.sort(Comparator.comparing(user -> (user.getFirstName() + " " + user.getLastName())));
        return sortedList;
    }

    // Optional: Add a method to display the sorted list (for debugging or logging purposes)
    public void displaySortedUsers() 
    {
        for (User user : filterAlphabetically()) {
            System.out.println(user.toString());
        }
    }
}
