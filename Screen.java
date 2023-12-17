package GUI;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen {
	private JFrame screen; // The screen 
	private JPanel toShow; // JPanel that the screen will show 
	private boolean visible;
	/**
	 * Initilize the Screen 
	 */
	public Screen() {
		visible = false; 
		toShow = new JPanel(); 
	}
	/**
	 * Creates a standard screen
	 * @param name
	 * @param width
	 * @param height
	 */
	public void create(ScreenQualities recipe) {
		// Creates the JFrame using the user input 
		screen = new JFrame(recipe.name);
		screen.setSize(recipe.width,recipe.height);
		screen.setLocation(recipe.xpos,recipe.ypos);
		screen.setDefaultCloseOperation(recipe.closeOperation);
		screen.setResizable(recipe.resizable);
		screen.getContentPane().add(toShow, BorderLayout.CENTER); 
		screen.setVisible(true);
//		screen.requestFocus();
	}
	/**
	 * Refreshes the screen 
	 */
	public void refresh() {
		SwingUtilities.updateComponentTreeUI(screen);
		toShow.repaint(); 
	}
	/**
	 * Switch from one JPanel to another 
	 * @param newScreen
	 */
	public void switchView(JPanel newScreen) {
		screen.getContentPane().remove(toShow);
		screen.getContentPane().add(newScreen); 
		toShow = newScreen; 
		toShow.setFocusable(true);
//		toShow.addKeyListener((KeyListener) toShow); 
	}
	/**
	 * Set the view of the screen 
	 * @param image
	 */
	public void setView(JPanel image) {
		if(visible) switchView(image); 
		else toShow = image; 
		toShow.setFocusable(true);
//		toShow.addKeyListener((KeyListener) toShow); 

	}
}