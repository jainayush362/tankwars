import java.awt.*;

public class PowerUp {
    private int x, y;
    private final int width = 20, height = 20;
    
    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, width, height);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
