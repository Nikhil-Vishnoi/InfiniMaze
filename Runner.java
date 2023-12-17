package Runner;


import GUI.*;
import Brain.*;
public class Runner {
	public static void main(String[] args) {
		Maze m = new Maze(30,30); 
//		m.Generate();
//		m.render();
		Screen screen = new Screen();
		MazePanel maze = new MazePanel(m, screen);
		ScreenQualities prop = new ScreenQualities(); 
		prop.xpos = 0; prop.ypos = 0; 
		prop.width = 700; prop.height = 700; 
		prop.name = "InfiniMaze"; prop.resizable = false; 
		screen.setView(maze);
		screen.create(prop);
	}
}
