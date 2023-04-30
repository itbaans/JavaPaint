package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import ToolBars.Dimensions;

public class ToolTip {

    
    private Font font;
    private Color color;
    
    private static ToolTip instance = new ToolTip();

    private ToolTip() {

        font = new Font("Arial", Font.PLAIN, 12);
        color = new Color(255, 164, 209);

    }

    public static ToolTip getInstance() {
        return instance;
    }

    

    public void drawToolTip(Graphics g, String data, int x, int y, int width, int height) {
        
        int cellX = x + width;
        int cellY = y + height;

        g.setFont(font);

        FontMetrics m = g.getFontMetrics();
        int textWidh = m.stringWidth(data);
        int textHeight = m.getHeight();

        int cellWidth = textWidh + 20;
        int cellHeight = textHeight + 20;

        if(cellWidth + cellX > Dimensions.mainWindow_width) {
            cellX = Dimensions.mainWindow_width - cellWidth;
        }

        if(cellHeight + cellY > Dimensions.MainWindow_height) {
            cellY = Dimensions.MainWindow_height - cellHeight;
        }

        
        g.setColor(color);
        g.fillRect(cellX, cellY, cellWidth, cellHeight);

        g.setColor(Color.black);
        
        g.drawString(data, cellX + 10, cellY+20);
        

    }
}
