package Windows;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import gui.*;

public class OpenWindow extends MyWindow {

    ArrayList <ToggleColorButton> buttons = new ArrayList<>();
    private boolean windowClose;
    private int count = 0;
    
    public OpenWindow(int x, int y, int height, int width ) {

        super(x, y, height, width);
        color = Color.gray;

        String directoryPath = "src/savedFiles";
        File directory = new File(directoryPath);

        //storing strings of file names in the given directory in a array
        File[] files = directory.listFiles();

        int buttonHeight = (int)(height*0.1);
        int tempY = tBar.getHeight()+tBar.getY();

        tBar.setTitle("Saved files");

        //max number of files visible
        if(count < 10) {
            for(File file: files) {

                //adding the file path name as string the buttons
                if(file.isFile() && file.getName().endsWith(".txt")) {
                   buttons.add(new ToggleColorButton(file.getName(), x, tempY , buttonHeight, width, Color.white));
                }
                tempY += buttonHeight;
            }
        }

    }

    public void draw(Graphics g) {

        if(!windowClose) {
            g.setColor(color);
            g.fillRect(x, y, width, height);

            tBar.draw(g);
            tBar.drawButton(g);
            if(tBar.closeButton.getToolTipState()) tBar.closeButton.drawToolTip(g);
            

            for (ToggleColorButton b : buttons) {
                b.drawButtonShapeWithText(g);
                if(b.getToolTipState()) b.drawToolTip(g);
            }
        }

    }

    public void onClick(int x, int y) {

        //function for releasing all components except the pressed component
        if(!windowClose) {        
            for (int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).IsClicked(x, y)) {
                    for(int j = 0; j < buttons.size(); j++) {
                        if(j != i) buttons.get(j).SetPressed(false);
                    }            
                } 
            }
        }

        if(tBar.closeButton.IsClicked(x, y)) windowClose = true;

    }

    public boolean getWindowClose() {
        return windowClose;
    }

    public void onMove(int x, int y) {
        tBar.closeButton.setToolTipState(x, y);
        for (ToggleColorButton b : buttons) {
            b.setToolTipState(x, y);
        }
    }
    
}
