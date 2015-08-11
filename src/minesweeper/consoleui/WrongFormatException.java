package minesweeper.consoleui;

/**
 * Wrong format exception.
 */
class WrongFormatException extends Exception {
	private static final long serialVersionUID = 8417339198127187715L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            message
	 */
	public WrongFormatException(String message) {
		super(message);
	}
}