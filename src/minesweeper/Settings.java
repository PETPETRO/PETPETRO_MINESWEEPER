package minesweeper;

import java.io.*;
import java.lang.Object;

import minesweeper.core.Field;

public class Settings implements Serializable {

	private final int rowCount;
	private final int columnCount;
	private final int mineCount;
	public static final Settings BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);
	private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
			+ "minesweeper.settings";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6902605351560444645L;

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Field && ((Field) o).getRowCount() == this.getRowCount()
				&& ((Field) o).getColumnCount() == this.getColumnCount()
				&& ((Field) o).getMineCount() == this.getMineCount()) {
			return true;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return columnCount * rowCount * mineCount;
	}

	public void save() throws IOException {

		FileOutputStream file = new FileOutputStream(SETTING_FILE);
		ObjectOutputStream object = new ObjectOutputStream(file);
		object.writeObject(this);
		object.close();

	}

	public static Settings load() throws ClassNotFoundException, IOException {
		FileInputStream input = null;
		try {
			input = new FileInputStream(SETTING_FILE);
		} catch (FileNotFoundException e) {
			e.getMessage();
			System.err.println(e);
			return BEGINNER;
		}
		ObjectInputStream object = new ObjectInputStream(input);
		return (Settings) object.readObject();
	}
}
