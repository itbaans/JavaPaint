package ShapesClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shape {

    private int[] xpoints;
    private int[] ypoints;
    private final int sides = 3;

    public Triangle(int[] xpoints, int[] ypoints, Color strokeColor, Color fillColor, float strokeSize) {

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

    @Override
    public void draw2D(Graphics2D g) {
        
        g.setStroke(new BasicStroke(strokeSize));
        g.setColor(strokeColor);
        g.drawPolygon(xpoints, ypoints, sides);

        g.setColor(fillColor);
        g.fillPolygon(xpoints, ypoints, sides);

    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
    
}
