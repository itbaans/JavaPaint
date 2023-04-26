package ToolBars;

import java.util.ArrayList;

public class ClickStorage {
    
    private ArrayList<Integer> xpoints = new ArrayList<>();
    private ArrayList<Integer> ypoints = new ArrayList<>();

    public void addPoints(int x, int y) {
        xpoints.add(x);
        ypoints.add(y);
    }

    public void cleanStorage() {

        xpoints.clear();
        ypoints.clear();
        
    }

    public ArrayList<Integer> getXStorage() {
        return xpoints;
    }

    public ArrayList<Integer> getYStorage() {
        return ypoints;
    }

}
