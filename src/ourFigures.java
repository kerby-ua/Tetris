import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ourFigures {
final static byte[][][] PATTERN = {
	{{0,0,0,0}, 
	{0,0,0,0},
	{0,0,0,0},
	{0,0,0,0}
	},
	{
	{1},
	{1},
	{1},
	{1}
	},
	
	{{2,0},
	{2,0},
	{2,2}
	},
	
	{{0,3},
	{0,3},
	{3,3}
	},
	
	{{4,0},
	{4,4},
	{4,0}
	},
	
	{{5,0},
	{5,5},
	{0,5}
	},
	
	{{0,6},
	{6,6},
	{6,0}
	},
	
	{{7,7},
	{7,7}
	},
	
	{{8,8}
	},
	
	{{9,9},
	{9,0}
	},
	
	{{10,0},
	{0,10}
	},
	
	{{11}
	},
	
	{{12,12,12}
	},
	
	{{0,13,0},
	{13,0,13}
	},
	
};
static Color olive = new Color(128, 128, 0);
static Color teal= new Color(0, 128, 128);
static Color aqua= new Color(160, 5, 255);
static Color green= new Color(0, 128, 0);
static Color navy= new Color(40, 168, 128);
static Color maroon= new Color(128, 0, 0);

final static Color[] COLORS = {
	Color.darkGray,
	Color.BLUE,
	Color.CYAN,
	Color.GREEN,
	Color.PINK,
	Color.RED,
	Color.YELLOW,
	Color.MAGENTA,
	olive,
	teal,
	aqua,
	green,
	navy,
	maroon
};
private int type;
private int[][] pat;
private int x,y,rotation;
private static Random r = new Random();
private static int nextType = 0;
private static int nextRot = -1;
public ourFigures() {
	if (nextType==0) {
		type = r.nextInt(PATTERN.length-1)+1;
		rotation = r.nextInt(4);
	} else {
		type = nextType;
		rotation = nextRot;
	}
	nextType = r.nextInt(PATTERN.length-1)+1;
	y = 0;
	x = 4;
	nextRot = r.nextInt(4);
	pat = new int[PATTERN[type].length][PATTERN[type][0].length];
	for (int i=0; i<pat.length; i++)
		for (int j=0; j<pat[i].length; j++) {
			pat[i][j] = PATTERN[type][i][j];
		}
	for (int i=0; i<rotation; i++) 
		rotate();
}	
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public int getHeight() {
	return pat.length;
}
public int getWidth() {
	return pat[0].length;
}
public int getCell(int i, int j) {
	if (i<0 || i>=pat.length || j<0 || j>=pat[0].length) return 0;
		return pat[i][j];
}
public void draw(Graphics g) {
	for (int i=0; i<pat.length; i++) {
			for (int j=0; j<pat[i].length; j++)
				if (pat[i][j]!=0){
					g.setColor(COLORS[pat[i][j]]);
					g.fillRect((x+j)*30+11,(i+y)*30+1,28,28);
				}
	}
	drawNext(g,410,35);
}
public void drawNext(Graphics g, int px, int py) {
	int[][] p = new int[PATTERN[nextType].length][PATTERN[nextType][0].length];
	for (int i=0; i<p.length; i++) {
		for (int j=0; j<p[0].length; j++) {
			p[i][j] = PATTERN[nextType][i][j];
		}	
	}
	for (int kr=0; kr<nextRot; kr++){
		int[][] p2 = new int[p[0].length][p.length];
		for (int i=0; i<p.length; i++) {
			for (int j=0; j<p[0].length; j++) {
				p2[j][i] = p[i][j];
			}
		}
		p = new int[p2.length][p2[0].length];
		for (int i=0; i<p.length; i++) for (int j=0; j<p[0].length; j++) {
			p[i][j] = p2[p.length-i-1][j];
		}
	}
	//for (int i=0; i<4; i++) for (int j=0; j<4; j++) {
	//	g.setColor(COLORS[0]);
	//	g.fillRect(j*30+px, i*30+py, 28,28);
	//}
	for (int i=0; i<p.length; i++) for(int j=0; j<p[0].length; j++) {
		if(p[i][j]!=0){
		g.setColor(COLORS[p[i][j]]);
		g.fillRect(j*30+px, i*30+py, 28,28);}
	}
}	
public void rotate() {
	int[][] newPat = new int[pat[0].length][pat.length];
	for (int i=0; i<pat.length; i++) for (int j=0; j<pat[0].length; j++) {
		newPat[j][i] = pat[i][j];
	}
	pat = new int[newPat.length][newPat[0].length];
	for (int i=0; i<pat.length; i++) for (int j=0; j<pat[0].length; j++) {
		pat[i][j] = newPat[pat.length-i-1][j];
	}
}
public boolean canDown(mainRectangle stakan) {
	int[][] a = new int[stakan.maxLine][stakan.maxColon];
	for (int i = 0; i<stakan.getHeight(); i++) {
		for (int j = 0; j<stakan.getWidth(); j++) {
			a[i][j] = stakan.getCell(i,j);
		}
	}
	for (int i=0; i<pat.length; i++) {
		for (int j=0; j<pat[i].length; j++) {
			int xx = x+j, yy = y+i+1;
			if (pat[i][j]>0 && a[yy][xx]>0) {
				return false;
			}
		}
	}
	return true;
}
public void moveDown() {
	y++;
}
public boolean canLeft(mainRectangle stakan) {
	if (x==0) return false;
	int [][] s = new int[pat.length][pat[0].length];
	for (int i=0; i<s.length; i++) for (int j=0; j<s[0].length; j++) {
		s[i][j] = stakan.getCell(y+i,j+x-1);
	}
	for (int i=0; i<s.length; i++) for (int j=0; j<s[0].length; j++) {
		if (s[i][j]*pat[i][j]>0) {
			return false;
		}
	}
	return true;
}
public boolean canRight(mainRectangle stakan) {
	if (x==stakan.getWidth()-pat[0].length) return false;
	int [][] s = new int[pat.length][pat[0].length];
	for (int i=0; i<s.length; i++) for (int j=0; j<s[0].length; j++) {
		s[i][j] = stakan.getCell(y+i,j+x+1);
	}
	for (int i=0; i<s.length; i++) for (int j=0; j<s[0].length; j++) {
		if (s[i][j]*pat[i][j]>0) {
			return false;
		}
	}	
	return true;
}
public boolean canRotate(mainRectangle stakan) {
	if (x+pat.length>stakan.getWidth()) 
		return false;
	int[][] tmpPat = new int[pat[0].length][pat.length];
	for (int i=0; i<pat.length; i++) for (int j=0; j<pat[0].length; j++) {
		tmpPat[j][i] = pat[i][j];
	}
	int[][] tPat = new int[tmpPat.length][tmpPat[0].length];
	for (int i=0; i<tPat.length; i++) 
		for (int j=0; j<tPat[0].length; j++) {
			tPat[i][j] = tmpPat[tPat.length-i-1][j];
		}
	int [][] s = new int[tPat.length][tPat[0].length];
	for (int i=0; i<s.length; i++) 
		for (int j=0; j<s[0].length; j++) {
			s[i][j] = stakan.getCell(y+i,j+x);
		}
	for (int i=0; i<s.length; i++) for (int j=0; j<s[0].length; j++) {
		if (s[i][j]*tPat[i][j]>0) {
			return false;
		}
	}	
	return true;
}
public void moveLeft() {
	if (x>0) {
		x--;
	}
}
public void moveRight() {
	if (x<mainRectangle.maxLine-pat[0].length) {
		x++;
	}
	}
}