package Brain;
/**
 * Utilizes sets to create a n by n maze with no cycles 
 * Starting position is defaulted to (0,0) 
 * Ending position is dependent on where the end of the maze is 
 * @author Nikhil
 *
 */
public class Maze {

	Block [][] maze; 
	DisjointSet set; 
	int width; 
	int height; 
	int pixelPerBlock; // How many pixels per block in maze 
	/**
	 * Constructor 
	 * @param h
	 * @param w
	 */
	public Maze(int h, int w) {
		this.width = w; 
		this.height = h; 
		maze = new Block[height][width]; 
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				maze[r][c] = new Block(); 
			}
		}
		set = new DisjointSet(height*width); 
	}
	/**
	 * Enlarge the Maze by some factor 
	 * @param factor
	 */
	public void enlarge(int factor) {
		width += factor; 
		height += factor; 
		maze = new Block[height][width];
	}
	/**
	 * Access a specific output 
	 * @param r
	 * @param c
	 * @return
	 */
	public Block access(int r, int c) {
		return maze[r][c]; 
	}
	/*
	 * Print out to terminal for visual debugging 
	 */
	public void render() {
		for (int i = 0; i < width*3; i++) {
			System.out.print("_"); 
		}
		System.out.print("\n");
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				Block b = maze[r][c]; 
				if(b.left == true) {
					System.out.print("|");
				}else {
					if (b.bottom == true) {
					    System.out.print(".");
					}else {
						System.out.print(" ");
					}
				}
				if(b.bottom == true) {
					System.out.print("_");
				}else {
					System.out.print(" ");
				}
				if(b.right == true) {
					System.out.print("|");
				}else {
					if (b.bottom == true) {
					    System.out.print(".");
					}else {
						System.out.print(" ");
					}				}
			}
			System.out.println();
		}
	}
	
	public void reset() {
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				maze[r][c] = new Block(); 
			}
		}
		set = new DisjointSet(height*width); 
	}
	/**
	 * Access the Maze 
	 * @return
	 */
	public Block[][] get() { return maze; }
	/*
	 * Uses a disjoint set to create a maze 
	 */
	public void Generate() {
		reset(); 
		// We will keep going until the map is fully connected 
		while (set.number_sets() > 1) {
			// Go through each of the blocks and randomly break blocks 
			for (int r = 0; r < height; r++) {
				for (int c = 0; c < width; c++) {
					int i = (int)(Math.random()*3);
					switch(i) {
					case 0:
						// Try to connect to the top 
						if (r-1 >= 0) {
							int ind1 = width*r + c;
							int ind2 = width*(r-1) + c; 
							if (set.connected(ind1, ind2) == false) {
								set.connect(ind1, ind2);
								maze[r-1][c].bottom = false;
								maze[r][c].top      = false; 
							}
						}
						break;
					case 1:
						// Try to connect the left 
						if (c-1 >= 0) {
							int ind1 = width*r + c;
							int ind2 = width*(r) + (c-1); 
							if (set.connected(ind1, ind2) == false) {
								set.connect(ind1, ind2);
								maze[r][c-1].right = false;
								maze[r][c].left      = false; 
							}
						}
						break;
					case 2:
						// Try to connect the right 
						if (c+1 < width) {
							int ind1 = width*r + c;
							int ind2 = width*(r) + c+1; 
							if (set.connected(ind1, ind2) == false) {
								set.connect(ind1, ind2);
								maze[r][c+1].left = false;
								maze[r][c].right  = false; 
							}
						}
						break;
					case 3:
						// Try to connect the bottom 
						if (r+1 < height) {
							int ind1 = width*r + c;
							int ind2 = width*(r+1) + c; 
							if (set.connected(ind1, ind2) == false) {
								set.connect(ind1, ind2);
								maze[r+1][c].top  = false;
								maze[r][c].bottom = false; 
							}
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// Enter Maze in the Top Left and Exit the Maze in the Bottom Right 
		maze[0][0].left = false; 
		maze[height-1][width-1].right = false; 
		
	}
}
