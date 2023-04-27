package ShapesClasses;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class Rectangle extends Shape {
    
    protected int height;
    protected int width;
    protected Color color;
    

    public Rectangle() {

    }

    public Rectangle(int x, int y, int height, int width, Color strokeColor, Color fillColor, float strokeSize) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeSize = strokeSize;
    }


    public void draw(Graphics g) {
       g.setColor(color);
       g.fillRect(x, y, width, height);
    }


    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        return "Rectangle "+x+" "+y+" "+height+" "+width;

    }

    @Override
    public void draw2D(Graphics2D g) {

        g.setStroke(new BasicStroke(strokeSize));
        g.setColor(strokeColor);
        g.drawRect(x, y, width, height);
        g.setColor(fillColor);
        g.fillRect(x, y, width, height);

    }


    
    
    
    
    

    

   
}
