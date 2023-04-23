package GradinetWindow;

import java.awt.Color;
import java.awt.Graphics;

public class Marker {
    
    private int height;
    private int width;

    public Marker(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
    }

}
