package gui;
import java.awt.Graphics;

public interface PortionListener {
    void onClick(int x, int y);

    void onPress(int x, int y);

    void onRelease(int x, int y);

    void onDrag(int x, int y);

    void onClickDraw(int x, int y, Graphics g);

    void onMove(int x, int y);
}
