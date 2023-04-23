package ToolBars;
import java.awt.Font;
import java.awt.Graphics;

import ShapesClasses.*;
import gui.PortionListener;

public abstract class Toolbar extends Rectangle implements PortionListener {

    protected Font font;

    public abstract void draw(Graphics g);
       
}
