package ShapesClasses;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
/**
 * 
 * @author uakhan
 * This class creates a circle
 */
public class Circle extends Shape 
{
    private int		size;  
    private Point 	center;
    private Color 	color;
    

    /**
     * This is the Circle constructor
     * @param iSize	defines the size
     * @param location defines the location
     * @param C	defines the color
     */
    public Circle(int iSize, Point location, Color strokeColor, Color fillColor, float strokeSize)
    {
        setSize(iSize);
        setLocation(location);
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeSize = strokeSize;
        
    }

    void setSize(int iSize) {
        if (iSize > 1) {
            size = iSize;
        } else {
            size = 1;
        }
    }

    void setLocation(Point Pcenter) {
        center = Pcenter;
    }
    /**
     * 
     * @return returns the size of the circle
     */
    int getSize()
    {
        return size;
    }

    Point getCenter()
    {
        return center;
    }

    Color getColor()
    {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void draw2D(Graphics2D g)
    {
        g.setStroke(new BasicStroke(strokeSize));
        g.setColor(strokeColor);
        g.drawOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
        g.setColor(fillColor);
        g.fillOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
        
    }

    public void draw(Graphics g)
    {
        
        g.setColor(getColor());
        g.drawOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        return "Circle "+size+" "+center.x+" "+center.y;
    }

    
    
}