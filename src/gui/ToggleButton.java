package gui;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;

public class ToggleButton extends Button {
    
    public int x;
	public int y;
	protected int width;
	protected int height;
	protected Image image_depressed;
	protected Image image_pressed;
	protected Image current_image;
	protected boolean pressed;
	protected String title;
	private Color titleColor;
	protected boolean isDrawed;
	protected boolean released;
	protected ImageIcon dep;
	protected ImageIcon pre;
	protected Color color;
	protected ButtonListener listener;
	protected boolean toolTipState;
	

    public ToggleButton(String title, int x, int y, String depress_icon_path, String press_icon_path) {

        this.x = x;
		this.y = y;
		this.title = title;
		
		dep = new ImageIcon(depress_icon_path);
        pre = new ImageIcon(press_icon_path);
		image_depressed = dep.getImage();
		image_pressed = pre.getImage();
		current_image = dep.getImage();
		image_depressed = dep.getImage();
		image_pressed = pre.getImage();

		try {
            BufferedImage img = ImageIO.read(new File(depress_icon_path));
            width = img.getWidth();
            height = img.getHeight();
        }
		catch (IOException e) {
            e.printStackTrace();
        }

		listener = new ButtonListener() {
			@Override
			public void click(int x, int y) {

			}
		};
		
		toolTip = ToolTip.getInstance();
		

    }

	public ToggleButton(String title, int x, int y, int width, int height, String depress_icon_path, String press_icon_path)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		dep = new ImageIcon(depress_icon_path);
        pre = new ImageIcon(press_icon_path);
		image_depressed = dep.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		image_pressed = pre.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		current_image = image_depressed;
		this.title = title;
		this.titleColor = Color.black;

		listener = new ButtonListener() {
			@Override
			public void click(int x, int y) {

			}
		};

		toolTip = ToolTip.getInstance();
		
	}

	public ToggleButton(int x, int y, int height, int width, Color color) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;

		toolTip = ToolTip.getInstance();
		

    }

	public ToggleButton(int x, int y, int height, int width) {

		this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
		toolTip = ToolTip.getInstance();
		

	}

    public boolean IsClicked(int x, int y)
	{
		if(isDrawed) {
			if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
			{
				pressed = true;
				released = false;				
				//current_image = image_pressed;
				return true;
			}			
		}
		return false;	
	}

	public void setListener(ButtonListener listener) {
		this.listener = listener;
	}

	public ButtonListener getListener() {
		return listener;
	}

    public Image GetImage() 
	{
		if(pressed == false) current_image = image_depressed;
		if(pressed == true) current_image = image_pressed;
		return current_image;
	}

    public void drawButtonImage(Graphics g, ImageObserver observer) {

		g.drawImage(GetImage(), x, y, observer );
		isDrawed = true;

	}

	public void drawButtonImageWithText(Graphics g, ImageObserver observer) {

		g.drawImage(GetImage(), x, y, observer);

		Font font = new Font("Arial", Font.PLAIN, 13);
		g.setFont(font);
		g.setColor(titleColor);
		FontMetrics m = g.getFontMetrics();
		int s_wdith = m.stringWidth(title);
		int s_height = m.getAscent() - m.getDescent();
		g.drawString(title, x + width / 2 - s_wdith / 2, y + height / 2 + s_height / 2);
		isDrawed = true;

	}

	public void setToolTipState(int x, int y) {

		if(isDrawed) {
			if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
				toolTipState = true;
			}
			else toolTipState = false;
		}
	}

	public boolean getToolTipState() {
		return toolTipState;
	}


    public Boolean IsPressed()
	{
		return pressed;
	}	
	
	public void SetPressed(boolean pressed)
	{
		this.pressed = pressed;
	}

    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public void setDrawn(boolean x) {
		isDrawed = x;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void drawToolTip(Graphics g) {
		if(isDrawed)
		toolTip.drawToolTip(g, title, x, y, width, height);
	}

	


	
}
