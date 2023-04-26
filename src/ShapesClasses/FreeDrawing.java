package ShapesClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class FreeDrawing extends Shape {

    ArrayList<Integer> xPoints = new ArrayList<>();
    ArrayList<Integer> yPoints = new ArrayList<>();

    public FreeDrawing(Color strokeColor, float strokeSize) {
        this.strokeColor = strokeColor;
        this.strokeSize = strokeSize;
    }

    public void addPoints(int x, int y) {

        xPoints.add(x);
        yPoints.add(y);

    }


    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void draw2D(Graphics2D g) {
        
        g.setColor(strokeColor);
        int i = 0;
        while(i < xPoints.size()) {
            g.fillOval(xPoints.get(i), yPoints.get(i), (int)(strokeSize*0.8), (int)(strokeSize*0.8));
            i++;
        }
        

    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
    
}
