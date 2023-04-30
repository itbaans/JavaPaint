package ShapesClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class BezierCurve extends Shape {

    private Point p0;
    private Point p1;
    private Point p2;
    private Point p3;  

    public BezierCurve() {
        strokeColor = Color.black;
    }

    public BezierCurve(Color strokeColor, float strokeSize) {
        this.strokeColor = strokeColor;
        this.strokeSize = (float)(strokeSize*0.5);
    }


    public void setInitialCordinates(Point p0, Point p3) {

        this.p0 = new Point(p0.x, p0.y);
        this.p3 = new Point(p3.x, p3.y);      

    }

    public void setStuff(Color strokeColor, float strokeSize) {
        this.strokeColor = strokeColor;
        this.strokeSize = (float)(strokeSize*0.5);
    }

    public void draw(Graphics g) {

        if(p0 != null && p3 != null && p1 == null && p2 == null) {
            //System.out.println(p0.x+" "+p3.x);
            linearBezier(g);
        }
        
        else if(p1 != null && p2 == null) {
            //System.out.println(p0.x+" "+p3.x);
            quadraticBezier(g);
        }
                
        
        else if(p1 != null && p2 != null) {
            //System.out.println("cubic");
            cubicBezier(g);                    
        }
        
        
        
        
    }

    public void draw2D(Graphics2D g) {
        
        g.setStroke(new BasicStroke(strokeSize));
        if(p0 != null && p3 != null && p1 == null && p2 == null) {
            //System.out.println("linera");
            linearBezier(g);
        }

        else if(p1 != null && p2 == null) {
            //System.out.println("quad");
            quadraticBezier(g);
        }
        

        else if(p1 != null && p2 != null) {
            //System.out.println("cubic");
            cubicBezier(g);
        }
        


    }

    private void linearBezier(Graphics g) {


        int bX = 0;
        int bY = 0;

        for (double t = 0; t <= 1; t+=0.001) {
            bX = (int)(p0.x * (1-t) + p3.x*t);
            bY = (int)(p0.y * (1-t) + p3.y*t);
            g.setColor(strokeColor);
            g.fillOval(bX, bY, (int)(strokeSize*0.8), (int)(strokeSize*0.8));
        }

    }

    private void quadraticBezier(Graphics g) {
        //(1-t)^2P0+2t(1-t)P1+t^2P2        
        int bX = 0;
        int bY = 0;

        for (double t = 0; t <= 1; t+=0.001) {

            bX = (int)(p0.x * Math.pow(t-1, 2) + 2*p1.x*t*(1-t) + p3.x*Math.pow(t, 2));
            bY = (int)(p0.y * Math.pow(t-1, 2) + 2*p1.y*t*(1-t) + p3.y*Math.pow(t, 2));
            g.setColor(strokeColor);
            g.fillOval(bX, bY, (int)(strokeSize*0.8), (int)(strokeSize*0.8));

        }

    }

    private void cubicBezier(Graphics g) {

        int bX = 0;
        int bY = 0;

        for (double t = 0; t <= 1; t+=0.001) {

            bX = (int)(p0.x * Math.pow((1-t), 3) + 3 * p1.x * t * Math.pow((1-t), 2) + 3 * p2.x * Math.pow(t, 2) * (1-t) + Math.pow(t, 3) * p3.x);
            bY = (int)(p0.y * Math.pow((1-t), 3) + 3 * p1.y * t * Math.pow((1-t), 2) + 3 * p2.y * Math.pow(t, 2) * (1-t) + Math.pow(t, 3) * p3.y);
            g.setColor(strokeColor);
            g.fillOval(bX, bY, (int)(strokeSize*0.8), (int)(strokeSize*0.8));

        }

    }

    public void getControlPoints(Point p, int clickCounter) {

        if(clickCounter == 2) {
            p1 = new Point();
            p1.x = p.x;
            p1.y = p.y;
            
        }
        else if(clickCounter == 3) {
            p2 = new Point();
            p2.x = p.x;
            p2.y = p.y;
        }       
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
   
}
