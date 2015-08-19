package minesweeper.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import minesweeper.Settings;

public class SettingsTest {

	private int rowCount = 10;
	private int columnCount = 10;
	private int mineCount = 10;
	private Settings settings1 = new Settings(10, 10, 10);
	private Settings settings2 = new Settings(10, 10, 10);
	private Settings settings3 = new Settings(5, 5, 5);

	@Test
	public void Settings() {
		Settings setting = new Settings(rowCount, columnCount, mineCount);
		assertEquals(setting.getColumnCount(), 10);
		assertEquals(setting.getRowCount(), 10);
		assertEquals(setting.getMineCount(), 10);
	}

	@Test
	public void equalsTrue() {
		assertEquals(settings1.equals(settings2), true);
	}

	@Test
	public void equalsFalse() {
		assertEquals(settings1.equals(settings3), false);
	}

	@Test
	public void hashCodeTest() {
		assertEquals(1000, settings1.hashCode());
	}

}
