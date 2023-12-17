/**
 * Java file for the Player
 * Stores its position for drawing. 
 */
package Player;

import Brain.Block;

public class Player {
	class Coordinate{
		int x; 
		int y; 
		public Coordinate(int x, int y) {
			this.x = x; 
			this.y = y; 
		}
		int getX() { return x; }
		int getY() { return y; } 
		void setX(int x) { this.x = x; } 
		void setY(int y) { this.y = y; } 
	}

	Coordinate pos; 
	// Its own copy of the map to figure out if it is making a legal move 
	Block[][] map; 
	/**
	 * 
	 * @param m
	 */
	public Player(Block[][] m) {
		this.map = m; 
		pos = new Coordinate(0,0); 
	}
	public void updateMaze(Block[][] newmaze) {
		this.map = newmaze; 
	}
	/**
	 * Making player move right 
	 * @return
	 */
	public boolean moveRight() {
		Block b = map[pos.getY()][pos.getX()]; 
		if ( b.right == true ) {
			return false; 
		}
		pos.setX(pos.getX()+1); 
		return true; 
	}
	/**
	 * Makes player move up 
	 * @return
	 */
	public boolean moveUp() {
		if(pos.getY() == 0) return false; 
		Block b = map[pos.getY()][pos.getX()]; 
		if ( b.top == true ) {
			return false; 
		}
		pos.setY(pos.getY()-1); 
		return true; 
	}
	/**
	 * Making player move left 
	 * @return
	 */
	public boolean moveLeft() {
		if (pos.getX() == 0) return false; 
		Block b = map[pos.getY()][pos.getX()]; 
		if ( b.left == true ) {
			return false; 
		}
		pos.setX(pos.getX()-1); 
		return true; 
	}
	/**
	 * Make player move down 
	 * @return
	 */
	public boolean moveDown() {
		Block b = map[pos.getY()][pos.getX()]; 
		if ( b.bottom == true ) {
			return false; 
		}
		pos.setY(pos.getY()+1); 
		return true; 
	}
	public int getX() { return pos.getX(); } 
	public int getY() { return pos.getY(); } 
	public void setX(int x) { pos.setX(x); }
	public void setY(int y) { pos.setY(y); }
	public void printPos() {System.out.print("("+pos.getX()+","+pos.getY()+")\n"); } 
}
