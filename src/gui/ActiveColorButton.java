package gui;

import java.awt.Color;
import java.awt.Graphics;

public class ActiveColorButton extends ActiveButton {

    private ColorButtonListner colorListener;
    public boolean isEmpty;

    public ActiveColorButton(int x, int y, int height, int width, Color color) {
        
        super(x, y, height, width, color);
        colorListener = new ColorButtonListner() {

            @Override
            public int redClick(int x, int y) {
                return color.getRed();
            }

            @Override
            public int greenClick(int x, int y) {               
                return color.getGreen();
            }

            @Override
            public int blueClick(int x, int y) {               
                return color.getBlue();
            }
            
        };
    }

    public ActiveColorButton(int x, int y, int height, int width) {
        super(x, y, height, width);
        isEmpty = true;
    }

    public boolean IsClickedPixel(int x, int y) {

        if(isDrawed) {

			if(this.x == x && this.y == y)
			{
				pressed = true;
				released = false;				
				return true;
			}		
		}
		return false;

    }

    public void drawButtonShape(Graphics g) {
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
		isDrawed = true;

    }

	public void drawEmptyShape(Graphics g) {
        
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
		isDrawed = true;

    }

    

    public ColorButtonListner gColorButtonListner() {
        return colorListener;
    }

    public Color getColor() {
        return color;
    }

	public void setColor(Color c) {
		isEmpty = false;
		color = c;
	}

    public void drawToolTip(Graphics g) {
		if(isDrawed) {
        String rgb = color.getRGB()+"";
		toolTip.drawToolTip(g, rgb, x, y, width, height);
        }
	}


    
}
