import java.util.*;

public class RateLimiter {
    private Map<String, Integer> requestCounts = new HashMap<>();
    private final int MAX_REQUESTS_PER_MINUTE = 5;

    public boolean isAllowed(String clientId) {
        // Create a key based on client and current minute
        long currentMinute = System.currentTimeMillis() / 60000;
        String key = clientId + "_" + currentMinute;

        int count = requestCounts.getOrDefault(key, 0);

        if (count < MAX_REQUESTS_PER_MINUTE) {
            requestCounts.put(key, count + 1);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
        String user = "Client_A";

        for (int i = 1; i <= 7; i++) {
            if (limiter.isAllowed(user)) {
                System.out.println("Request " + i + ": Allowed");
            } else {
                System.out.println("Request " + i + ": Rejected (Rate Limit Exceeded)");
            }
        }
    }
}