package ToolBars;

import java.awt.Graphics;
import gui.*;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import GradinetWindow.*;
import Windows.GradientWindow;

public class ColorBar extends Toolbar {
    
    private ToggleColorButton stroke_color;
    private ToggleColorButton fill_color;
    private ArrayList<ActiveColorButton> colorButtons = new ArrayList<>();
    private GradientWindow gW;
    private ActiveButton gWButton;
    private boolean windowOpen;
    private Color strokeColor;
    private Color fillColor;
    private boolean strokeClicked;
    private boolean fillClicked;


    public ColorBar() {

        height = 100;
        width = 1130;
        x = 150;
        y = 700;
        color = new Color(250, 243, 239);
        font = new Font("Arial", Font.PLAIN, 13);

        stroke_color = new ToggleColorButton("Stroke ",478, y+15, 40, 40, color.black);
        fill_color = new ToggleColorButton("fill Color", 776, 720, 40, 40, color.white);
        gW = new GradientWindow();
        stroke_color.SetPressed(true);
        strokeClicked = true;
        strokeColor = stroke_color.getColor();
        fillColor = fill_color.getColor();
        gWButton = new ActiveButton("gradient", 858, 720, 40, 40, "src/GradinetWindow/gradient.PNG", "src/GradinetWindow/gradient _press.PNG");

        int temp = 0;
        int temp2 = 0;    
        for (int i = 0; i < 20; i++) {
            colorButtons.add(new ActiveColorButton(526+temp, 720+temp2, 15, 15, new Color(rgbValues()[i][0], rgbValues()[i][1], rgbValues()[i][2])));
            temp += 25;
            if(i==9) {
                temp2+=25;
                temp = 0;
            }
            
            if(i==19) {
                temp = 0;
                temp2+=25;
                for (int j = 0; j < 10; j++) {
                    colorButtons.add(new ActiveColorButton(526+temp, 720+temp2, 15, 15));
                    temp +=25;
                }
            }
        }
    }

    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(x, y, width, height);

        stroke_color.drawButtonShape(g);
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(stroke_color.getTitle(), 476, 776);

        fill_color.drawButtonShape(g);
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(fill_color.getTitle(), 777, 776);

        gWButton.drawButtonImage(g, null);
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Edit Colors", 850, 776);

        for(ActiveColorButton b : colorButtons) {
            if(!b.isEmpty)
            b.drawButtonShape(g);
            else b.drawEmptyShape(g);
        }

        if(windowOpen)
        gW.draw(g);

    }

    private int[][] rgbValues() {
        int[][] rgbValues = {
            {255, 0, 0},       // Red
            {255, 165, 0},     // Orange
            {255, 255, 0},     // Yellow
            {0, 255, 0},       // Green
            {0, 128, 0},       // Dark Green
            {0, 0, 255},       // Blue
            {0, 0, 128},       // Dark Blue
            {128, 0, 128},     // Purple
            {255, 0, 255},     // Magenta
            {128, 128, 128},   // Gray
            {255, 255, 255},   // White
            {0, 0, 0},         // Black
            {255, 192, 203},   // Pink
            {255, 255, 224},   // Beige
            {0, 255, 255},     // Cyan
            {255, 140, 0},     // Dark Orange
            {128, 0, 0},       // Maroon
            {255, 218, 185},   // Peach
            {210, 180, 140},   // Tan
            {128, 128, 0}      // Olive
        };

        return rgbValues;
    }

    @Override
    public void onClick(int x, int y) {
        
        
    }

    @Override
    public void onPress(int x, int y) {

        if(stroke_color.IsClicked(x, y)){
            strokeClicked = true;
            fillClicked = false;
            fill_color.SetPressed(false);
        }
        if(fill_color.IsClicked(x, y)) {
            fillClicked = true;
            strokeClicked = false;
            stroke_color.SetPressed(false);
        }

        for (ActiveColorButton b : colorButtons) {
            if(b.IsClicked(x, y)) {
                if(fill_color.IsPressed()) 
                {
                   if(!b.isEmpty) {
                    fillColor = b.getColor();
                    fill_color.setColor(fillColor);
                   }
                }
                if(stroke_color.IsPressed()){
                    if(!b.isEmpty) {
                        strokeColor = b.getColor();
                        stroke_color.setColor(strokeColor);
                    }  
                }
            }
        }
        
        if(gWButton.IsClicked(x, y)) {
             windowOpen = true;
             gW.setWindowClose(false);
        }
        if(!gW.getWindowClose()) gW.onPress(x, y);
        
    }

    @Override
    public void onRelease(int x, int y) {

        gW.onRelease(x, y);
        if(gW.getWindowClose()) {
            int temp = 20;
            for(int i = 0; i < gW.getCustomColors().length; i++) {
                for(; temp < colorButtons.size();) {
                    if(colorButtons.get(temp).isEmpty && !(gW.getCustomColors()[i].isEmpty)) colorButtons.get(temp).setColor(gW.getCustomColors()[i].getColor());
                    temp++;
                    break;
                }
            }
            windowOpen = false; 
            gWButton.IsReleased();
        }
    }

    @Override
    public void onClickDraw(int x, int y, Graphics g) {
        if(!gW.getWindowClose())
        gW.onClickDraw(x, y, g);
    }

    

    @Override
    public void onDrag(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDrag'");
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public boolean getWindowState() {
        return windowOpen;
    }

    public boolean getstrokeClicked() {
        return strokeClicked;
    }
    
    public boolean getfillClicked() {
        return fillClicked;
    }

    @Override
    public void onMove(int x, int y) {
        
    }


}
