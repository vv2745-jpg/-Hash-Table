import java.util.*;

public class PlagiarismDetector {
    // Maps a 3-word phrase to a set of Document IDs containing it
    private Map<String, Set<String>> index = new HashMap<>();

    public void indexDocument(String docId, String content) {
        String[] words = content.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

        for (int i = 0; i < words.length - 2; i++) {
            String trigram = words[i] + " " + words[i+1] + " " + words[i+2];
            index.computeIfAbsent(trigram, k -> new HashSet<>()).add(docId);
        }
    }

    public void detectOverlap(String docId1, String docId2) {
        int commonGrams = 0;
        for (Set<String> docs : index.values()) {
            if (docs.contains(docId1) && docs.contains(docId2)) {
                commonGrams++;
            }
        }
        System.out.println("Common phrases between " + docId1 + " and " + docId2 + ": " + commonGrams);
    }

    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector();

        detector.indexDocument("DocA", "The quick brown fox jumps over the lazy dog");
        detector.indexDocument("DocB", "A quick brown fox jumps over the sleeping cat");

        detector.detectOverlap("DocA", "DocB");
    }
}