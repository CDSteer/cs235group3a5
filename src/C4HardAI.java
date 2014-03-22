import java.util.Random;

/**
 *
 * @file C4HardAI.java
 * @author Thomas Werner
 * @brief Allows a 'Hard' AI to generate moves in a game of Connect 4
 * @detail An AI that generates moves by attempting to extend existing lines of 2 or 3,
 * 	       or by randomly generating 2 numbers if none are available
 *
 */
public class C4HardAI {

	private final int BOARD_HEIGHT = 7;
	private final int BOARD_WIDTH = 10;
	private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;
	private final int EMPTY = 2;
	private final int VALID = 0;
	private final int INVALID = 1;
	private final String PLAYER_ONE_PIECE_COLOUR = "Red";
	private final String PLAYER_TWO_PIECE_COLOUR = "Yellow";

	private Random m_rand;
	private int m_selectedCol;
	private boolean m_validMove;
	private int[][] m_boardState = new int[BOARD_WIDTH][BOARD_HEIGHT];
	private int[] m_possibleMoves3;
	private int[] m_possibleMoves2;
	private boolean m_lineFound3;
	private boolean m_lineFound2;
	private boolean m_lineFoundAny;
	private Piece m_currentPiece;

	/*
	 * Not working yet! Theory is there though
	 */
	public int selectCol(ProgramController PC) {

		m_selectedCol = 0;
		m_possibleMoves3 = new int[BOARD_WIDTH];
		m_possibleMoves2 = new int[BOARD_WIDTH];
		m_lineFound3 = false;
		m_lineFound2 = false;
		m_lineFoundAny = false;
		m_validMove = false;

		// Trying as [ROW][COLUMN] first

		// Clears m_boardState
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				m_boardState[i][j] = EMPTY;
			}
		}

		// Calculate current board state and place in m_boardState
		// Will be used in deciding better moves for Hard AI
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				m_currentPiece = PC.getGame().getPiece(i, j);
				System.out.println("ERROR TESTING:");
				System.out.println("CO-ORDINATES: " + i + " " + j);
				System.out.println("PIECE COLOUR FOUND: " + m_currentPiece.getColour());
				if(m_currentPiece.getColour().equals("")) {
					m_boardState[i][j] = EMPTY;
				} else if (m_currentPiece.getColour().equals(PLAYER_ONE_PIECE_COLOUR)) {
					m_boardState[i][j] = PLAYER_ONE;
				} else if(m_currentPiece.getColour().equals(PLAYER_TWO_PIECE_COLOUR)) {
					m_boardState[i][j] = PLAYER_TWO;
				}
			}
		}

		if(extendCols() || extendRows() || extendRightDiag() || extendLeftDiag()) {
			m_lineFoundAny = true;
		}

		if(m_lineFound3 == true) {
			while(m_validMove == false) {
				m_rand = new Random();
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				if(m_possibleMoves3[m_selectedCol] == VALID) {
					m_validMove = true;
					return m_selectedCol;
				}
			}
		} else if(m_lineFound2 == true) {
			while(m_validMove == false) {
				m_rand = new Random();
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				if(m_possibleMoves2[m_selectedCol] == VALID) {
					m_validMove = true;
					return m_selectedCol;
				}
			}
		} else {
			while(m_validMove == false) {
				m_rand = new Random();
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				m_validMove = PC.getGame().checkValid(m_selectedCol, 0, PC.getGame().getPlayer(PLAYER_TWO));
			}
		}

		return m_selectedCol;


	}

	private boolean extendCols() {

		boolean movesFound = false;

		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 3; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i][j+1] == PLAYER_TWO &&
					   m_boardState[i][j+2] == PLAYER_TWO &&
					   m_boardState[i][j+3] == EMPTY) {

						m_possibleMoves3[j+3] = VALID;
						m_lineFound3 = true;
						movesFound = true;
					}

				}
			}
		}

		if(movesFound) { return true; }

		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 2; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i][j+1] == PLAYER_TWO &&
					   m_boardState[i][j+2] == EMPTY) {

						m_possibleMoves2[j+2] = VALID;
						m_lineFound2 = true;
						movesFound = true;
					}
				}
			}
		}

		if(movesFound) {
			return true;
		} else {
			return false;
		}
	}

	private boolean extendRows() {

		boolean movesFound = false;

		for(int i = 0; i < BOARD_WIDTH - 3; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j] == PLAYER_TWO &&
					   m_boardState[i+2][j] == PLAYER_TWO &&
					   m_boardState[i+3][j] == EMPTY) {

						m_possibleMoves3[j] = VALID;
						m_lineFound3 = true;
						movesFound = true;
					}

				}
			}
		}

		if(movesFound) { return true; }

		for(int i = 0; i < BOARD_WIDTH - 2; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j] == PLAYER_TWO &&
					   m_boardState[i+2][j] == EMPTY) {

						m_possibleMoves2[j] = VALID;
						m_lineFound2 = true;
						movesFound = true;
					}
				}
			}
		}

		if(movesFound) {
			return true;
		} else {
			return false;
		}

	}

	private boolean extendRightDiag() {

		boolean movesFound = false;

		for(int i = 0; i < BOARD_WIDTH - 3; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 3; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j+1] == PLAYER_TWO &&
					   m_boardState[i+2][j+2] == PLAYER_TWO &&
					   m_boardState[i+3][j+3] == EMPTY) {

						m_possibleMoves3[j+3] = VALID;
						m_lineFound3 = true;
						movesFound = true;
					}

				}
			}
		}

		if(movesFound) { return true; }

		for(int i = 0; i < BOARD_WIDTH - 2; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 2; j++) {
				if(m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j+1] == PLAYER_TWO &&
					   m_boardState[i+2][j+2] == EMPTY) {

						m_possibleMoves2[j+2] = VALID;
						m_lineFound2 = true;
						movesFound = true;
					}

				}
			}
		}

		if(movesFound) {
			return true;
		} else {
			return false;
		}

	}

	private boolean extendLeftDiag() {

		boolean movesFound = false;

		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 3; j++) {
				if(i >= 3 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i-1][j+1] == PLAYER_TWO &&
					   m_boardState[i-2][j+2] == PLAYER_TWO &&
					   m_boardState[i-3][j+3] == EMPTY) {

							m_possibleMoves3[j+3] = VALID;
							m_lineFound3 = true;
							movesFound = true;
					}
				}
			}
		}

		if(movesFound) { return true; }

		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT - 3; j++) {
				if(i >= 2 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i-1][j+1] == PLAYER_TWO &&
					   m_boardState[i-2][j+2] == EMPTY) {

							m_possibleMoves2[j+2] = VALID;
							m_lineFound2 = true;
							movesFound = true;
					}
				}
			}
		}

		if(movesFound) {
			return true;
		} else {
			return false;
		}

	}
}
