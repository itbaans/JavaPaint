package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ToolTip {

    
    private Font font;
    
    private static ToolTip instance = new ToolTip();

    private ToolTip() {

        font = new Font("Arial", Font.PLAIN, 12);

    }

    public static ToolTip getInstance() {
        return instance;
    }

    

    public void drawToolTip(Graphics g, String data, int x, int y, int width, int height) {
        
        g.setColor(Color.cyan);
        int cellX = x+width;
        int cellY = y+height;
        FontMetrics m = g.getFontMetrics();
      
        //System.out.println(x+" "+(x+width));
		int textWidth = m.stringWidth(data);      
        int textHeight = m.getAscent() - m.getDescent();
        Dimension cellDimension = new Dimension(textWidth+2, m.getHeight()+2);
        g.fillRect(cellX, cellY, (int)cellDimension.getWidth(), (int)cellDimension.getHeight());
        g.setColor(Color.black);
        g.setFont(font);      
        g.drawString(data, cellX + (int)(cellDimension.getWidth()) / 2 - textWidth / 2, cellY + (int)(cellDimension.getHeight())/ 2 + textHeight / 2);
        

    }
}
