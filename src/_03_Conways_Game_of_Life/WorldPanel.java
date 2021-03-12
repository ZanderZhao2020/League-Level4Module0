package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	Random RandGen = new Random();
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] Cells;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		this.cellSize = w / cpr;
		//3. Initialize the cell array to the appropriate size.
		Cells = new Cell[cpr][cpr];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				Cells[WidthLooper][HeightLooper] = new Cell(WidthLooper, HeightLooper, this.cellSize);
			}
		}
		
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				Cells[WidthLooper][HeightLooper].isAlive = RandGen.nextBoolean();
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				Cells[WidthLooper][HeightLooper].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				Cells[WidthLooper][HeightLooper].draw(g);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		int[][] liveOrDieBuffer = new int[Cells.length][Cells[0].length];
		//8. check if each cell should live or die
		int HeightLooper;
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				liveOrDieBuffer[WidthLooper][HeightLooper] = getLivingNeighbors(WidthLooper, HeightLooper);
			}
		}
		for (int WidthLooper = 0; WidthLooper < Cells.length; WidthLooper++) {
			for (HeightLooper = 0; HeightLooper < Cells[WidthLooper].length; HeightLooper++) {
				Cells[WidthLooper][HeightLooper].liveOrDie(liveOrDieBuffer[WidthLooper][HeightLooper]);
			}
		}
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	private boolean IsValidCell(int X, int Y) {
		return !(X < 0 || Y < 0 || X >= Cells.length || Y >= Cells[0].length);
	}
	public int getLivingNeighbors(int x, int y){
		int Total = 0;
		int HeightLooper;
		for (int WidthLooper = -1; WidthLooper < 2; WidthLooper++) {
			for (HeightLooper = -1; HeightLooper < 2; HeightLooper++) {
				if (!(WidthLooper == 0 && HeightLooper == 0)) {
					if (IsValidCell(x + WidthLooper, y + HeightLooper)) {
						if (Cells[x + WidthLooper][y + HeightLooper].isAlive) {
							Total++;
						}
					}
				}
			}
		}
		return Total;
	} 
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		Cells[(int) Math.floor(e.getX() / this.cellSize)][(int) Math.floor(e.getY() / this.cellSize)].isAlive = !Cells[(int) Math.floor(e.getX() / this.cellSize)][(int) Math.floor(e.getY() / this.cellSize)].isAlive;
		
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
