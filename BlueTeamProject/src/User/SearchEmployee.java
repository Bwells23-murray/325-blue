package User;

import java.util.*;

public class SearchEmployee {
    private List<User> users;

    // Constructor to initialize the list of users
    public SearchEmployee(List<User> users) {
        this.users = users;
    }

    // Method to search users by name or ID
    public List<User> searchByNameOrID(String searchQuery) {
        List<User> results = new ArrayList<>();
        
        // Validate input query
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            System.out.println("Search query cannot be null or empty.");
            return results; // Return an empty list
        }

        // Perform search
        for (User user : users) {
            if (user != null) {
                String fullName = (user.getFirstName() + " " + user.getLastName()).trim();
                if (user.getID().equalsIgnoreCase(searchQuery) || fullName.equalsIgnoreCase(searchQuery)) {
                    results.add(user);
                }
            }
        }
        return results;
    }

    // Optional: Display results for debugging or logging purposes
    public void displaySearchResults(String searchQuery) {
        List<User> results = searchByNameOrID(searchQuery);
        if (results.isEmpty()) {
            System.out.println("No matching users found for query: " + searchQuery);
        } else {
            System.out.println("Search results:");
            for (User user : results) {
                System.out.println(user.toString());
            }
        }
    }
}
