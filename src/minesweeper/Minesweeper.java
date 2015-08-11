package minesweeper;

import java.io.IOException;

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
	private BestTimes bestTimes;
	private Settings setting;

	/**
	 * Constructor.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Minesweeper() throws ClassNotFoundException, IOException {
		instance = this;
		userInterface = new ConsoleUI();
		bestTimes = new BestTimes();
		setting = Settings.load();
		setSetting(setting);

		Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
		bestTimes.addPlayerTime("Fero", 145);
		bestTimes.addPlayerTime("Jano", 45);
		bestTimes.addPlayerTime("Mato", 78);
		bestTimes.addPlayerTime("Vlado", 3);
		System.out.println(bestTimes.toString());

		startMillis = System.currentTimeMillis();
		userInterface.newGameStarted(field);

	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		new Minesweeper();

	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public static Minesweeper getInstance() {
		return instance;

	}

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) throws IOException {
		this.setting = setting;
		setting.save();
	}

}
