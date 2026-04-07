import java.util.*;

public class FlashSaleManager {
    private HashMap<String, Integer> inventory = new HashMap<>();
    private LinkedHashMap<String, String> waitingList = new LinkedHashMap<>();

    public void addStock(String item, int count) {
        inventory.put(item, count);
    }

    public void processPurchase(String userId, String itemId) {
        int currentStock = inventory.getOrDefault(itemId, 0);

        if (currentStock > 0) {
            inventory.put(itemId, currentStock - 1);
            System.out.println("User " + userId + " successfully purchased " + itemId);
        } else {
            waitingList.put(userId, itemId);
            System.out.println("Item out of stock. User " + userId + " added to waiting list.");
        }
    }

    public void showWaitingList() {
        System.out.println("Current Waiting List: " + waitingList);
    }

    public static void main(String[] args) {
        FlashSaleManager sale = new FlashSaleManager();
        sale.addStock("Phone15", 2);

        sale.processPurchase("User_A", "Phone15");
        sale.processPurchase("User_B", "Phone15");
        sale.processPurchase("User_C", "Phone15"); // Should go to waiting list

        sale.showWaitingList();
    }
}