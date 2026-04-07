import java.util.*;

public class FraudDetector {
    public static int[] findSuspiciousPair(int[] transactions, int target) {
        // Map to store: Value -> Index
        Map<Integer, Integer> seenAmounts = new HashMap<>();

        for (int i = 0; i < transactions.length; i++) {
            int currentAmount = transactions[i];
            int complement = target - currentAmount;

            if (seenAmounts.containsKey(complement)) {
                // Return the indices of the two transactions
                return new int[] { seenAmounts.get(complement), i };
            }

            seenAmounts.put(currentAmount, i);
        }
        return null; // No pair found
    }

    public static void main(String[] args) {
        int[] dailyTransactions = {120, 500, 300, 750, 200};
        int suspiciousTarget = 1050; // 300 + 750

        int[] result = findSuspiciousPair(dailyTransactions, suspiciousTarget);

        if (result != null) {
            System.out.println("Suspicious pair found at indices: " + result[0] + " and " + result[1]);
            System.out.println("Amounts: " + dailyTransactions[result[0]] + " + " + dailyTransactions[result[1]]);
        } else {
            System.out.println("No suspicious pair detected.");
        }
    }
}