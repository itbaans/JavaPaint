package gui;

import java.awt.Color;
import java.awt.Graphics;

public class ActiveButton extends ToggleButton {

    public ActiveButton(String title, int x, int y, int width, int height, String depress_icon_path, String press_icon_path) {

        super(title, x, y, width, height, depress_icon_path, press_icon_path);
               
    }

    public ActiveButton(String title, int x, int y, String depress_icon_path, String press_icon_path) {

        super(title, x, y, depress_icon_path, press_icon_path);
               
    }

    public ActiveButton(int x, int y, int height, int width, Color color) {
        super(x, y, height, width, color);
        
    }

    public ActiveButton(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    public void IsReleased() {
		
		super.pressed = false;
		//super.released = true;
			
	}

    public void IsReleasedShape() {    

        super.pressed = false;
		super.released = true;
    }

    
    
}
