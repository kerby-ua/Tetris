import java.awt.Color;
import java.awt.Graphics;


public class Glass {
	private int[] x = {0, 10, 10, 310, 310, 320, 320, 0};
	private int[] y = {0, 0, 600, 600, 0, 0, 610, 610};
	private int[][] cells = new int[21][10];
	public Glass() {
		clearGlass();
	}
	public void clearGlass() {
		for (int i = 0; i<cells.length; i++) {
			for (int j = 0; j<cells[i].length; j++) {
				cells[i][j] = (i==cells.length-1) ? 10 : 0;
			}
		}
	}
	public int getHeight() {
		return cells.length;
	}
	public int getWidth() {
		return cells[0].length;
	}
	public int getCell(int i, int j) {
		return cells[i][j];
	}
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(10,0,300,600);
	//	g.setColor(Color.BLACK);
		g.fillPolygon(x,y,x.length);
		for (int i = 0; i<cells.length-1; i++) {
			for (int j = 0; j<cells[i].length; j++) {
				drawCell(g,i,j);
			}
		}
	}
	public void drawCell(Graphics g, int i, int j) {
		g.setColor(Figure.COLORS[cells[i][j]]);
		g.fillRect(j*30+11,i*30+1,28,28);
	}
	public int acceptFigure(Figure f){
		for (int i=0; i<f.getHeight(); i++) {
			for (int j=0; j<f.getWidth(); j++) {
				int xx = f.getX()+j, yy = f.getY()+i;
				if (f.getCell(i,j)!=0) { // клетка не пуста
					cells[yy][xx] = f.getCell(i,j);
				}
			}
		}
		int lines = clearFullLines();
		if (lines>0) return lines;
		if (f.getY()==0) return -1;
		return 0;
	}
	private int clearFullLines() {
		int linesCount = 0;
		lineLoop: for (int i=1; i<20; i++){
			for (int j=0; j<10; j++) {	
				if (cells[i][j]==0) {
					continue lineLoop;
				}
			}
			linesCount++;
			for (int j=i; j>=1; j--) {
				cells[j]=cells[j-1];
			}
			cells[0] = new int[10];
			for (int j=0; j<10; j++) {
				cells[0][j]=0;
			}
		}
		return linesCount;
	}
	}
	