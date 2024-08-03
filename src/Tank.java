import java.awt.*;

public class Tank {
    private int x, y;
    private int width = 50, height = 30;
    private int speed = 5;
    private int health = 100;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    private int lives = 3;
    private Color color;
    private double angle = 0;
    
    public Tank(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(angle, x + width / 2, y + height / 2);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-angle, x + width / 2, y + height / 2); // Reset rotation
    }
    
    public void update() {
        // Update tank position, handle movement, etc.
    }
    
    public void moveForward() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }
    
    public void moveBackward() {
        x -= speed * Math.cos(angle);
        y -= speed * Math.sin(angle);
    }
    
    public void rotateLeft() {
        angle -= Math.PI / 16;
    }
    
    public void rotateRight() {
        angle += Math.PI / 16;
    }
    
    public Bullet shoot() {
        int bulletX = x + width / 2;
        int bulletY = y + height / 2;
        double bulletAngle = angle;
        System.out.println("Bullet is shooted");
        return new Bullet(bulletX, bulletY, bulletAngle);
    }
    
    public void takeDamage() {
        System.out.println("Damge");
        health -= 10;
        if (health <= 0) {
            lives--;
            health = 100;
        }
    }
    
    public void applyPowerUp(PowerUp powerUp) {
        // Apply the effect of the power-up
    }
    
    public int getLives() {
        return lives;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
