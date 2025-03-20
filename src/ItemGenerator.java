import java.util.Random;

public class ItemGenerator {
    private static final String[] ITEM_TYPES = {"Sword", "Shield", "Helmet", "Gauntlets", "Bow", "Staff"};
    //could be transformed into an API call to get random adjectives...
    private static final String[] ADJECTIVES = {"Radiance", "Darkness", "Ice", "Domination", "Shadow", "Dread"};
    private static final Random random = new Random();

    //could be transformed to have the rarity corresponding to multiple adjectives
    //common could be: mundance, basic, poor, etc.
    public static String generateItem(String rarity) {
        String itemType = ITEM_TYPES[random.nextInt(ITEM_TYPES.length)];
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        return rarity + " " + itemType + " of " + adjective;
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