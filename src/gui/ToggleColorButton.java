package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


public class ToggleColorButton extends ToggleButton {

    public ToggleColorButton(String title,int x, int y, int height, int width, Color color) {

        super(x, y, height, width, color);
        this.title = title;
        
    }

    public void drawButtonShape(Graphics g) {
        
        isDrawed = true;
        if(pressed) {
            g.setColor(new Color(184, 214, 222));
            g.fillRect(x, y, width, height);
        }
        else {
            g.setColor(Color.lightGray);
            g.fillRect(x, y, width, height);
        }    
        g.setColor(color);
        g.fillRect(x+10, y+10, width-20, height-20);
        
    }

    public void drawButtonShapeWithText(Graphics g) {
        
        isDrawed = true;
        if(pressed) {
            g.setColor(new Color(184, 214, 222));
            g.fillRect(x, y, width, height);
        }
        else {
            g.setColor(Color.lightGray);
            g.fillRect(x, y, width, height);
        }    
        g.setColor(color);
        g.fillRect(x+10, y+10, width-20, height-20);

        Font font = new Font("Arial", Font.PLAIN, 13);
		g.setFont(font);
		g.setColor(Color.black);
		FontMetrics m = g.getFontMetrics();
		int s_wdith = m.stringWidth(this.title);
		int s_height = m.getAscent() - m.getDescent();
		g.drawString(this.title, x + width / 2 - s_wdith / 2, y + height / 2 + s_height / 2);
        
    }

    public void setTitle(String title) {
		this.title = title;
	}

    public String getTitle() {
		return title;
	}

    public Color getColor() {
        return color;
    }

	public void setColor(Color c) {
		color = c;
	}

}
