import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;

public class Main {

    // This variable remembers how many times the button was clicked.
    private int buttonClicks = 0;

    // This label will change when the counter changes.
    private JLabel countLabel;

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

        JButton clickButton = new JButton("Click Me");

        // This event runs every time the button is clicked.
        clickButton.addActionListener(event -> increaseCounter());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(helloLabel, BorderLayout.NORTH);
        panel.add(countLabel, BorderLayout.CENTER);
        panel.add(clickButton, BorderLayout.SOUTH);

        window.add(panel);
        window.setVisible(true);
    }

    // This method changes the variable and updates the label.
    private void increaseCounter() {
        buttonClicks = buttonClicks + 1;
        countLabel.setText("Button clicks: " + buttonClicks);
    }
}
