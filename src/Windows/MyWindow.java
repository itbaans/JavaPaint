package Windows;

import java.awt.Graphics;

import ShapesClasses.Rectangle;
import gui.*;

public class MyWindow extends Rectangle implements PortionListener {

    protected TitleBar tBar;

    public MyWindow() {
        
    }

    public MyWindow(int x, int y, int height, int width) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        tBar = new TitleBar("", x, y, width, (int)(height*0.1));

    }

    public void setTitle(String title) {
        tBar.setTitle(title);
    }

    @Override
    public void onClick(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClick'");
    }

    @Override
    public void onPress(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPress'");
    }

    @Override
    public void onRelease(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onRelease'");
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
