import java.util.*;

public class UsernameService{
    private HashSet<String> takenUsernames = new HashSet<>();
    private HashMap<String, Integer> searchFrequency = new HashMap<>();

    public void addExistingUser(String username) {
        takenUsernames.add(username.toLowerCase());
    }

    public boolean isAvailable(String username) {
        String name = username.toLowerCase();
        searchFrequency.put(name, searchFrequency.getOrDefault(name, 0) + 1);
        return !takenUsernames.contains(name);
    }

    public List<String> getSuggestions(String username) {
        List<String> suggestions = new ArrayList<>();
        int count = 1;
        while (suggestions.size() < 3) {
            String candidate = username.toLowerCase() + count;
            if (!takenUsernames.contains(candidate)) {
                suggestions.add(candidate);
            }
            count++;
        }
        return suggestions;
    }

    public void displayAnalytics() {
        System.out.println("Search Analytics: " + searchFrequency);
    }

    public static void main(String[] args) {
        UsernameService service = new UsernameService();
        service.addExistingUser("alice");
        service.addExistingUser("bob");

        String input = "alice";
        if (!service.isAvailable(input)) {
            System.out.println("Username '" + input + "' is taken.");
            System.out.println("Suggestions: " + service.getSuggestions(input));
        }
        service.displayAnalytics();
    }
}