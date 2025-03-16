import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LootBoxSimulator {
    private static final String[] LOOT_ITEMS = {"Common Item", "Uncommon Item", "Rare Item", "Epic Item", "Legendary Item"};
    private static final Color[] ITEM_COLORS = {Color.gray, Color.green, Color.blue, Color.magenta, Color.orange};
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Loot Box Simulator");
        frame.setSize(400, 200);
        //center the window
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //result of loot box
        JLabel resultLabel = new JLabel("Click 'Open Loot Box' to get an item!");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(resultLabel);

        //button to open
        JButton openButton = new JButton("Open Loot Box");
        frame.add(openButton);
        
        //label to show loot box count
        AtomicInteger lootBoxCount = new AtomicInteger(LootBoxTracker.loadLootBoxCount().get());
        JLabel countLabel = new JLabel("Loot Boxes Opened: " + lootBoxCount.get());
        countLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(countLabel);


        //Listener for button
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //weights on items to simualte rarity
                int index = getRandomIndex(new int[]{50, 30, 15, 4, 1});
                resultLabel.setText("You got: " + LOOT_ITEMS[index]);
                resultLabel.setForeground(ITEM_COLORS[index]);

                lootBoxCount.incrementAndGet();
                LootBoxTracker.saveLootBoxCount(lootBoxCount.get());

                countLabel.setText("Loot Boxes Opened: " + lootBoxCount.get());

                //sound effect
                playSound("../resources/lootbox_open.wav");
            }
        });

        frame.setVisible(true);
    }

    private static int getRandomIndex(int[] weights) {
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }
        int randomValue = new Random().nextInt(totalWeight);
        for (int i = 0; i < weights.length; i++) {
            if (randomValue < weights[i]) {
                return i;
            }
            randomValue -= weights[i];
        }
        return -1;
    }

    //play sound
    private static void playSound(String filename) {
        try {
            //load the sound file as a resource
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + filename);
                return;
            }
            
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
            clip.drain();
        } catch (Exception ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }
    
}
