package ToolBars;
import java.awt.Color;
import java.awt.Graphics;

import LayerEngineRecources.LayersEngine;
import gui.*;
import Windows.*;
import stackNqueueRecources.*;

public class MenuBar extends Toolbar {
    
    private DropDownButton fileMenu;
    private ActiveButton newButton;
    private ActiveButton openButton;
    private boolean openWindowState;
    private ActiveButton saveButton;

    private DropDownButton editMenu;
    ActiveButton undoButton;
    ActiveButton redoButton;
    private String undoRedoState = "";
    private LayersEngine outlayers;
    //private LayersEngine readLayers;
    FileWriting fWrite;

    private OpenWindow openWindow;

    public MenuBar() {

        fileMenu = new DropDownButton("FileMenu", 0, 0, "src/FileMenuIcons/square_depressed.png", "src/FileMenuIcons/square_pressed.png");
        fileMenu.setToolTipContent("File operations");
        newButton = new ActiveButton("New", 0, 60, "src/FileMenuIcons/temp1.png", "src/FileMenuIcons/temp1_pres.png");
        newButton.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("New clicked");
                if(outlayers != null) outlayers.refreshList();
                
            }
        });
        newButton.setToolTipContent("Open new file (Ctrl + N)");
        fileMenu.addComponents(newButton);

        openButton = new ActiveButton("Open", 45, 60, "src/FileMenuIcons/temp2.png", "src/FileMenuIcons/temp2_pres.png");
        openButton.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("Open clicked");
                openWindow = new OpenWindow(397, 100, 500, 600);
                 
            }
        });
        openButton.setToolTipContent("Open saved files (Ctrl + O)");
        fileMenu.addComponents(openButton);

        saveButton = new ActiveButton("Save", 90, 60, "src/FileMenuIcons/temp3.png", "src/FileMenuIcons/temp3_pres.png");
        saveButton.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("save clicked");
                if(outlayers != null)
                fWrite = new FileWriting(outlayers);
            }
        });
        fileMenu.addComponents(saveButton);
        saveButton.setToolTipContent("Save current file (Ctrl + S)");

        editMenu = new DropDownButton("EditMenu",60, 0, "src/EditMenuIcons/editMenu_dep.png", "src/EditMenuIcons/editMenu_pres.png");
        undoButton = new ActiveButton("Undo", 60, 60, "src/EditMenuIcons/Undo_dep.png", "src/EditMenuIcons/Undo_pres.png");
        undoButton.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("undo clicked");
                undoRedoState = "undo";
            }
        });
        undoButton.setToolTipContent("get back in the past without ur mistakes (Ctrl + Z)");
        editMenu.addComponents(undoButton);

        redoButton = new ActiveButton("Redo", 105,60, "src/EditMenuIcons/Redo_dep.png", "src/EditMenuIcons/Redo_pres.png");
        redoButton.setListener(new ButtonListener() {
            @Override
            public void click(int x, int y) {
                System.out.println("redo clicked");
                undoRedoState = "redo";
            }
        });
        redoButton.setToolTipContent("u are now realizing you were wrong about the thought that you were wrong (Ctrl + Y)");
        editMenu.addComponents(redoButton);

        color = new Color(160, 207, 202);
        width = 1500;
        height = 60;
    }


    
    public void draw(Graphics g) {
        
        g.setColor(color);
        g.fillRect(0, 0, width, height);

        fileMenu.drawButtonImage(g, null);
        
        if(fileMenu.IsPressed()) fileMenu.drawComponentsImage(g, null);
        else fileMenu.setComponentsDrawn(false);

        

        editMenu.drawButtonImage(g, null);
        if(editMenu.IsPressed()) editMenu.drawComponentsImage(g, null);
        else editMenu.setComponentsDrawn(false);

        if(openWindow != null) {
            openWindow.draw(g);           
            if(openWindow.getWindowClose()) openWindowState = false;
            else openWindowState = true;
        }

        if(fileMenu.getToolTipState()) fileMenu.drawToolTip(g);
        if(editMenu.getToolTipState()) editMenu.drawToolTip(g);

        for (ActiveButton b : fileMenu.getComponents()) {
            if(b.getToolTipState()) {
                b.drawToolTip(g);
                
            }
            
        }

        for (ActiveButton b : editMenu.getComponents()) {
            if(b.getToolTipState()) {
                b.drawToolTip(g);;             
            }
        }

           

    }

    public void setUndoRedo(Stack stack, Stack undoredo) {

        switch(undoRedoState) {
            case"undo":
            if(stack.stackSize()!=0)
            undoredo.push(stack.pop());
            undoRedoState = "";
            break;
            case"redo":
            if(undoredo.stackSize() != 0)        
            stack.push(undoredo.pop());
            undoRedoState = "";
            break;
        }

    }


    @Override
    public void onClick(int x, int y) {
        
        fileMenu.IsClicked(x, y);
        editMenu.IsClicked(x, y);

        for (ActiveButton b : fileMenu.getComponents()) {
            if(b.IsClicked(x, y)) {
                b.getListener().click(x, y);
                
            }
            
        }

        for (ActiveButton b : editMenu.getComponents()) {
            if(b.IsClicked(x, y)) {
                b.getListener().click(x, y);
                
            }
        }

        if(openWindow != null) {
            openWindow.onClick(x, y);
        }

    }

    @Override
    public void onPress(int x, int y) {
        
    }

    @Override
    public void onRelease(int x, int y) {
        
        for (ActiveButton b : fileMenu.getComponents()) {
            b.IsReleased();
        }

        for (ActiveButton b : editMenu.getComponents()) {
            b.IsReleased();
        }

    }

    @Override
    public void onClickDraw(int x, int y, Graphics g) {
        
    }
    
    public void getButtonFunctionality(String funtion) {

        funtion = funtion.toLowerCase();

        switch(funtion) {

            case"new":
            newButton.getListener().click(x, y);
            break;

            case"open":
            openButton.getListener().click(x, y);
            break;

            case"save":
            saveButton.getListener().click(x, y);
            break;

            case"undo":
            undoButton.getListener().click(x, y);          
            break;

            case"redo":
            redoButton.getListener().click(x, y);
            break;

        }
        
    }

    public boolean getOpenWindowState() {
        return openWindowState;
    }

    public void setOutLayers(LayersEngine layers) {
        this.outlayers = layers;
    }

    public LayersEngine getReadLayers() {
        if(openWindow != null)
        return openWindow.getReadLayers();
        else return null;
    }


    @Override
    public void onDrag(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDrag'");
    }



    @Override
    public void onMove(int x, int y) {

        fileMenu.setToolTipState(x, y);
        editMenu.setToolTipState(x, y);

        for (ActiveButton b : fileMenu.getComponents()) {
            b.setToolTipState(x, y);           
        }

        for (ActiveButton b : editMenu.getComponents()) {
            b.setToolTipState(x, y);
        }
        
        if(openWindow != null) {
            openWindow.onMove(x, y);
        }
    }
}
