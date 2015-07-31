package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
<<<<<<< HEAD
	private UserInterface userInterface;
=======
	private ConsoleUI userInterface;
>>>>>>> origin/master

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		userInterface = new ConsoleUI();

		Field field = new Field(10, 10, 10);
<<<<<<< HEAD
		//
		// for(int i=0; i< 10;i++){
		// for(int b=0; b<10;b++){
		// field.openTile(i, b);
		// }
		// }
		userInterface.newGameStarted(field);
		// System.out.println(field.isSolved());
=======
		// userInterface.newGameStarted(field);
		field.openTile(1, 4);
		System.out.println(field);
>>>>>>> origin/master
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {

		new Minesweeper();

	}
}
