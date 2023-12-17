package GUI;

import Brain.Block;
import Brain.Maze;
import Player.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MazePanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Block[][] maze;
	Player p; 
	Screen host; 
	Maze mazegenerator; 
	int score; 
	/**
	 * Initilizing the MazePanel giving it a player, mazegenerator 
	 * used to run the whole game. 
	 * @param mazegenerator
	 * @param host
	 */
	public MazePanel(Maze mazegenerator, Screen host) {
		score = 0; 
		this.mazegenerator = mazegenerator;  
		mazegenerator.Generate();
		maze = mazegenerator.get(); 
		this.setBackground(Color.black);
		p = new Player(maze);		
		this.host = host; 
	    addKeyListener(this);
	}
	
	
	/**
	 * Paint Component for drawing the board and player 
	 */
	public void paintComponent(Graphics g) {
		Color azure = new Color(25, 150, 255);
		Color mintGreen = new Color(160, 255, 160);
		g.setColor(azure);
		g.fill3DRect(0, 0, 800, 800, getFocusTraversalKeysEnabled());
		g.setColor(Color.BLACK);
		g.drawString(("Score: "+score+"                      wasd to move, space to get new map, r to reset position"), 10, 15);
		g.setColor(Color.GRAY);
		// Draw our maze 
		int startx = 40; 
		int starty = 20; 
		for (int r = 0; r < maze.length; r++) {
			startx = 40; 
			for (int c = 0; c < maze[r].length; c++) {
				if (maze[r][c].top == true)
					g.fillRect(startx, starty, 20, 5);
				if (maze[r][c].left == true)
					g.fillRect(startx, starty, 5, 25);
				if (maze[r][c].bottom == true)
					g.fillRect(startx, starty+20, 20, 5);
				if (maze[r][c].right == true)
					g.fillRect(startx+20, starty, 5, 25);
				if ( r == p.getY() && c == p.getX() ) {
					g.setColor(mintGreen);
					g.fill3DRect(5+startx, 5+starty, 15, 15, getFocusTraversalKeysEnabled());
					g.setColor(Color.GRAY);

				}

				startx += 20; 
			}
			starty += 20; 
		}
		// Draw the Player 
	}
	
	@Override
	/**
	 * Player Movement 
	 */
	public void keyTyped(KeyEvent e) {
//		System.out.println("Key Typed " + e.getKeyChar());
		// TODO Auto-generated method stub
		try {
			char c = e.getKeyChar(); 
			if( c == 'w') p.moveUp();
			if( c == 'd') p.moveRight();
			if( c == 'a') p.moveLeft();
			if( c == 's') p.moveDown();
			if( c == 'r') {
				p.setX(0);
				p.setY(0); 
			}
			if( c == ' ') {
				p.setX(0);
				p.setY(0); 
				mazegenerator.Generate();
				maze = mazegenerator.get(); 
				p.updateMaze(maze);
			}
			host.refresh();
		}catch(Error error) {
			System.out.println("There was some Error\n"); 
		}
		
		if ( p.getX() >= maze.length) {
			score += maze.length-1; 
			mazegenerator.enlarge(1);
			p.setX(0);
			p.setY(0); 
			mazegenerator.Generate();
			maze = mazegenerator.get(); 
			p.updateMaze(maze);
		}
		// Max Maze Length 
		if ( maze.length >= 30) {
			mazegenerator.enlarge(-28);
			p.setX(0);
			p.setY(0); 
			mazegenerator.Generate();
			maze = mazegenerator.get(); 
			p.updateMaze(maze);	
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key Pressed " + e.getKeyChar()); 

		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("Key Released " + e.getKeyChar()); 

		// TODO Auto-generated method stub
		
	}

}
