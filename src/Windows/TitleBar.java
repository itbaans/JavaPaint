package Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ShapesClasses.Rectangle;
import gui.*;

public class TitleBar extends Rectangle {

    ActiveButton closeButton;
    private String title;

    public TitleBar(String title, int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        color = Color.darkGray;
        closeButton = new ActiveButton("Close", (x+width)-(int)(width*0.1), y, (int)(width*0.1), height, "src/titleBar Buttons/close.png", "src/titleBar Buttons/close.png");      
        
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString(title, x+10, y+(height/2+5));
    }

    public void drawButton(Graphics g) {
        closeButton.drawButtonImage(g, null);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
    
    
}
