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
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import Windows.MainWindow;

public class Board extends JPanel
        implements ActionListener , MouseInputListener{

    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 800;

    private final int DELAY = 10;
    private Timer timer;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean mouseDragging;
    private boolean mouseReleased;
    private MainWindow myWindow;
    

    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }
    }

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {
        
        myWindow = new MainWindow(B_HEIGHT, B_WIDTH);

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
        
        myWindow.draw(g);
        

    }

	@Override
	public void mouseClicked(MouseEvent e) {
        myWindow.onClick(e.getX(), e.getY());
	}


	// MOUSE LISTENERS
	@Override
	public void mousePressed(MouseEvent e) {
        
        myWindow.onPress(e.getX(), e.getY());
        myWindow.onClickDraw(e.getX(), e.getY(), getGraphics());
        x1 = e.getX();
        y1 = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

        mouseReleased = true;
        mouseDragging = false;
        myWindow.onRelease(e.getX(), e.getY());
        x2 = e.getX();
        y2 = e.getY();
        
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

        mouseDragging = true;
        mouseReleased = false;
        myWindow.onDrag(e.getX(), e.getY());
        myWindow.onClickDrag(e.getX(), e.getY(), getGraphics());
        

	}

	@Override
	public void mouseMoved(MouseEvent e) {
        myWindow.onMove(e.getX(), e.getY());
	}

	// refreshing
    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }
}