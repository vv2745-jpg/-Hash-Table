import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {
    private Map<String, Integer> queryFrequency = new HashMap<>();

    public void recordQuery(String query) {
        query = query.toLowerCase();
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
    }

    public List<String> suggest(String prefix) {
        String p = prefix.toLowerCase();
        return queryFrequency.entrySet().stream()
                .filter(e -> e.getKey().startsWith(p))
                .sorted((a, b) -> b.getValue() - a.getValue()) // Sort by frequency
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();
        ac.recordQuery("how to code java");
        ac.recordQuery("how to code java");
        ac.recordQuery("how to bake cake");
        ac.recordQuery("how to code python");

        System.out.println("Suggestions for 'how to': " + ac.suggest("how to"));
    }
}