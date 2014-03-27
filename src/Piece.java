
/**
 * @author A.Alakeel, Cameron Steer
 * @date 25 Feb '14
 * @brief Change colour of a Piece
 * @details This class can change the colour of a piece to any desired
 * colour. It stores the colour of a Piece, so that it can then
 * be used to get the colour of the
 * piece.
 *
 */

public class Piece {

	/** the colour of the piece */
	private String m_Colour;

	/**
	 * This method simply returns the colour of piece
	 *
	 * @return this.m_Colour returns the colour of type String
	 */
	public String getColour() {
		return m_Colour;
	}


	/**
	 * Technically a setColour method. Renamed changeColour to reflect
	 * its singular use in the Othello game.
	 *
	 * This method changes the colour of a piece
	 * and assigns it to attribute m_Colour
	 * @param colour assign this colour to m_Colour of the piece
	 * @return null
	 */
	public void changeColour(String colour) {
		m_Colour = colour;
	}

	/**
	 * This is a parametarized constructor of the class
	 * Piece which changes the colour of the piece passed
	 * to it.
	 *
	 * @param colour this is the colour to which the piece is to be changed
	 * @return null
	 */
	public Piece(String colour) {
		changeColour(colour);
	}
	//, String name, String playerType
}