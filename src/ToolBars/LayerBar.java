package ToolBars;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.*;
import stackNqueueRecources.*;
import LayerEngineRecources.LayersEngine;

public class LayerBar extends Toolbar {
    
    private ActiveButton remove;
    private ActiveButton add;
    private ActiveButton uP;
    private ActiveButton down;

    //a custom list created for layers that creates,remove and swap up and down as functions
    private LayersEngine layers;

    public LayerBar() {

        remove = new ActiveButton("remove", 1280, 60,40,40, "src/icons/remove.png", "src/icons/remove_press.png");
        uP = new ActiveButton(null, 1340, 60, 40, 40, "src/icons/up.png", "src/icons/up_press.png");
        down = new ActiveButton(null, 1400, 60, 40, 40, "src/icons/down.png", "src/icons/down_press.png" );
        add = new ActiveButton(null, 1460, 60, 40, 40, "src/icons/add.png", "src/icons/add_press.png");
        layers = new LayersEngine(1280, 800, 50, 220);
        height = 740;
        width = 220;
        x = 1280;
        y = 60;
        layers.append();

    }

    public void draw(Graphics g) {

        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);

        remove.drawButtonImage(g, null);
        add.drawButtonImage(g, null);
        uP.drawButtonImage(g, null);
        down.drawButtonImage(g, null);
        layers.drawData(g);
    }

    public void drawCurrentLayerShapes(Graphics2D g) {
            
        layers.drawLayerElements(g);      
        
    }

    

    @Override
    public void onClick(int x, int y) {

        layers.buttonClicked(x, y);
        
    }

    public Stack getStack() {
        return layers.getStack();
    }

    public Queue getQueue() {
        return layers.getQ();
    }

    @Override
    public void onPress(int x, int y) {

        if(add.IsClicked(x, y)) layers.append();
        if(remove.IsClicked(x, y)) layers.remove();
        if(uP.IsClicked(x, y)) {
            if(layers.length() > 0) layers.moveUp();
        } 
        if(down.IsClicked(x, y)) {
            if(layers.length() > 0) layers.moveDown();
        } 
       
    }

    @Override
    public void onRelease(int x, int y) {
        
        add.IsReleased();
        remove.IsReleased();
        uP.IsReleased();
        down.IsReleased();

    }

    @Override
    public void onClickDraw(int x, int y, Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClickDraw'");
    }

    @Override
    public void onDrag(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDrag'");
    }

    @Override
    public void onMove(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMove'");
    }




}
