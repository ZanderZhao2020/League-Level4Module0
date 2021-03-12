package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] Pixels;
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		Pixels = new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < rows; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < cols; HeightLooper++) {
				Pixels[WidthLooper][HeightLooper] = new Pixel(WidthLooper, HeightLooper);
			}
		}
		for (int WidthLooper = 0; WidthLooper < rows; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < cols; HeightLooper++) {
				if (Math.sqrt(Math.pow(WidthLooper - rows / 2, 2) + Math.pow(HeightLooper - cols / 2, 2)) < 25) {
					Pixels[WidthLooper][HeightLooper].color = new Color(0, 0, 0);
				}
			}
		}
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		Pixels[(int) Math.floor(mouseX / pixelWidth)][(int) Math.floor(mouseY / pixelHeight)].color = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < rows; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < cols; HeightLooper++) {
				g.setColor(Pixels[WidthLooper][HeightLooper].color);
				g.fillRect(WidthLooper * pixelWidth, HeightLooper * pixelHeight, pixelWidth, pixelHeight);
			}
		}
	}
}
