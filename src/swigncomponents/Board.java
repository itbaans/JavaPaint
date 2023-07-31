package swigncomponents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import ToolBars.*;
import ToolBars.MenuBar;
import Windows.*;

public class Board extends JPanel
        implements ActionListener , MouseInputListener{

    private final int B_WIDTH = Dimensions.mainWindow_width;
    private final int B_HEIGHT = Dimensions.MainWindow_height;

    private final int DELAY = 10;
    private Timer timer;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean mouseDragging;
    private boolean mouseReleased;
    private MainWindow mainWindow;

    
    

    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            MenuBar bar = mainWindow.getMenuBar();

            if ((e.getKeyCode() == KeyEvent.VK_N) && (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)) {
                bar.getButtonFunctionality("new");
            }

            if ((e.getKeyCode() == KeyEvent.VK_S) && (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)) {
                bar.getButtonFunctionality("save");
            }

            if ((e.getKeyCode() == KeyEvent.VK_O) && (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)) {
                bar.getButtonFunctionality("open");
            }

            if ((e.getKeyCode() == KeyEvent.VK_Z) && (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)) {
                bar.getButtonFunctionality("undo");
            }

            if ((e.getKeyCode() == KeyEvent.VK_Y) && (e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK)) {
                bar.getButtonFunctionality("redo");
            }

        }
    }

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {
        
        mainWindow = new MainWindow(B_HEIGHT, B_WIDTH);
        

    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        mainWindow.draw(g);
        
        

    }

	@Override
	public void mouseClicked(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {
            mainWindow.onClick(e.getX(), e.getY());

            //System.out.println(e.getX()+" "+e.getY());
        }
	}


	// MOUSE LISTENERS
	@Override
	public void mousePressed(MouseEvent e) {
        
        if(SwingUtilities.isLeftMouseButton(e)) {
            mainWindow.onPress(e.getX(), e.getY());
            mainWindow.onClickDraw(e.getX(), e.getY(), getGraphics());
            x1 = e.getX();
            y1 = e.getY();
        }

	}

	@Override
	public void mouseReleased(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {
            mouseReleased = true;
            mouseDragging = false;
            mainWindow.onRelease(e.getX(), e.getY());
            x2 = e.getX();
            y2 = e.getY();
        }
        
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {
            mouseDragging = true;
            mouseReleased = false;
            mainWindow.onDrag(e.getX(), e.getY());
            mainWindow.onClickDrag(e.getX(), e.getY(), getGraphics());
            //System.out.println("board: "+e.getX()+" "+e.getY());
        }
        

	}

	@Override
	public void mouseMoved(MouseEvent e) {
        mainWindow.onMove(e.getX(), e.getY());
	}

	// refreshing
    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }
}