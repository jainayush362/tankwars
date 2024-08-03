import java.awt.*;

public class Wall {
    private int x, y;
    private int width, height;
    private boolean breakable;
    
    public Wall(int x, int y, int width, int height, boolean breakable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.breakable = breakable;
    }
    
    public void draw(Graphics g) {
        g.setColor(breakable ? Color.GRAY : Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean isBreakable() {
        return breakable;
    }
}
