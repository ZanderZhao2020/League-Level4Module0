package _01_Crazy_Digital_Painting;

import java.awt.Color;

public class CrazyDigitalPainting {
	//1. Create two final static integers for the width and height of the display.
	final static int Width = 2500;
	final static int Height = 1250;
	
	//2. Create a 2D array of Color objects. You will need to import
	//java.awt.Color. Initialize the size of the array using the 
	//integers created in step 1.
	Color[][] Colors = new Color[Width][Height];
	
	int Conv(double In) {
		return (int) Math.round(In) % 256; 
	}
	public CrazyDigitalPainting() {
		//3. Open the crazy_digital_painting.png file and look at the image.
		
		//4. Iterate through the 2D array and initialize each Color object
		//   to a new color. The sample image was created using the following 
		//   pattern:
		//   colors[i][j] = new Color(i % 256, (i * j) % 256, j % 256);
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Width; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Height; HeightLooper++) {
				Colors[WidthLooper][HeightLooper] = new Color(Conv(Math.sin(WidthLooper) * 127 + 128), Conv(Math.cos(WidthLooper * HeightLooper) * 127 + 128), Conv(Math.hypot(WidthLooper, HeightLooper)));
			}
		}
		//5. Come up with your own pattern to make a cool crazy image.
		
		//6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method 
		//   to show off your picture.
		ColorArrayDisplayer.displayColorsAsImage(Colors);
	}
	
	public static void main(String[] args) {
		new CrazyDigitalPainting();
	}
}
