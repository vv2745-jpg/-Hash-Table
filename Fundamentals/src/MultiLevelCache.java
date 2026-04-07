import java.util.*;

public class MultiLevelCache<K, V> extends LinkedHashMap<K, V> {
    private final int L1_CAPACITY = 5;

    public MultiLevelCache() {
        // true = access-order (moves recently used items to end)
        super(5, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > L1_CAPACITY) {
            System.out.println("L1 Full. Evicting " + eldest.getKey() + " to L2 Storage.");
            return true; // Removes from L1
        }
        return false;
    }

    public static void main(String[] args) {
        MultiLevelCache<String, String> cache = new MultiLevelCache<>();

        cache.put("Session_1", "Data_A");
        cache.put("Session_2", "Data_B");
        cache.put("Session_3", "Data_C");
        cache.put("Session_4", "Data_D");
        cache.put("Session_5", "Data_E");

        // Access Session_1 to make it "recently used"
        cache.get("Session_1");

        // Add one more to trigger eviction of the oldest (Session_2)
        System.out.println("Adding Session_6...");
        cache.put("Session_6", "Data_F");

        System.out.println("Current L1 Cache: " + cache.keySet());
    }
}