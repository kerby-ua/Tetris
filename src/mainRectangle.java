import java.awt.Color;
import java.awt.Graphics;


public class mainRectangle {
	final static int maxLine=21;
	final static int maxColon=12;
	
	private int[][] cells = new int[maxLine][maxColon];
	public mainRectangle() {
		clearGlass();
	}
	public void clearGlass() {
		for (int i = 0; i<cells.length; i++) {
			for (int j = 0; j<cells[i].length; j++) {
				cells[i][j] = (i==cells.length-1) ? maxColon : 0;
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
	//	g.setColor(Color.BLACK);
		for (int i=0; i<cells.length-1; i++) {
			for (int j=0; j<cells[i].length; j++)
//			if (pat[i][j]!=0){
//				g.setColor(COLORS[pat[i][j]]);
				g.fillRect((j)*30+11,(i)*30+1,28,28);
//			}
		}
//		g.fillRect(10,0,300,600);
	//	g.setColor(Color.BLACK);
	//	g.fillPolygon(x,y,x.length);
		for (int i = 0; i<cells.length-1; i++) {
			for (int j = 0; j<cells[i].length; j++) {
				drawCell(g,i,j);
			}
		}
	}
	public void drawCell(Graphics g, int i, int j) {
		g.setColor(ourFigures.COLORS[cells[i][j]]);
		g.fillRect(j*30+11,i*30+1,28,28);
	}
	public int acceptFigure(ourFigures f){
		for (int i=0; i<f.getHeight(); i++) {
			for (int j=0; j<f.getWidth(); j++) {
				int xx = f.getX()+j, yy = f.getY()+i;
				if (f.getCell(i,j)!=0) { // клетка не пуста
					cells[yy][xx] = f.getCell(i,j);
				}
			}
		}
		if (f.getY()==0) return -1;
		return clearFullLines();
	}
	private int clearFullLines() {
		int linesCount = 0;
		lineLoop: for (int i=1; i< maxLine-1; i++){
			for (int j=0; j<maxColon; j++) {	
				if (cells[i][j]==0) {
					continue lineLoop;
				}
			}
			linesCount++;
			for (int j=i; j>=1; j--) {
				cells[j]=cells[j-1];
			}
			cells[0] = new int[maxColon];
			for (int j=0; j<maxColon; j++) {
				cells[0][j]=0;
			}
		}
		return linesCount;
	}
	}
	