package Windows;
import java.awt.Color;
import java.awt.Graphics;

import ToolBars.Dimensions;

public class Grid {


    private int type;
    private boolean state;
    private static Grid instance = new Grid();

    private Grid() {
        state = false;
        type = 0;
    }


    public void drawGrid(Graphics g) {

        if(state) {
           
            int yPlus = 0;
            int xPlus = 0;
                for (int i = 0; i < Dimensions.canvas_height/type; i++) {
                    g.setColor(Color.black);
                    g.drawLine(Dimensions.canvas_x, Dimensions.canvas_y+yPlus, Dimensions.canvas_x+Dimensions.canvas_width, Dimensions.canvas_y+yPlus);
                    yPlus = yPlus + type;
                }
                for (int i = 0; i < Dimensions.canvas_width/type; i++) {
                    g.setColor(Color.black);
                    g.drawLine(Dimensions.canvas_x+xPlus, Dimensions.canvas_y, Dimensions.canvas_x+xPlus, Dimensions.canvas_y+Dimensions.canvas_height);
                    xPlus = xPlus + type;
                }                         
        }
       
    }

    public void setType(int type) {
        if(type == 0) state = false;
        else state = true;
        this.type = type;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public static Grid getInstance() {
        return instance;
    }



    
}
