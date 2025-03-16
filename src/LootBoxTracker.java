import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LootBoxTracker {
    private static final String FILE_NAME = "lootbox_count.txt";
    private static final String INVENTORY_FILE = "inventory.txt";

    public static AtomicInteger loadLootBoxCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return new AtomicInteger(Integer.parseInt(reader.readLine()));
        } catch (Exception e) {
            return new AtomicInteger(0);
        }
    }

    public static void saveLootBoxCount(int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(count));
        } catch (IOException e) {
            System.out.println("Error saving loot box count: " + e.getMessage());
        }
    }

    public static List<String> loadInventory() {
        List<String> inventory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inventory.add(line);
            }
        } catch (Exception e) {
            System.out.println("Inventory not found, starting fresh.");
        }
        return inventory;
    }

    public static void saveInventory(List<String> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (String item : inventory) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}
