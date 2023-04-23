package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class LayerButton extends ToggleColorButton {

    private Color randColor;

    public LayerButton(String title, int x, int y, int height, int width, Color color) {
        super(title, x, y, height, width, color);
        randColor = new Color((int) (Math.random() * 256),(int) (Math.random() * 256),(int) (Math.random() * 256));
        //TODO Auto-generated constructor stub
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

        g.setColor(randColor);
        g.fillRect(x+width/3, y+height/2, 10, 10);

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

    
    
}
