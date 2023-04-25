package ToolBars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import gui.*;
import stackNqueueRecources.*;
import ShapesClasses.*;
import java.awt.Point;

public class ShapesBar extends Toolbar {
    
    private ArrayList<ToggleButton> shapeButtons = new ArrayList<>();
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

    public ShapesBar() {

        this.x = 0;
        this.y = 60;
        this.width = 150;
        this.height = 800;
        color = new Color(223, 238, 221);
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

        ToggleButton pentagon = new ToggleButton("Pentagon", tempX, tempY+=75, "src/Shapes/pentagon.png", "src/Shapes/pentagon_press.png");
        pentagon.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Pentagon selected");
                drawingState = "pentagon";
            }            
        });
        shapeButtons.add(pentagon);

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
                   Circle circle = new Circle(diam, new Point(clickX, clickY), strokeColor, fillColor,15);
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

                       Hexagon hexagon = new Hexagon(xpoints, ypoints, strokeColor, fillColor, 15);
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

                       Triangle equiTriangle = new Triangle(xpoints, ypoints, strokeColor, fillColor, 10);
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

                    Triangle rightTriangle = new Triangle(xpoints, ypoints, strokeColor, fillColor, 10);
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

                    Pentagram pentagram = new Pentagram(xpoints, ypoints, strokeColor, fillColor, 10);
                    if(stack != null) stack.push(pentagram);
                    System.out.println("oush");
                    mouseReleased = false;            
                }
                break;

            }
       }


    }

    @Override
    public void onClick(int x, int y) {

        //function for releasing all components except the pressed component
        for (int i = 0; i < shapeButtons.size(); i++) {
            if(shapeButtons.get(i).IsClicked(x, y)) {
                shapeButtons.get(i).getListener().click(x, y);
                for(int j = 0; j < shapeButtons.size(); j++) {
                    if(j != i) shapeButtons.get(j).SetPressed(false);
                }            
            } 
        }

    }

    @Override
    public void onPress(int x, int y) {

        if(canvasClicked(x, y) && isDraw()) {         
            clickX = x;
            clickY = y;
            mouseReleased = false;
        }
        
    }

    @Override
    public void onRelease(int x, int y) {

        if(canvasClicked(x, y) && isDraw()) {
            mouseReleased = true;
            mouseClicked = false;
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
        mouseDragging = true;
        dragX = x;
        dragY = y;
        }
        
    }

    public void giveStack(Stack stack) {
        this.stack = stack;
    }

   

    public boolean canvasClicked(int x, int y) {
        if(x > CanvasDimensions.x && x < CanvasDimensions.x + CanvasDimensions.width && y > CanvasDimensions.y && y < CanvasDimensions.y + CanvasDimensions.height) {
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
        if(!openFolderState && !gradientWindowState)
        return true;
        else return false;
    }

    @Override
    public void onMove(int x, int y) {
        
        for (ToggleButton b : shapeButtons) {
            b.setToolTipState(x, y);
        }

    }

    
}
