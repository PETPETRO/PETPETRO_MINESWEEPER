package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;

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
		System.out.println("Vitaj: " + System.getProperty("user.name"));
		this.field = field;
		do {
			update();
			processInput();
		} while (field.getState() == GameState.PLAYING);
		if (field.getState() == GameState.SOLVED) {
			update();
			System.err.println("VYHRAL SI");
		} else if (field.getState() == GameState.FAILED) {
			openField();
			update();
			System.err.println("PREHRAL SI");
		}

	}

	private void openField() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int col = 0; col < field.getColumnCount(); col++) {
				field.openTile(row, col);
			}
		}
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
		char pismeno = 'A';

		int x = Integer.toString(field.getColumnCount()).length();
		int y = 2;
		// vypis oznacenia stlpcov (0,1,2,....)
		System.out.print(" ");
		for (int a = 0; a < columnCount; a++) {
			System.out.printf("%" + x + "s", a);
		}
		System.out.printf("\n");

		for (int row = 0; row < rowCount; row++) {
			System.out.printf("%" + y + "s", pismeno++);
			for (int column = 0; column < columnCount; column++) {
				System.out.printf("%" + x + "s", field.getTile(row, column));
			}
			System.out.print("\n");
		}
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.println(
				"X � ukoncenie hry \nMA1 � oznacenie dla�dice v riadku A a stlpci 1\nOB4 � odkrytie dla�dice v riadku B a stlpci 4");
		String input = readLine();
		input = input.toUpperCase();
		Pattern p = Pattern.compile("([OM])([A-Z])([0-9])");
		Matcher m = p.matcher(input);
		if (m.matches()) {
			if (m.group(1).charAt(0) == 'O' && m.group(2).charAt(0) - 'A' <= field.getRowCount()
					&& Integer.parseInt(m.group(3)) <= field.getColumnCount()) {
				int row = m.group(2).charAt(0) - 'A';
				int column = Integer.parseInt(m.group(3));
				field.openTile(row, column);
			} else if (m.group(1).charAt(0) == 'M' && m.group(2).charAt(0) - 'A' <= field.getRowCount()
					&& Integer.parseInt(m.group(3)) <= field.getColumnCount()) {
				int row = m.group(2).charAt(0) - 'A';
				int column = Integer.parseInt(m.group(3));
				field.markTile(row, column);
			} else {
				System.err.println("Nespravny vstup");
			}
		} else if (input.charAt(0) == 'X' && input.length() == 1) {
			System.err.println("Ukoncil si hru");
			System.exit(0);
		} else {
			System.err.println("Nespravny vstup");
		}

	}
}
