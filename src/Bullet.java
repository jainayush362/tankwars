import java.awt.*;

public class Bullet {
    private int x, y;
    private int speed = 10;
    private double angle;
    private final int width = 10, height = 10;

    public Bullet(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void draw(Graphics g) {
        System.out.println("Bullet drwan");
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public void update() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public boolean isInBounds(int width, int height) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
