package Windows;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ToolBars.*;

public class MainWindow extends MyWindow {

    private ShapesBar shapesBar;
    private LayerBar layersBar;
    private ColorBar colorBar;
    private MenuBar menuBar;
    

    public MainWindow(int height, int width) {

        x = 0;
        y = 0;
        this.height = height;
        this.width = width;

        layersBar = new LayerBar();
        colorBar = new ColorBar();
        shapesBar = new ShapesBar();
        menuBar = new MenuBar();
    
    }

    public void draw(Graphics g) {
        
        g.setColor(color.white);
        g.fillRect(x, y, width, height);
    
        
        shapesBar.giveStack(layersBar.getStack());
        menuBar.setUndoRedo(layersBar.getStack(), layersBar.getUndoRedoStack());
        shapesBar.setColorBarStates(colorBar.getStrokeColor(), colorBar.getFillColor(), colorBar.getstrokeClicked(), colorBar.getfillClicked(), colorBar.getWindowState());
        shapesBar.menuBarStates(menuBar.getOpenWindowState());  

        Graphics2D g2D = (Graphics2D) g;
        layersBar.drawCurrentLayerShapes(g2D);
        shapesBar.drawDragShapes(g);
        layersBar.draw(g);
        g2D.setStroke(new BasicStroke(1));
        colorBar.draw(g);
        shapesBar.draw(g);
        menuBar.draw(g);       
    }

    @Override
    public void onClick(int x, int y) {

        shapesBar.onClick(x, y);
        layersBar.onClick(x, y);
        menuBar.onClick(x, y);

    }

    @Override
    public void onPress(int x, int y) {
        shapesBar.onPress(x, y);
        layersBar.onPress(x, y);
        colorBar.onPress(x, y);
    }

    @Override
    public void onRelease(int x, int y) {
        menuBar.onRelease(x, y);
        layersBar.onRelease(x, y);
        shapesBar.onRelease(x, y);
        colorBar.onRelease(x, y);
    }

    @Override
    public void onClickDraw(int x, int y, Graphics g) {
        colorBar.onClickDraw(x, y, g);
    }

    public void onDrag(int x, int y) {
        shapesBar.onDrag(x, y);
    }

    public void onClickDrag(int x, int y, Graphics g) {
        colorBar.onClickDraw(x, y, g);
    }

    public void onMove(int x, int y) {
        menuBar.onMove(x, y);
        shapesBar.onMove(x, y);
        colorBar.onMove(x, y);
        layersBar.onMove(x, y);
    }









    
}
