package gui;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Button 
{
	protected ToolTip toolTip;

	public abstract Boolean IsPressed();
		
	public abstract boolean IsClicked(int x, int y);

	public abstract void drawButtonImage(Graphics g, ImageObserver observer);

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract String getTitle();

	public abstract void setDrawn(boolean x);

	public abstract void drawToolTip(Graphics g);

}
