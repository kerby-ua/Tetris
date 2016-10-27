import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
public class mainPainter extends javax.swing.JPanel implements ActionListener {
	final static int PLAY = 1;
	final static int STOP = 2;
	final static int PAUSE =3;
	private mainRectangle stakan;
	private ourFigures fig = null;
	private int score = 0;
	private int gameState;
	private int[] DELAY = {500, 400, 300, 200, 150, 120, 100, 80, 60, 40};
	private int level = 1;
	Timer t = new javax.swing.Timer(DELAY[level], this);
public mainPainter() {
	startNewGame();
	t.start();
}
@Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	stakan.draw(g);
	fig.draw(g);
	g.setFont(new Font("Times New Roman", Font.BOLD, 24));
	g.setColor(Color.WHITE);
	g.drawString("Next figure: ", 410, 20);
	g.drawString("Score: " + score, 410, 200);
	g.drawString("Level: " + level, 410, 220);
}
public int getScore() {
	return score;
}
public void startNewGame() {
	score = 0;
	level = 0;
	fig = new ourFigures();
	stakan = new mainRectangle();
	gameState = PLAY;
}
public void pauseGame() {
	if (gameState == PLAY) {
		gameState = PAUSE;
	}
}
public void resumeGame() {
	if (gameState == PAUSE) {
		gameState = PLAY;
	}
}
public void stopGame() {
	gameState = STOP;
}
public int getState() {
	return gameState;
}
public void figureMoveRight(){
	if (fig.canRight(stakan)& gameState==PLAY)
		fig.moveRight();
}
public void figureMoveLeft(){
	if (fig.canLeft(stakan)& gameState==PLAY)
		fig.moveLeft();
}
public void figureMoveDown(){
	t.setDelay(DELAY[level]/10);
}
public void figureRotate(){
	if (fig.canRotate(stakan)& gameState==PLAY)
		fig.rotate();
}
public void gamePauseResume(){
	if (gameState==PLAY) {
		gameState = PAUSE;
	}
	else
		if (gameState==PAUSE) {
			gameState = PLAY;
		}
	}
public void gameScore() throws IOException{
	File file = new File("score.txt");
	 StringBuilder sb = new StringBuilder();
	   BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
           String s;
           while ((s = in.readLine()) != null) {
        	   sb.append(s);
               sb.append("\n");
           }
  JOptionPane.showMessageDialog(null, sb.toString(), "Leaders", JOptionPane.PLAIN_MESSAGE);
}
public static void checkLeaders(int score) throws IOException {
	File file = new File("score.txt");
	 StringBuilder sb = new StringBuilder();
	   BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
	  
          String s; String temp[];
          int i=0;
          boolean flag=false;
          temp=new String[10];
          while (((s = in.readLine()) != null)) {
       	   temp[i]=s;
       	   
       	   if (score>Integer.parseInt(temp[i].substring(temp[i].indexOf(" ")+1)))
       		   flag=true;
       	i++;  
          }
       
          String a=null;
          if (flag){
        	  a = (String)
        			  JOptionPane.showInputDialog(
                      null,
                      "Congratulations, \n"
                      + "you are in the list of leaders",
                      "Customized Dialog",
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      null,
                      "Your name");
        	  if (a!=null){
        		  a=a.replace(' ', '_');
        		  String buf=null;
        		  
        	  	for(i=0;i<temp.length;i++){
        	  	  if ((buf!=null)&&(i<temp.length)){
        	  		String buff=temp[i];
        	  		temp[i]=buf;
        	  		buf=buff;
          		  }
        	  	  else
        	  	  {}
        		  if ((score>Integer.parseInt(temp[i].substring(temp[i].indexOf(" ")+1)))&&(flag)){
        			  buf=temp[i];
        			  temp[i]=a+" "+score;
        			  flag=false;
        			  }
        		  else
        		  {}
        	  	}
        	  	BufferedWriter  out = new BufferedWriter(new FileWriter(file,false));
        	  	for(i=0;i<temp.length;i++){
        	  		out.write(temp[i]);
        	  		out.append('\n');
        	  	}
        	  	out.flush();
        	  }
          }
}
public void actionPerformed(ActionEvent e) {
	if (gameState != PLAY) {
		return;
	}

	if (fig.canDown(stakan)) {
		fig.moveDown();
	} else {
		int plus;
		if ((plus=stakan.acceptFigure(fig)) < 0) {
			stopGame();
			JOptionPane.showMessageDialog(null, "Your score: "+score, "Game over", JOptionPane.PLAIN_MESSAGE);
			try {
				checkLeaders(score);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else  {
			score+=plus*10;	
			int newLevel = (score )/ 50 + 1;
			if (newLevel > level && level < 9) {
				level++;
			}
		}
		if (gameState == PLAY) {
			fig = new ourFigures();
			t.setDelay(DELAY[level]);
		}
	}
	repaint();
	}
private void initComponents() {
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 400, Short.MAX_VALUE)
			);
	layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 300, Short.MAX_VALUE)
			);
	}
}
