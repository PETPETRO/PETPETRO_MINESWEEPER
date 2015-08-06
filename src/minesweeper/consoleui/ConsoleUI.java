package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.Minesweeper;
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
			openField();
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
		StringBuilder builder = new StringBuilder();
		Formatter formatter = new Formatter(builder);

		int x = Integer.toString(field.getColumnCount()).length();
		int y = 2;
		formatter.format(" ");
		for (int a = 0; a < columnCount; a++) {
			formatter.format("%" + (x + 1) + "s", a);
		}
		formatter.format("%n");
		for (int row = 0; row < rowCount; row++) {
			formatter.format("%" + y + "s", pismeno++);
			for (int column = 0; column < columnCount; column++) {
				formatter.format("%" + (x + 1) + "s", field.getTile(row, column));
			}
			formatter.format("%n");
		}
		System.out.print(builder);
		formatter.close();
		System.out.println(
				"\nX – ukoncenie hry \nMA1 – oznacenie dlaždice v riadku A a stlpci 1\nOB4 – odkrytie dlaždice v riadku B a stlpci 4");
		System.out.println("Pocet neoznacenych min je: " + field.getRemainingMineCount());
		System.out.println(Minesweeper.getInstance().getPlayingSeconds());
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		String input = readLine();
		try {
			handleInput(input);
		} catch (WrongFormatException ex) {
			ex.getMessage();
			System.err.println(ex);
		}
	}

	void handleInput(String input) throws WrongFormatException {
		input = input.toUpperCase();
		Pattern p = Pattern.compile("([OM])([A-Z])([0-9]+)");
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
				throw new WrongFormatException("Hodnota je mimo rozsahu pola");
			}
		} else if (input.length() == 1 && input.charAt(0) == 'X') {
			System.err.println("Ukoncil si hru");
			System.exit(0);
		} else {
			throw new WrongFormatException("Nespravny vstup");
		}
	}

}
