import java.io.*;
import java.util.*;

public class ItemGenerator {
    private static final Random random = new Random();
    private static final String[] ITEM_TYPES = {"Sword", "Shield", "Helmet", "Gauntlets", "Bow", "Staff"};
    //could be transformed into an API call to get random nouns...
    private static final String[] NOUNS = {"Radiance", "Darkness", "Ice", "Domination", "Shadow", "Dread"};
    //map to store rarity and corresponding adjectives
    private static final Map<String, List<String>> RARITY_ADJECTIVES = new HashMap<>();
    //load CSV data into the map
    static {
        String csvFile = "../resources/rarity_adjectives.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String rarity = parts[0];
                List<String> adjectives = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                RARITY_ADJECTIVES.put(rarity, adjectives);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }


    public static String generateItem(String rarity) {
        String itemType = ITEM_TYPES[random.nextInt(ITEM_TYPES.length)];
        String rarityAdjective = RARITY_ADJECTIVES.get(rarity).get(random.nextInt(RARITY_ADJECTIVES.get(rarity).size()));
        //rarity is not found in the map, use a default adjective
        if (rarityAdjective == null) {
            rarityAdjective = "Unknown";
        }
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        return rarityAdjective + " " + itemType + " of " + noun;
    }

    //for debugging purposes
    public static String[] generateDebug(String[] rarities) {
        String[] items = new String[rarities.length];
        for (int i = 0; i < rarities.length; i++) {
            items[i] = generateItem(rarities[i]);
        }
        return items;
    }
    public static void main(String[] args) {
        String[] rarities = {"Common", "Rare", "Legendary"};
        String[] items = generateDebug(rarities);
        for (String item : items) {
            System.out.println(item);
        }
    }
}