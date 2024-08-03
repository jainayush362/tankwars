import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Tank tank1, tank2;
    private List<Bullet> bullets;
    private List<Wall> walls;
    private List<PowerUp> powerUps;
    private List<HealthBar> healthBars;
    private boolean gameRunning;

    // Track pressed keys
    private boolean wPressed, sPressed, aPressed, dPressed;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        timer = new Timer(16, this); // Roughly 60 FPS
        bullets = new ArrayList<>();
        walls = new ArrayList<>();
        powerUps = new ArrayList<>();
        healthBars = new ArrayList<>();
        setFocusTraversalKeysEnabled(false);
    }

    public void startGame() {
        tank1 = new Tank(100, 100, Color.GREEN);
        tank2 = new Tank(600, 400, Color.RED);
        gameRunning = true;
        initializeWalls();
        initializePowerUps();
        initializeHealthBars();
        timer.start();
    }

    private void initializeHealthBars(){
        healthBars.add(new HealthBar(tank1.getHealth(), 50, 50, Color.GREEN));
        healthBars.add(new HealthBar(tank2.getHealth(), 600, 50, Color.RED));
    }

    private void initializeWalls() {
        walls.add(new Wall(200, 200, 100, 20, true)); // Example unbreakable wall
        walls.add(new Wall(400, 300, 80, 80, false)); // Example breakable wall
    }

    private void initializePowerUps() {
        powerUps.add(new PowerUp(300, 300));
    }

    public void restartGame() {
        tank1 = new Tank(100, 100, Color.GREEN);
        tank2 = new Tank(600, 400, Color.RED);
        bullets.clear();
        walls.clear();
        powerUps.clear();
        gameRunning = true;
        initializeWalls();
        initializePowerUps();
        initializeHealthBars();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRunning) {
            tank1.draw(g);
            tank2.draw(g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            for (Wall wall : walls) {
                wall.draw(g);
            }
            for (PowerUp powerUp : powerUps) {
                powerUp.draw(g);
            }
            for (HealthBar healthBar : healthBars){
                healthBar.draw(g);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Game Over", getWidth() / 2 - 50, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            if (wPressed) tank1.moveForward();
            if (sPressed) tank1.moveBackward();
            if (aPressed) tank1.rotateLeft();
            if (dPressed) tank1.rotateRight();

            if (upPressed) tank2.moveForward();
            if (downPressed) tank2.moveBackward();
            if (leftPressed) tank2.rotateLeft();
            if (rightPressed) tank2.rotateRight();

            for (Bullet bullet : bullets) {
                System.out.println("Bullet Update");
                bullet.update();
            }

            checkCollisions();
            repaint();
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            boolean removed = false;

            for (int j = 0; j < walls.size(); j++) {
                Wall wall = walls.get(j);
                if (wall.getBounds().intersects(bullet.getBounds())) {
                    if (wall.isBreakable()) {
                        walls.remove(j);
                    }
                    removed = true;
                    break;
                }
            }

            if (removed) {
                bullets.remove(i);
                i--;
                continue;
            }

            if (bullet.getBounds().intersects(tank1.getBounds())) {
                tank1.takeDamage();
                bullets.remove(i);
                i--;
                continue;
            }

            if (bullet.getBounds().intersects(tank2.getBounds())) {
                tank2.takeDamage();
                bullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            if (tank1.getBounds().intersects(powerUp.getBounds())) {
                tank1.applyPowerUp(powerUp);
                powerUps.remove(i);
                i--;
            } else if (tank2.getBounds().intersects(powerUp.getBounds())) {
                tank2.applyPowerUp(powerUp);
                powerUps.remove(i);
                i--;
            }
        }

        if (tank1.getLives() <= 0 || tank2.getLives() <= 0) {
            gameRunning = false;
            timer.stop();
            // Show end screen or handle game over
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_S:
                sPressed = true;
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                break;
            case KeyEvent.VK_D:
                dPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                bullets.add(tank1.shoot());
                break;
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_ENTER:
                bullets.add(tank2.shoot());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_S:
                sPressed = false;
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                break;
            case KeyEvent.VK_D:
                dPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }
}
