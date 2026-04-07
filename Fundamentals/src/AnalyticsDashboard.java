import java.util.*;

public class AnalyticsDashboard {
    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();

    public void recordVisit(String url, String userId) {
        // Track total views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // Track unique users
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
    }

    public void displayStats(String url) {
        System.out.println("--- Stats for: " + url + " ---");
        System.out.println("Total Views: " + pageViews.getOrDefault(url, 0));
        System.out.println("Unique Visitors: " +
                (uniqueVisitors.containsKey(url) ? uniqueVisitors.get(url).size() : 0));
    }

    public static void main(String[] args) {
        AnalyticsDashboard dashboard = new AnalyticsDashboard();

        dashboard.recordVisit("/home", "user1");
        dashboard.recordVisit("/home", "user2");
        dashboard.recordVisit("/home", "user1"); // Repeat visit
        dashboard.recordVisit("/shop", "user3");

        dashboard.displayStats("/home");
        dashboard.displayStats("/shop");
    }
}