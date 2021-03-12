package _04_Maze_Maker;
import java.awt.Graphics;

public class Maze {
	//1. Create a 2D array of cells. Don't initialize it.
	Cell[][] Cells;
	private int width;
	private int height;

	public Maze(int w, int h) {
		this.width = w;
		this.height = h;

		//2. Initialize the cells using the width and height varibles
		Cells = new Cell[w][h];
		//3. Iterated through each cell and initialize it
		//   using i and j as the location
		int J;
		for (int I = 0; I < w; I++) {
			for (J = 0; J < h; J++) {
				Cells[I][J] = new Cell(I, J);
			}
		}
	}

	//4. This method iterates through the cells and draws them
	public void draw(Graphics g) {
		int J;
		for (int I = 0; I < this.width; I++) {
			for (J = 0; J < this.height; J++) {
				Cells[I][J].draw(g);
			}
		}
	}
	
	//4b. This method returns the selected cell.
	public Cell getCell(int x, int y){
		return this.Cells[x][y];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
