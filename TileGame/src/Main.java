import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener, Runnable {

    private static final int TILE_SIZE = 48;
    private static final int SCREEN_WIDTH = 768;
    private static final int SCREEN_HEIGHT = 576;

    private Thread gameThread;
    private boolean running = false;

    private int playerX = TILE_SIZE * 2;
    private int playerY = TILE_SIZE * 2;
    private int playerSpeed = 4;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private final int[][] map = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
            {1,0,1,1,1,0,1,1,1,0,1,1,1,0,0,1},
            {1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,1},
            {1,1,1,0,1,1,1,0,1,1,1,0,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public Main() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        final int fps = 60;
        final double drawInterval = 1000000000.0 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (running) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000.0;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void update() {
        int nextX = playerX;
        int nextY = playerY;

        if (up) {
            nextY -= playerSpeed;
        }
        if (down) {
            nextY += playerSpeed;
        }
        if (left) {
            nextX -= playerSpeed;
        }
        if (right) {
            nextX += playerSpeed;
        }

        if (!hitsWall(nextX, nextY)) {
            playerX = nextX;
            playerY = nextY;
        }
    }

    private boolean hitsWall(int x, int y) {
        int leftTile = x / TILE_SIZE;
        int rightTile = (x + TILE_SIZE - 1) / TILE_SIZE;
        int topTile = y / TILE_SIZE;
        int bottomTile = (y + TILE_SIZE - 1) / TILE_SIZE;

        return isWall(leftTile, topTile)
                || isWall(rightTile, topTile)
                || isWall(leftTile, bottomTile)
                || isWall(rightTile, bottomTile);
    }

    private boolean isWall(int col, int row) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return true;
        }

        return map[row][col] == 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
        drawPlayer(g);
        drawInstructions(g);
    }

    private void drawMap(Graphics g) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int tile = map[row][col];

                if (tile == 1) {
                    g.setColor(Color.DARK_GRAY);
                } else if (tile == 2) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(new Color(35, 120, 50));
                }

                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.WHITE);
        g.drawRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
    }

    private void drawInstructions(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Move with arrow keys or WASD. Reach the yellow tile.", 20, SCREEN_HEIGHT - 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            up = true;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            up = false;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for this game.
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phoenix Tile Game");

        Main game = new Main();

        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.startGame();
    }
}
