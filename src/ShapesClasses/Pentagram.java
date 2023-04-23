package ShapesClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pentagram extends Shape {

    private int[] xpoints;
    private int[] ypoints;
    private final int sides = 10;
    
    public Pentagram(int[] xpoints, int[] ypoints, Color strokeColor, Color fillColor, float strokeSize) {

        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeSize = strokeSize;
     

    }
    

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    public void smallerPentagon(Graphics g) {

        int[] xp = new int[5];
        int[] yp = new int[5];
        int j = 0;
        for (int i = 0; i < 10; i++) {
            if(i % 2 != 0) {
            xp[j] = xpoints[i];
            yp[j] = ypoints[i];
            j++;
            }
        }
        g.setColor(strokeColor);
        g.drawPolygon(xp, yp, 5);
        g.setColor(fillColor);
        g.fillPolygon(xp, yp, 5);

    }

    @Override
    public void draw2D(Graphics2D g) {
        g.setStroke(new BasicStroke(strokeSize));
        g.setColor(strokeColor);
        g.drawPolygon(xpoints, ypoints, sides);

        g.setColor(fillColor);
        g.fillPolygon(xpoints, ypoints, sides);
        smallerPentagon(g);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
    
}
