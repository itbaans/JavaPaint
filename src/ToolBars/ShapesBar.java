package ToolBars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import gui.*;
import stackNqueueRecources.*;
import ShapesClasses.*;
import java.awt.Point;

public class ShapesBar extends Toolbar {
    
    private ArrayList<ToggleButton> shapeButtons = new ArrayList<>();
    private ToggleButton smallStroke;
    private ToggleButton mediumStroke;
    private ToggleButton largeStroke;
    private boolean shapeSelected;
    private String drawingState = "";
    private boolean gradientWindowState;
    private boolean openFolderState;
    private int clickX;
    private int clickY;
    private int dragX;
    private int dragY;
    private Stack stack;
    private boolean mouseReleased;
    private boolean mouseDragging;
    private Color strokeColor;
    private Color fillColor;
    private boolean strokeClicked;
    private boolean fillClicked;
    private boolean mouseClicked;
    private int strokeSize;
    private ClickStorage clickStorage = new ClickStorage();

    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Point controlPoint = new Point();
    private int clickCounter = 0;
    private BezierCurve bezierCurve;

    


    public ShapesBar() {

        this.x = 0;
        this.y = 60;
        this.width = 150;
        this.height = 800;
        color = new Color(223, 238, 221);
        strokeSize = 10;    
        intiializeButtons();

    }

    public void intiializeButtons() {

        int tempX = x+19;
        int tempY = y+60;

        ToggleButton circle = new ToggleButton("Circle", tempX, tempY, "src/Shapes/circle.png", "src/Shapes/circle_press.png");
        circle.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Circle selected");
                
                drawingState = "circle";

            }            
        });
        shapeButtons.add(circle);

        ToggleButton hexagon = new ToggleButton("Hexagon", tempX+76, tempY, "src/Shapes/hexagon.png", "src/Shapes/hexagon_press.png");
        hexagon.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Hexagon selected");
                drawingState = "hexagon";
            }            
        });
        shapeButtons.add(hexagon);

        ToggleButton bezierCurve = new ToggleButton("BezierCurve", tempX, tempY+=75, "src/Shapes/curve.png", "src/Shapes/curve_press.png");
        bezierCurve.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("BezierCurve selected");
                drawingState = "bezier curve";
            }            
        });
        shapeButtons.add(bezierCurve);

        ToggleButton rectangle = new ToggleButton("Rectangle", tempX+76, tempY, "src/Shapes/rectangle.png", "src/Shapes/rectangle_press.png");
        rectangle.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("rectangle selected");
                drawingState = "rectangle";
            }            
        });
        shapeButtons.add(rectangle);

        ToggleButton rigthTriangle = new ToggleButton("Right-Triangle", tempX, tempY+=75, "src/Shapes/right-triangle.png", "src/Shapes/right-triangle_press.png");
        rigthTriangle.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Right-Triangle selected");
                drawingState = "right-triangle";
            }            
        });
        shapeButtons.add(rigthTriangle);

        ToggleButton equiTriangle = new ToggleButton("Equi-Triangle", tempX+76, tempY, "src/Shapes/triangle.png", "src/Shapes/triangle_press.png");
        equiTriangle.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Equi-Triangle selected");
                drawingState = "equi-triangle";
            }            
        });
        shapeButtons.add(equiTriangle);
        ToggleButton freeDrawing = new ToggleButton("Free drawing", tempX, tempY+=75, "src/Shapes/pencil.png", "src/Shapes/pencil_press.png");
        freeDrawing.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Free drawing selected");
                drawingState = "free drawing";
            }            
        });
        shapeButtons.add(freeDrawing);
        ToggleButton pentaGram = new ToggleButton("Pentagram", tempX+76, tempY, "src/Shapes/pentagram.png", "src/Shapes/pentagram_press.png");
        pentaGram.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Pentagram selected");
                drawingState = "pentagram";
            }            
        });
        shapeButtons.add(pentaGram);

        smallStroke =  new ToggleButton("small stroke", 15, 750, 35, 35, "src/Shapes/stroke Widths/small.png", "src/Shapes/stroke Widths/small_press.png");
        mediumStroke = new ToggleButton("medium stroke", 55, 750, 35, 35, "src/Shapes/stroke Widths/medium.png", "src/Shapes/stroke Widths/medium_press.png");
        largeStroke = new ToggleButton("large stroke", 95, 750, 35, 35, "src/Shapes/stroke Widths/large.png", "src/Shapes/stroke Widths/large_press.png");
        
        smallStroke.SetPressed(true);

        font = new Font("Arial", Font.PLAIN, 20);

    }

    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Drawing Items", 10, 85);

        for (ToggleButton b : shapeButtons) {
            b.drawButtonImage(g, null);
            if(b.getToolTipState()) b.drawToolTip(g);
                           
        }

        smallStroke.drawButtonImage(g, null);
        mediumStroke.drawButtonImage(g, null);
        largeStroke.drawButtonImage(g, null);

        if(smallStroke.getToolTipState()) smallStroke.drawToolTip(g);
        if(mediumStroke.getToolTipState()) mediumStroke.drawToolTip(g);
        if(largeStroke.getToolTipState()) largeStroke.drawToolTip(g);


    }

    public void drawDragShapes(Graphics g) {

       if(isDraw()) {
        switch(drawingState) {

            case"circle":
                             
               if(mouseDragging) {
                   
                   //System.out.println(mouseClicked);
                   int xSqr = (int)Math.pow((double) dragX-clickX, 2);
                   int ySqr = (int)Math.pow((double) dragY-clickY, 2);

                   int diam = (int) Math.sqrt(ySqr+xSqr);
                   g.setColor(strokeColor);
                   g.drawOval(clickX-diam/2, clickY-diam/2,diam,diam);
                   g.setColor(Color.white);
                   g.fillOval(clickX-diam/2, clickY-diam/2,diam,diam);

               }
               
               
               if(mouseReleased && stack != null) {
                   
                   mouseDragging = false;
                   int xSqr = (int)Math.pow((double) dragX-clickX, 2);
                   int ySqr = (int)Math.pow((double) dragY-clickY, 2);
                   int diam = (int) Math.sqrt(ySqr+xSqr);
                   Circle circle = new Circle(diam, new Point(clickX, clickY), strokeColor, fillColor,strokeSize);
                   if(stack != null)
                   stack.push(circle);
                   System.out.println("oush");
                   mouseReleased = false;

               }               
               break;
        
            case"hexagon": 

               if(mouseDragging) {
                   
                   int[] xpoints = new int[6];
                   int[] ypoints = new int[6];
                   int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));

                   for (int i = 0; i < 6; i++ ) {

                       xpoints[i] = (int)(clickX + (distance*Math.cos((Math.toRadians(30+(60*i))))));
                       ypoints[i] = (int)(clickY - (distance*Math.sin((Math.toRadians(30+(60*i))))));
                       //System.out.println(xpoints[i]+" "+ypoints[i]);
           
                   }

                   g.setColor(strokeColor);
                   g.drawPolygon(xpoints, ypoints, 6);
                   g.setColor(Color.white);
                   g.fillPolygon(xpoints, ypoints, 6);
           
                   //System.out.println("**********");
                }

                if(mouseReleased && stack != null) {
                 
                       mouseDragging = false;
                       int[] xpoints = new int[6];
                       int[] ypoints = new int[6];
                       int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));

                       for (int i = 0; i < 6; i++ ) {

                        xpoints[i] = (int)(clickX + (distance*Math.cos((Math.toRadians(30+(60*i))))));
                        ypoints[i] = (int)(clickY - (distance*Math.sin((Math.toRadians(30+(60*i))))));
                           //System.out.println(xpoints[i]+" "+ypoints[i]);
               
                       }

                       Hexagon hexagon = new Hexagon(xpoints, ypoints, strokeColor, fillColor, strokeSize);
                       if(stack != null) stack.push(hexagon);
                       System.out.println("oush");
                       mouseReleased = false;
                }
                break;

            case"equi-triangle":
          
               if(mouseDragging) {
                   
                   int[] xpoints = new int[3];
                   int[] ypoints = new int[3];
                   int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));                  

                   for (int i = 0; i < 3; i++ ) {

                       xpoints[i] = (int)(clickX + (distance*Math.cos((Math.toRadians(90+(i*120))))));
                       ypoints[i] = (int)(clickY - (distance*Math.sin((Math.toRadians(90+(i*120))))));
                       //System.out.println(xpoints[i]+" "+ypoints[i]);
           
                   }

                   g.setColor(strokeColor);
                   g.drawPolygon(xpoints, ypoints, 3);
                   g.setColor(Color.white);
                   g.fillPolygon(xpoints, ypoints, 3);
           
                   //System.out.println("**********");
               }

               if(mouseReleased && stack != null) {
                 
                       mouseDragging = false;
                       int[] xpoints = new int[3];
                       int[] ypoints = new int[3];
                       int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));

                       for (int i = 0; i < 3; i++ ) {

                        xpoints[i] = (int)(clickX + (distance*Math.cos((Math.toRadians(90+(i*120))))));
                        ypoints[i] = (int)(clickY - (distance*Math.sin((Math.toRadians(90+(i*120))))));
                           //System.out.println(xpoints[i]+" "+ypoints[i]);
               
                       }

                       Triangle equiTriangle = new Triangle(xpoints, ypoints, strokeColor, fillColor, strokeSize);
                       if(stack != null) stack.push(equiTriangle);
                       System.out.println("oush");
                       mouseReleased = false;            
                }
                break;

            case"right-triangle":

                if(mouseDragging) {

                    int[] xpoints = new int[3];
                    int[] ypoints = new int[3];
              
                    int adj = Math.abs(dragX-clickX);
                    int opp = Math.abs(dragY-clickY);

                    xpoints[0] = clickX;
                    xpoints[1] = dragX;
                    if(clickX < dragX)
                    xpoints[2] = dragX-adj;
                    if(clickX > dragX)
                    xpoints[2] = dragX+adj;

                    ypoints[0] = clickY;
                    ypoints[1] = dragY;
                    if(clickY < dragY)
                    ypoints[2] = clickY+opp;
                    if(clickY > dragY)
                    ypoints[2] = clickY-opp;

                    g.setColor(strokeColor);
                    g.drawPolygon(xpoints, ypoints, 3);
                    g.setColor(Color.white);
                    g.fillPolygon(xpoints, ypoints, 3);

                }

                if(mouseReleased && stack != null) {
                    mouseDragging = false;
                    int[] xpoints = new int[3];
                    int[] ypoints = new int[3];
              
                    int adj = Math.abs(dragX-clickX);
                    int opp = Math.abs(dragY-clickY);

                    xpoints[0] = clickX;
                    xpoints[1] = dragX;
                    if(clickX < dragX)
                    xpoints[2] = dragX-adj;
                    if(clickX > dragX)
                    xpoints[2] = dragX+adj;

                    ypoints[0] = clickY;
                    ypoints[1] = dragY;
                    if(clickY < dragY)
                    ypoints[2] = clickY+opp;
                    if(clickY > dragY)
                    ypoints[2] = clickY-opp;

                    Triangle rightTriangle = new Triangle(xpoints, ypoints, strokeColor, fillColor, strokeSize);
                    if(stack != null) stack.push(rightTriangle);
                    System.out.println("oush");
                    mouseReleased = false;
                }
                break;

            case"pentagram":

                if(mouseDragging) {

                    int[] xpoints = new int[10];
                    int[] ypoints = new int[10];
                    int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));
                    int j = 0;
                    
                    for (int i = 0; i < 10; i++) {

                        int x;
                        int y;

                        if(i % 2 == 0) {
                            x = clickX + (int) (distance * Math.cos(Math.toRadians(18+(72*j))));
                            y = clickY - (int) (distance * Math.sin(Math.toRadians(18+(72*j))));
                        } else {
                            x = clickX + (int) ((distance/2) * Math.cos(Math.toRadians(54+(72*j))));
                            y = clickY - (int) ((distance/2) * Math.sin(Math.toRadians(54+(72*j))));
                            j++;
                        }

                        xpoints[i] = x;
                        ypoints[i] = y;

                    }

                    g.setColor(strokeColor);
                    g.drawPolygon(xpoints, ypoints, 10);
                    g.setColor(Color.white);
                    g.fillPolygon(xpoints, ypoints, 10);
               
                }

                if(mouseReleased && stack != null) {
                 
                    mouseDragging = false;
                    int[] xpoints = new int[10];
                    int[] ypoints = new int[10];
                    int distance = (int)Math.sqrt(Math.pow(dragX-clickX, 2) + Math.pow(dragX-clickX, 2));
                    int j = 0;

                    for (int i = 0; i < 10; i++ ) {

                        int x;
                        int y;

                        if(i % 2 == 0) {
                            x = clickX + (int) (distance * Math.cos(Math.toRadians(18+(72*j))));
                            y = clickY - (int) (distance * Math.sin(Math.toRadians(18+(72*j))));
                        } else {
                            x = clickX + (int) ((distance/2) * Math.cos(Math.toRadians(54+(72*j))));
                            y = clickY - (int) ((distance/2) * Math.sin(Math.toRadians(54+(72*j))));
                            System.out.println(j);
                            j++;
                        }

                        xpoints[i] = x;
                        ypoints[i] = y;
            
                    }

                    Pentagram pentagram = new Pentagram(xpoints, ypoints, strokeColor, fillColor, strokeSize);
                    if(stack != null) stack.push(pentagram);
                    System.out.println("oush");
                    mouseReleased = false;            
                }
                break;

            case"free drawing":
                              
                if(mouseDragging) {
                                        
                    g.setColor(strokeColor);
                    int i = 0;
                    while(i < clickStorage.getXStorage().size()) {
                    g.fillOval(clickStorage.getXStorage().get(i), clickStorage.getYStorage().get(i), (int)(strokeSize*0.8), (int)(strokeSize*0.8));
                    i++;
                    }
                    
                }

                if(mouseReleased && stack != null) {
                    mouseDragging = false;
                    FreeDrawing freeDrawing = new FreeDrawing(strokeColor, strokeSize);
                    for(int i = 0; i < clickStorage.getXStorage().size(); i++) {
                        //System.out.println("storage: "+clickStorage.getXStorage().get(i)+" "+clickStorage.getYStorage().get(i));
                        freeDrawing.addPoints(clickStorage.getXStorage().get(i), clickStorage.getYStorage().get(i));
                    }
                    mouseDragging = false;
                    if(stack != null) stack.push(freeDrawing);
                    System.out.println("oush");
                    clickStorage.cleanStorage();
                    mouseReleased = false; 
                }
                break;
            
            case"bezier curve":
                                
                if(bezierCurve != null) {
                    
                    if(clickCounter != 0) bezierCurve.draw(g);
                }
             
                break;

            case"rectangle": 

                if(mouseDragging) {

                    int width = Math.abs(dragX - clickX);
                    int height = Math.abs(dragY - clickY);
                    int xT = Math.min(clickX, dragX);
                    int yT = Math.min(clickY, dragY);
                    g.setColor(strokeColor);                    
                    g.drawRect(xT, yT, width, height);               
                    g.setColor(Color.white);
                    g.fillRect(xT, yT, width, height);
                    

                }

                if(mouseReleased && stack != null) {

                    mouseDragging = false;
                    int width = Math.abs(dragX - clickX);
                    int height = Math.abs(dragY - clickY);
                    int xT = Math.min(clickX, dragX);
                    int yT = Math.min(clickY, dragY);
                    Rectangle rectangle = new Rectangle(xT, yT, height, width, strokeColor, fillColor, strokeSize);
                    if(stack != null) stack.push(rectangle);
                    mouseReleased = false;
                }
                

            }
       }


    }

    @Override
    public void onClick(int x, int y) {

        //function for releasing all components except the pressed component
        for (int i = 0; i < shapeButtons.size(); i++) {
            if(shapeButtons.get(i).IsClicked(x, y)) {
                shapeButtons.get(i).getListener().click(x, y);
                shapeSelected = true;
                for(int j = 0; j < shapeButtons.size(); j++) {
                    if(j != i) shapeButtons.get(j).SetPressed(false);
                }            
            } 
        }

        if(smallStroke.IsClicked(x, y)) {
            strokeSize = 10;
            mediumStroke.SetPressed(false);
            largeStroke.SetPressed(false);
        }

        if(mediumStroke.IsClicked(x, y)) {
            strokeSize = 15;
            smallStroke.SetPressed(false);
            largeStroke.SetPressed(false);
        }

        if(largeStroke.IsClicked(x, y)) {
            strokeSize = 25;
            smallStroke.SetPressed(false);
            mediumStroke.SetPressed(false);
        }

    }

    @Override
    public void onPress(int x, int y) {

        
        if(canvasClicked(x, y) && isDraw()) {
                              
            if(drawingState.equals("bezier curve")) {
                clickCounter++;
                if(clickCounter == 1) {
                    startPoint.x = x;
                    startPoint.y = y;
                }
            }

            else {
                clickX = x;
                clickY = y;
                mouseReleased = false;
            }
            
        }
        
    }

    @Override
    public void onRelease(int x, int y) {

        if(canvasClicked(x, y) && isDraw()) {
                       
            if(drawingState.equals("bezier curve")) {
                if(clickCounter == 1) {
                    endPoint.x = x;
                    endPoint.y = y;
                    bezierCurve = new BezierCurve(strokeColor, strokeSize);
                }
                               
                bezierCurve.setInitialCordinates(startPoint, endPoint);        
                if(clickCounter == 2) {
                    controlPoint.x = x;
                    controlPoint.y = y;
                    bezierCurve.getControlPoints(controlPoint, clickCounter);
                }
                if(clickCounter == 3) {
                    controlPoint.x = x;
                    controlPoint.y = y;
                    bezierCurve.getControlPoints(controlPoint, clickCounter);
                    stack.push(bezierCurve);
                    clickCounter = 0;
                }
            }

            else {
                mouseReleased = true;
                mouseClicked = false;
            }
        }
        
    }

    @Override
    public void onClickDraw(int x, int y, Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickDraw'");
    }

    @Override
    public void onDrag(int x, int y) {
        if(canvasClicked(x, y) && isDraw()) {            
            if(drawingState.equals("bezier curve")) {
                if(clickCounter == 1) {
                    endPoint.x = x;
                    endPoint.y = y;
                    bezierCurve = new BezierCurve(strokeColor, strokeSize);
                }
                bezierCurve.setInitialCordinates(startPoint, endPoint);
                if(clickCounter == 2) {
                    controlPoint.x = x;
                    controlPoint.y = y;
                    bezierCurve.getControlPoints(controlPoint, clickCounter);
                }
                if(clickCounter == 3) {
                    controlPoint.x = x;
                    controlPoint.y = y;
                    bezierCurve.getControlPoints(controlPoint, clickCounter);
                }
            }
            
            //System.out.println("onDrag: "+dragX+" "+dragY);
            else {
                if(drawingState.equals("free drawing")) clickStorage.addPoints(dragX, dragY);              
                mouseDragging = true;
                dragX = x;
                dragY = y;
            }
        
        }
        
    }

    public void giveStack(Stack stack) {
        this.stack = stack;
    }

   

    public boolean canvasClicked(int x, int y) {
        if(x > Dimensions.canvas_x && x < Dimensions.canvas_x + Dimensions.canvas_width && y > Dimensions.canvas_y && y < Dimensions.canvas_y + Dimensions.canvas_height) {
            return true;
        }
        else return false;
    }

    public void setColorBarStates(Color strokeColor, Color fillColor, boolean strokeClicked, boolean fillClicked, boolean windowOpen) {

        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeClicked = strokeClicked;
        this.fillClicked = fillClicked;
        if(windowOpen) gradientWindowState = true;    
        else gradientWindowState = false;
    }

    public void menuBarStates(boolean openFolderState) {
        if(openFolderState) this.openFolderState = true;
        else this.openFolderState = false;
        
    }

    private boolean isDraw() {
        if(!openFolderState && !gradientWindowState && shapeSelected)
        return true;
        else return false;
    }

    @Override
    public void onMove(int x, int y) {
        
        for (ToggleButton b : shapeButtons) {
            b.setToolTipState(x, y);
        }

        smallStroke.setToolTipState(x, y);
        mediumStroke.setToolTipState(x, y);
        largeStroke.setToolTipState(x, y);

    }

    
}
