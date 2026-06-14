import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;

public class Main {

    private String[] enemies = { "Goblin", "Orc", "Troll", "Dragon" };
    private String[] items = { "Sword", "Shield", "Spear", "Bow" };
    private String[] enchantments = { "Fire", "Ice", "Lightning", "Poison" };

    private int buttonClicks = 0;

    private JLabel countLabel;
    private JLabel enemyLabel;
    private JLabel weaponLabel;
    private JLabel enchantmentLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.createWindow();
        });
    }

    private void createWindow() {
        JFrame window = new JFrame("Phoenix Learns Java");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 400);
        window.setLocationRelativeTo(null);

        JLabel helloLabel = new JLabel("Hello Phoenix!", JLabel.CENTER);
        helloLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        helloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        countLabel = new JLabel("Button clicks: 0");
        enemyLabel = new JLabel("Enemy is: None");
        weaponLabel = new JLabel("Weapon is: None");
        enchantmentLabel = new JLabel("Enchantment is: None");

        JButton clickButton = new JButton("Click Me");
        JButton enemyButton = new JButton("Enemy Generator");
        JButton weaponButton = new JButton("Weapon Generator");
        JButton enchantmentButton = new JButton("Enchantment Generator");

        clickButton.addActionListener(event -> increaseCounter());
        enemyButton.addActionListener(event -> generateEnemy());
        weaponButton.addActionListener(event -> generateWeapon());
        enchantmentButton.addActionListener(event -> generateEnchantment());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(helloLabel);
        panel.add(countLabel);
        panel.add(enemyLabel);
        panel.add(weaponLabel);
        panel.add(enchantmentLabel);

        panel.add(clickButton);
        panel.add(enemyButton);
        panel.add(weaponButton);
        panel.add(enchantmentButton);

        window.add(panel);
        window.setVisible(true);
    }

    private void increaseCounter() {
        buttonClicks++;
        countLabel.setText("Button clicks: " + buttonClicks);
    }

    private void generateEnemy() {
        int randomIndex = (int) (Math.random() * enemies.length);
        String randomEnemy = enemies[randomIndex];

        System.out.println("A wild " + randomEnemy + " appears!");
        enemyLabel.setText("Enemy is: " + randomEnemy);
    }

    private void generateWeapon() {
        int randomIndex = (int) (Math.random() * items.length);
        String randomWeapon = items[randomIndex];

        System.out.println("You found a " + randomWeapon + "!");
        weaponLabel.setText("Weapon is: " + randomWeapon);
    }

    private void generateEnchantment() {
        int randomIndex = (int) (Math.random() * enchantments.length);
        String randomEnchantment = enchantments[randomIndex];

        System.out.println("Your weapon is enchanted with " + randomEnchantment + "!");
        enchantmentLabel.setText("Enchantment is: " + randomEnchantment);
    }
}