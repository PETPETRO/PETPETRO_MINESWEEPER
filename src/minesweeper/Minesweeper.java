package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */

	private UserInterface userInterface;
	private long startMillis;
	private static Minesweeper instance;

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();

		Field field = new Field(10, 10, 10);

		startMillis = System.currentTimeMillis();
		userInterface.newGameStarted(field);

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

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public static Minesweeper getInstance() {
		return instance;

	}
}
