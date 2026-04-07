public class ParkingSlotMap {
    private String[] slots;
    private int capacity;

    public ParkingSlotMap(int capacity) {
        this.capacity = capacity;
        this.slots = new String[capacity];
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % capacity;
    }

    public int assignSlot(String licensePlate) {
        int index = hash(licensePlate);
        int startIndex = index;

        // Linear Probing: find next empty slot
        while (slots[index] != null) {
            index = (index + 1) % capacity;
            if (index == startIndex) return -1; // Lot is full
        }

        slots[index] = licensePlate;
        return index;
    }

    public void releaseSlot(int slotNumber) {
        if (slotNumber >= 0 && slotNumber < capacity) {
            slots[slotNumber] = null;
            System.out.println("Slot " + slotNumber + " is now free.");
        }
    }

    public static void main(String[] args) {
        ParkingSlotMap lot = new ParkingSlotMap(10);
        System.out.println("Car A assigned to slot: " + lot.assignSlot("ABC-123"));
        System.out.println("Car B assigned to slot: " + lot.assignSlot("XYZ-789"));
    }
}