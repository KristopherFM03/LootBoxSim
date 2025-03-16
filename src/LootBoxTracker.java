import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LootBoxTracker {
    private static final String FILE_NAME = "lootbox_count.txt";

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
}
