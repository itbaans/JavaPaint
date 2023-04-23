package ShapesClasses;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class Rectangle extends Shape {
    
    protected int height;
    protected int width;
    protected Color color;
    Random random = new Random();

    public Rectangle() {

    }

    public Rectangle(int x, int y, int height, int width) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw2D'");
    }


    
    
    
    
    

    

   
}
