package gui;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class DropDownButton extends ToggleButton {

    private ArrayList<ActiveButton> buttons = new ArrayList<>();


    public DropDownButton(int x, int y, int height, int width, Color color) {

        super(x, y, height, width, color);

    }

	public DropDownButton(String title, int x, int y, String depress_icon_path, String press_icon_path) {

        super(title, x, y, depress_icon_path, press_icon_path);
       
    }

    public boolean IsClicked(int x, int y)
	{
		if(isDrawed) {
			if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
			{
				pressed = true;
				released = false;				
				current_image = image_pressed;
				return true;
			}
			else {
				pressed = false;
				current_image = image_depressed;
				//isDrawed = false;				
			}
			return false;
		}
		return false;	
	}

	public boolean IsClikedShape(int x, int y) {

        if(isDrawed) {
			if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
			{
				pressed = true;
				released = false;				
				return true;
			}
			//this makes sures that the dropDown is unpressed when u click somewhere else
			else {
				pressed = false;
				//isDrawed = false;				
			}
			return false;
		}
		return false;

    }

    

    public void addComponents(ActiveButton button) {

        buttons.add(button);

    }

    public void drawComponentsImage(Graphics g, ImageObserver observer) {

        for (ActiveButton b : buttons) {
            b.drawButtonImage(g, observer);
        }

    }

    public void setComponentsDrawn(boolean x) {

        for (ActiveButton b : buttons) {
            b.setDrawn(x);
        }

    }
   
    public ArrayList<ActiveButton> getComponents() {
        return buttons;
    }
    
}
