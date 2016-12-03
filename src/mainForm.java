import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class mainForm extends javax.swing.JFrame {

	private javax.swing.JPanel buttonPanel;
	private javax.swing.JMenu jMenu;
	private javax.swing.JSeparator jSeparator;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem menuFileExit;
	private javax.swing.JMenuItem menuNewGame;
	private javax.swing.JMenuItem menuPauseGame;
	private mainPainter tetrisGamePanel;
	
	private javax.swing.JMenuItem menuSaveGame;
	private javax.swing.JMenuItem menuLoadGame;
	private javax.swing.JMenuItem menuScore;
	

public static void main(String[] args) throws InterruptedException {
	mainForm frame=new mainForm();
	frame.setVisible(true);
}
	
public mainForm() {
	initComponents();
	tetrisGamePanel.grabFocus();
}

private void initComponents() {
	buttonPanel = new JPanel();
	menuNewGame = new javax.swing.JMenuItem();
	tetrisGamePanel = new mainPainter();
	menuBar = new javax.swing.JMenuBar();
	jMenu = new javax.swing.JMenu();
	menuPauseGame = new javax.swing.JMenuItem();
	menuScore = new javax.swing.JMenuItem();
	menuLoadGame = new javax.swing.JMenuItem();
	menuSaveGame = new javax.swing.JMenuItem();
	jSeparator = new javax.swing.JSeparator();
	menuFileExit = new javax.swing.JMenuItem();
	this.setName("Tetris");
	//frame.setBackground(Color.BLACK);
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int sizeWidth = 555;
				
				int locationX = (screenSize.width - sizeWidth) / 2;
				int locationY = 0; 
				setBounds(locationX, locationY, sizeWidth, screenSize.height);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container fc =this.getContentPane();
				 buttonPanel.setBackground(Color.BLACK);
				fc.setBackground(Color.BLACK);
				tetrisGamePanel.setBackground(Color.BLACK);
				
				this.setResizable(false);
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	//setExtendedState(MAXIMIZED_BOTH);
	setFocusable(false);
	tetrisGamePanel.addKeyListener(new java.awt.event.KeyAdapter() {
		public void keyPressed(java.awt.event.KeyEvent evt) {
			tetrisGamePanel1KeyPressed(evt);
		}
	});
	menuBar.setFocusable(false);
	jMenu.setText("File");

	jMenu.setFocusable(false);
	jMenu.add(menuNewGame);
	
	menuNewGame.setText("Start New Game (R)");
	menuNewGame.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			menuNewGame1ActionPerformed(evt);
		}
	});
	jMenu.add(menuPauseGame);
		menuPauseGame.setText("Pause/Resume Game (P)");
		menuPauseGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuPauseGame1ActionPerformed(evt);
			}
		});
	jMenu.add(menuSaveGame);
		menuSaveGame.setText("Save Game");
		menuSaveGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					menuSave1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	jMenu.add(menuLoadGame);
		menuLoadGame.setText("Load Game");
		menuLoadGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					menuLoad1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
			
	jMenu.add(menuScore);
		menuScore.setText("Leaders");
		menuScore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					menuScore1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jMenu.add(jSeparator);
		menuFileExit.setText("Exit");
		menuFileExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuFileExit1ActionPerformed(evt);
			}
		});
		jMenu.add(menuFileExit);
		menuBar.add(jMenu);
		setJMenuBar(menuBar);
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(153, 153, 153)
										.addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(tetrisGamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(tetrisGamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		pack();
}
private void tetrisGamePanel1KeyPressed(java.awt.event.KeyEvent evt) {
	mainPainter p = (mainPainter)tetrisGamePanel;
	switch (evt.getKeyCode()) {
	case KeyEvent.VK_LEFT:
		p.figureMoveLeft();
		break;
	case KeyEvent.VK_RIGHT:
		p.figureMoveRight();
		break;
	case KeyEvent.VK_UP:
		p.figureRotate();
		break;
	case KeyEvent.VK_DOWN:
		p.figureMoveDown();
		break;
	case KeyEvent.VK_R:
		p.startNewGame();
		break;
	case KeyEvent.VK_P:
		p.gamePauseResume();
		break;
	default: return;
	}
	repaint();
}//GEN-LAST:event_tetrisGamePanel1KeyPressed
private void menuNewGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewGame1ActionPerformed
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.startNewGame();
}//GEN-LAST:event_menuNewGame1ActionPerformed
private void menuPauseGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPauseGame1ActionPerformed
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.gamePauseResume();
}//GEN-LAST:event_menuPauseGame1ActionPerformed
private void menuFileExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileExit1ActionPerformed
	System.exit(0);
}//GEN-LAST:event_menuFileExit1ActionPerformed

public void menuSave1ActionPerformed(ActionEvent evt) throws IOException {
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.gamePauseResume();
	p.gameSave();
	
}
public void menuLoad1ActionPerformed(ActionEvent evt) throws IOException {
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.gamePauseResume();
	p.gameLoad();

    
}
public void menuScore1ActionPerformed(ActionEvent evt) throws IOException {
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.gameScore();
}
public void menuFile1ActionPerformed(ActionEvent evt){
	mainPainter p = (mainPainter)tetrisGamePanel;
	p.gamePauseResume();
}
}
