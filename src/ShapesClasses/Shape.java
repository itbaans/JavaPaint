package ShapesClasses;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape implements Serializable {

    protected int x;
    protected int y;
    protected Color strokeColor;
    protected Color fillColor;
    protected float strokeSize;

    public abstract void draw(Graphics g);
    public abstract void draw2D(Graphics2D g);
    public abstract String getInfo() ;
  
}
