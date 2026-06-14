import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;

public class Main {

    private String[] enemies = { "Goblin", "Orc", "Troll", "Dragon" };
    private String[] items = { "Sword", "Shield", "spear", "Bow" };
    private String[] enchantments = { "Fire", "Ice", "Lightning", "Poison" };
    // This variable remembers how many times the button was clicked.
    private int buttonClicks = 0;

    // This label will change when the counter changes.
    private JLabel countLabel;
    private JLabel enemyLabel;
    private JLabel weaponLabel;
    private JLabel enchantmentLabel;

    public static void main(String[] args) {
        // SwingUtilities starts the GUI safely.
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.createWindow();
        });
    }

    // This method creates the window and everything inside it.
    private void createWindow() {
        JFrame window = new JFrame("Phoenix Learns Java");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 250);
        window.setLocationRelativeTo(null);

        JLabel helloLabel = new JLabel("Hello Phoenix!", JLabel.CENTER);
        helloLabel.setFont(new Font("SansSerif", Font.BOLD, 30));

        countLabel = new JLabel("Button clicks: 0", JLabel.CENTER);
        enemyLabel = new JLabel("Enemy is", JLabel.CENTER);
        weaponLabel = new JLabel("Weapon is", JLabel.CENTER);
        JButton clickButton = new JButton("Click Me");
        JButton enemyButton = new JButton("Enemy Generator");
        JButton weaponbutton = new JButton("Weapon Generator");
        JButton enchantmentButton = new JButton("Enchantment Generator");
        // This event runs every time the button is clicked.
        clickButton.addActionListener(event -> increaseCounter());
        enemyButton.addActionListener(event -> generateEnemy());
        weaponbutton.addActionListener(event -> generateWeapon());
        enchantmentButton.addActionListener(event -> generateenchantment());
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(helloLabel, BorderLayout.NORTH);
        panel.add(countLabel, BorderLayout.CENTER);
        panel.add(enemyLabel, BorderLayout.CENTER);
        panel.add(weaponLabel, BorderLayout.CENTER);
        panel.add(enchantmentLabel, BorderLayout.CENTER);
        panel.add(clickButton, BorderLayout.SOUTH);
        panel.add(enemyButton, BorderLayout.SOUTH);
        panel.add(weaponbutton, BorderLayout.SOUTH);
        window.add(panel);
        window.setVisible(true);
    }

    // This method changes the variable and updates the label.
    private void increaseCounter() {
        buttonClicks = buttonClicks + 1;
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

    private void generateenchantment() {
        int randomIndex = (int) (Math.random() * enchantments.length);
        String randomEnchantment = enchantments[randomIndex];
        System.out.println("Your weapon is enchanted with " + randomEnchantment + "!");
        enchantmentLabel.setText("Enchantment is: " + randomEnchantment);
    }

}
