import java.awt.*;

public class HealthBar {
    private int x, y;
    int health;
    private final int width = 100, height = 10;
    Color color;

    public HealthBar(int health, int x, int y, Color color) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g) {
        System.out.println(health);
        g.setColor(color);
        g.fillRect(x, y, health, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, health, height);
    }

    public void setHealth(int n){
        health = n;
    }

    public int getHealth(){
        return this.health;
    }
}
