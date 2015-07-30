package minesweeper.core;

/**
 * Tile of a field.
 */
public abstract class Tile {

	/** Tile states. */
	public enum State {
		/** Open tile. */
		OPEN, /** Closed tile. */
		CLOSED, /** Marked tile. */
		MARKED
	}

	/** Tile state. */
	private State state = State.CLOSED;

	/**
	 * Returns current state of this tile.
	 * 
	 * @return current state of this tile
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets current current state of this tile.
	 * 
	 * @param state
	 *            current state of this tile
	 */
	void setState(State state) {
		this.state = state;
	}

	/**
	 * Returns string that represents state of current tile, "- " if CLOSED "M "
	 * if MARKED.
	 * 
	 * @return string that represents state of current tile
	 */
	@Override
	public String toString() {
		if (this.state == State.MARKED) {
			return "M ";
		} else if (this.state == State.CLOSED) {
			return "- ";
		}
		return null;

	}
}
