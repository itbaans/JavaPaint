package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ToolBars.ColorBar;
import Windows.GradientWindow;
import Windows.Grid;

public class GridButton extends ToggleButton {

    private Image offState;
    private Image state2x2;
    private Image state4x4;
    private Image state8x8;
    private Image state16x16;
    private Image state32x32;
    private Image state64x64;
    private Grid grid;
    private int clickX;
    private int clikcY;
    private Image currentState;
    private int currentType;


    public GridButton(int x, int y, int height, int width) {

        super(x, y, height, width);
        title = "grid off";
        ImageIcon off = (new ImageIcon("src/gridIcons/off.png"));
        ImageIcon set2 = (new ImageIcon("src/gridIcons/2x2.png"));
        ImageIcon set4 = (new ImageIcon("src/gridIcons/4x4.png"));
        ImageIcon set8 = (new ImageIcon("src/gridIcons/8x8.png"));
        ImageIcon set16 = (new ImageIcon("src/gridIcons/16x16.png"));
        ImageIcon set32 = (new ImageIcon("src/gridIcons/32x32.png"));
        ImageIcon set64 = (new ImageIcon("src/gridIcons/64x64.png"));

        offState = off.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state2x2 = set2.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state4x4 = set4.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state8x8 = set8.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state16x16 = set16.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state32x32 = set32.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        state64x64 = set64.getImage().getScaledInstance(width, height, Image.SCALE_FAST);;
        currentState = offState;
        currentType = 0;

        
        grid = Grid.getInstance();
      
    }

    public void draw(Graphics g, int gridClickCounter) {

        isDrawed = true;
        
        if(IsClicked(clickX, clikcY)) {

            if(gridClickCounter == 0) {
                currentState = offState;
                currentType = 0;
                toolTipContent = "grid off";
                
            }
            if(gridClickCounter == 1) {
                currentState = state2x2;
                currentType = 2;
                toolTipContent = "grid 2x2";
                
            }
            if(gridClickCounter == 2) {
                currentState = state4x4;
                currentType = 4;
                toolTipContent = "grid 4x4";
                
            }
            if(gridClickCounter == 3) {
                currentState = state8x8;
                currentType = 8;
                toolTipContent = "grid 8x8";
                
            }
            if(gridClickCounter == 4) {
                currentState = state16x16;
                currentType = 16;
                toolTipContent = "grid 16x16";
                
            }
            if(gridClickCounter == 5) {
                currentState = state32x32;
                currentType = 32;
                toolTipContent = "grid 32x32";
                
            }
            if(gridClickCounter == 6) {
                currentState = state64x64;
                currentType = 64;
                toolTipContent = "grid 64x64";               
            }

        }

        
        g.drawImage(currentState, x, y, null);
        grid.setType(currentType);
        if(!ColorBar.getWindowState())
            grid.drawGrid(g);
        

    }

    public void getClicks(int x, int y) {
        clickX = x;
        clikcY = y;
    }



    
    
}
