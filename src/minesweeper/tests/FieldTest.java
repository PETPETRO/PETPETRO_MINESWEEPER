package minesweeper.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import minesweeper.core.*;

public class FieldTest {
	static final int ROWS = 9;
	static final int COLUMNS = 9;
	static final int MINES = 10;
	static Field field;

	@Before
	public void setUp() {
		field = new Field(ROWS, COLUMNS, MINES);
	}

	@Test
	public void isSolved() {

		assertEquals(GameState.PLAYING, field.getState());

		int open = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					open++;
				}
				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} else {
					assertNotSame(GameState.FAILED, field.getState());
				}
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void generate() {

		Field field = new Field(ROWS, COLUMNS, MINES);
		int mineCount = 0;
		int clueCount = 0;
		assertEquals(ROWS, field.getRowCount());
		assertEquals(COLUMNS, field.getColumnCount());
		assertEquals(MINES, field.getMineCount());

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				assertNotNull("Dlazdica na pozicii[" + row + "][" + column + "] je null", field.getTile(row, column));
				if (field.getTile(row, column) instanceof Mine) {
					mineCount++;
				} else if (field.getTile(row, column) instanceof Clue) {
					clueCount++;
				}

			}

		}

		assertEquals(MINES, mineCount);
		assertEquals(ROWS * COLUMNS - MINES, clueCount);

	}

	@Test
	public void markTile() {

		Field field = new Field(ROWS, COLUMNS, MINES);
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				field.markTile(row, column);
				assertEquals(tile.getState(), Tile.State.MARKED);
				field.openTile(row, column);
				assertEquals(tile.getState(), Tile.State.MARKED);
				field.markTile(row, column);
				assertEquals(tile.getState(), Tile.State.CLOSED);

			}
		}

	}

	@Test
	public void openTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				field.openTile(row, column);
				assertEquals(tile.getState(), Tile.State.OPEN);
				field.markTile(row, column);
				field.openTile(row, column);
				assertEquals(tile.getState(), Tile.State.OPEN);
				field.openTile(row, column);
				assertEquals(tile.getState(), Tile.State.OPEN);
			}
		}
	}

	@Test
	public void countAdjacentMines() {

	}

	@Test
	public void openAdjacentTiles() {

	}

}
