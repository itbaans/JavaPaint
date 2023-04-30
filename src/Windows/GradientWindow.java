package Windows;

import GradinetWindow.HSVtoRGB;
import GradinetWindow.Marker;
import gui.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GradientWindow extends MyWindow {
    
    private ActiveColorButton[][] gradient = new ActiveColorButton[250][300];
    private ActiveColorButton[] basicColors = new ActiveColorButton[20];
    private ActiveColorButton[] customColors = new ActiveColorButton[15];
    private ActiveButton addColor;
    //marker class was experimental which didnt work as intended
    private Marker gMarker = new Marker(1, 1);
    private Marker cMarker = new Marker(20, 20);
    private Font font;
    private int R;
    private int G;
    private int B;
    private Color currentColor;
    private boolean windowClose;
    private static GradientWindow instance = new GradientWindow();


    private GradientWindow() {

        font = new Font("Arial", Font.PLAIN, 13);
        x = 416;
        y = 80;
        color = Color.lightGray;
        height = 500;
        width = 700;

       initializeGradient();
       initializeColors();
       
       tBar = new TitleBar("Edit Colors", x, y, width, (int)(height*0.1));
       addColor = new ActiveButton("Add Color", 495, 514, 70, 30, "src/GradinetWindow/addColor.png", "src/GradinetWindow/addColor_press.png");
       addColor.setListener(new ButtonListener() {
        @Override
        public void click(int x, int y) {     
            System.out.println("color added");
        }
        
       });
       currentColor = Color.white;  

    }

    public static GradientWindow getInstance() {
        return instance;
    }

    private void initializeGradient() {

        double saturation = 0;
        double hue = 0;

        int gHeight = gradient.length;
        int gWidth = gradient[0].length;
        
        for (int i = 0; i < gHeight; i++) {
            saturation = (1-((double)i/(double)gHeight));
            
            for (int j = 0; j < gWidth; j++) {

                //width at each iteration converting to hue value
                hue = ((double)j*360.0)/(double)gWidth;
                
                //i made a seperate class that contains the formula for converting HSV to RGB                
                HSVtoRGB f = new HSVtoRGB(hue, saturation);
                gradient[i][j] = new ActiveColorButton(j+750, i+188, 1, 1, new Color(f.r, f.g, f.b));
            }
            
        }

    }


    public void draw(Graphics g) {

        if(!windowClose) {
            g.setColor(color);
            g.fillRect(x, y, width, height);

            g.setColor(Color.white);
            g.fillRect(800, 475, 50, 30);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString("R: "+R, 805, 495);

            g.setColor(Color.white);
            g.fillRect(800+70, 475, 50, 30);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString("G: "+G, 805+70, 495);

            g.setColor(Color.white);
            g.fillRect(800+140, 475, 50, 30);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString("B: "+B, 805+140, 495);

            for (int i = 0; i < basicColors.length; i++) {
                basicColors[i].drawButtonShape(g);
            }

            for (int i = 0; i < customColors.length; i++) {
                if(customColors[i].isEmpty)
                customColors[i].drawEmptyShape(g);
                else customColors[i].drawButtonShape(g);
            }

            for (int i = 0; i < gradient.length; i++) {
                
                for (int j = 0; j < gradient[0].length; j++) {
                    gradient[i][j].drawButtonShape(g);
                }
            }

            tBar.draw(g);
            tBar.drawButton(g);
            addColor.drawButtonImageWithText(g, null);
            
            if(tBar.closeButton.getToolTipState()) tBar.closeButton.drawToolTip(g);
            if(addColor.getToolTipState()) addColor.drawToolTip(g);
            
            for (ActiveButton b : basicColors) {
                if(b.getToolTipState()) b.drawToolTip(g);
            }
            for (ActiveButton b : customColors) {
                if(b.getToolTipState()) b.drawToolTip(g);
            }

        }    
        
    }

    private void initializeColors() {

        int x = 465;
        int y = 191;
        int width = 20;
        int height = 20;
        Color[] colors = {new Color(230, 230, 250),new Color(255, 127, 80), new Color(0, 128, 128), new Color(230, 230, 250), new Color(128, 0, 0), new Color(128, 128, 0), new Color(106, 90, 205), new Color(160, 82, 45), new Color(70, 130, 180), new Color(238, 232, 170), new Color(189, 183, 107), new Color(183, 110, 121), new Color(188, 184, 138), new Color(204, 85, 0), new Color(0, 0, 128), new Color(142, 69, 133), new Color(135, 206, 235), new Color(255, 218, 185), new Color(255, 219, 88), new Color(64, 224, 208)};

        for (int i = 0; i < basicColors.length; i++) {

            basicColors[i] = new ActiveColorButton(x, y, height, width,colors[i]);
            basicColors[i].setToolTipContent("R:"+colors[i].getRed()+" G:"+colors[i].getGreen()+" B:"+colors[i].getBlue());          
            x += width+15;

            if (x-465 >= 170) {
                x = 465;
                y += height+15;
            }
        }

        y += 25;

        for (int i = 0; i < customColors.length; i++) {

            customColors[i] = new ActiveColorButton(x, y, height, width);            
            x += width+15;

            if (x-465 >= 170) {
                x = 465;
                y += height+15;
            }
        }
    }

    

    @Override
    public void onClick(int x, int y) {
        
             
    }
    
    @Override
    public void onPress(int x, int y) {
        if(addColor.IsClicked(x, y)) {
            addColor.getListener().click(x, y);
            for (int i = 0; i < customColors.length; i++) {
                    if(customColors[i].isEmpty) { 
                    customColors[i].setColor(currentColor);
                    customColors[i].setToolTipContent("R:"+currentColor.getRed()+" G:"+currentColor.getGreen()+" B:"+currentColor.getBlue());             
                    break;
                }
            }
        }

        if(tBar.closeButton.IsClicked(x, y)) {
            windowClose = true;          
        } 
    }

    @Override
    public void onRelease(int x, int y) {
        addColor.IsReleased();
    }
    //made this for marking a graphic as you click somewhere didnt work that well tho
    @Override
    public void onClickDraw(int x, int y, Graphics g) {

        for (int i = 0; i < gradient.length; i++) {
            for (int j = 0; j < gradient[0].length; j++) {
                if(gradient[i][j].IsClickedPixel(x, y)) {
                    gMarker.draw(g, x, y);
                    R = gradient[i][j].gColorButtonListner().redClick(x, y);
                    G = gradient[i][j].gColorButtonListner().greenClick(x, y);
                    B = gradient[i][j].gColorButtonListner().blueClick(x, y);
                    currentColor = gradient[i][j].getColor();
                    //System.out.println(r);
                }                 
            }
        }

        for (int i = 0; i < basicColors.length; i++) {
            if(basicColors[i].IsClicked(x, y)) {
                cMarker.draw(g, basicColors[i].x, basicColors[i].y);
                currentColor = basicColors[i].getColor();
            }
        }

    }

    public void onMove(int x, int y) {
        tBar.closeButton.setToolTipState(x, y);
        addColor.setToolTipState(x, y);
        for (ActiveColorButton b : basicColors) {
            b.setToolTipState(x, y);
        }
        for (ActiveColorButton b : customColors) {
            if(!b.isEmpty) b.setToolTipState(x, y);
        }
    }

    public void setWindowClose(boolean x) {
        windowClose = x;
    }

    public boolean getWindowClose() {
        return windowClose;
    }

    public ActiveColorButton[] getCustomColors() {
        return customColors;
    }


}
