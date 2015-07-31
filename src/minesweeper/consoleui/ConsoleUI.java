package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.UserInterface;
import minesweeper.core.Field;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		// do {
		update();
		// processInput();
		// } while(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		int rowCount = field.getRowCount();
		int columnCount = field.getColumnCount();
		char pismeno1 = 'A';
		int x = Integer.toString(field.getColumnCount()).length();
		// int y = Integer.toString(field.getRowCount()).length();
		int y = 2;

		System.out.print(" ");
		for (int a = 0; a < columnCount; a++) {
			// System.out.print(" " + a);
			System.out.printf("%" + x + "s", a);
		}

		System.out.printf("\n");

		for (int row = 0; row < rowCount; row++) {
			System.out.printf("%" + y + "s", pismeno1);
			for (int column = 0; column < columnCount; column++) {
				// System.out.print(field.getTile(row, column));
				System.out.printf("%" + x + "s", field.getTile(row, column));
			}
			System.out.print("\n");
			pismeno1++;
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {

	}
}
