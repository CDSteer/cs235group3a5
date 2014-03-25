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
	private int[] m_blockColMoves;
	private int[] m_blockRowMoves;
	private boolean m_lineFound3;
	private boolean m_lineFound2;
	private boolean m_lineFoundAny;
	private Piece m_currentPiece;
	
	/*
	 * Not working yet! Theory is there though
	 */
	public int selectCol(ProgramController PC) {
		
		m_selectedCol = 0;
		m_rand = new Random();
		m_possibleMoves3 = new int[BOARD_WIDTH];
		m_possibleMoves2 = new int[BOARD_WIDTH];
		m_blockColMoves = new int[BOARD_WIDTH];
		m_blockRowMoves = new int[BOARD_WIDTH];
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
		
		for(int x = 0; x < BOARD_WIDTH; x++) {
			m_possibleMoves2[x] = INVALID;
			m_possibleMoves3[x] = INVALID;
			m_blockColMoves[x] = INVALID;
			m_blockRowMoves[x] = INVALID;
		}
		
		// Calculate current board state and place in m_boardState
		// Will be used in deciding better moves for Hard AI		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				m_currentPiece = PC.getGame().getPiece(i, j);
				if(m_currentPiece.getColour().equals("")) {				
					m_boardState[i][j] = EMPTY;
				} else if (m_currentPiece.getColour().equals(PLAYER_ONE_PIECE_COLOUR)) {
					m_boardState[i][j] = PLAYER_ONE;
				} else if(m_currentPiece.getColour().equals(PLAYER_TWO_PIECE_COLOUR)) {
					m_boardState[i][j] = PLAYER_TWO;
				}
			}
		}
		
		boolean m_blockPlayer = true;
		
		if(m_blockPlayer) {
			if(blockPlayerCols()) {
				while(m_validMove == false) {
					m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
					if(m_blockColMoves[m_selectedCol] == VALID) {
						m_validMove = true;
						return m_selectedCol;
					}
				}
			}
			
			if(blockPlayerRows()) {
				while(m_validMove == false) {
					m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
					if(m_blockRowMoves[m_selectedCol] == VALID) {
						m_validMove = true;
						System.out.println("ROW BLOCKED");
						return m_selectedCol;
					}
				}
			}
		}
		

		
		boolean examine1 = extendCols();
		boolean examine2 = extendRows();
		boolean examine3 = extendRightDiag();
		boolean examine4 = extendLeftDiag();
		
		if(examine1 || examine2 || examine3 || examine4) {
			m_lineFoundAny = true;
		}
		
		if(m_lineFound3 == true) {
			System.out.println("Line of 3 found!");
			while(m_validMove == false) {
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				if(m_possibleMoves3[m_selectedCol] == VALID) {
					m_validMove = true;
					return m_selectedCol;
				}
			}
		} else if(m_lineFound2 == true) {
			System.out.println("Line of 2 found!");
			while(m_validMove == false) {
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				if(m_possibleMoves2[m_selectedCol] == VALID) {
					m_validMove = true;
					return m_selectedCol;
				}
			}
		} else {
			System.out.println("No Line Found!");
			while(m_validMove == false) {
				m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
				m_validMove = PC.getGame().checkValid(m_selectedCol, 0, PC.getGame().getPlayer(PLAYER_TWO));
			}
		}
		
		return m_selectedCol;
		
		
	}
	
	private boolean extendCols() {
		
		boolean movesFound = false;
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(j >= 3 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i][j-1] == PLAYER_TWO &&
					m_boardState[i][j-2] == PLAYER_TWO &&
					m_boardState[i][j-3] == EMPTY) {
						
						m_possibleMoves3[i] = VALID;
						m_lineFound3 = true;
						movesFound = true;
					}
					
				}
			}
		}
		
		if(movesFound) { return true; }
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(j >= 3 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i][j-1] == PLAYER_TWO &&
					m_boardState[i][j-2] == EMPTY) {
						
						m_possibleMoves2[i] = VALID;
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
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(i < BOARD_WIDTH - 3 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j] == PLAYER_TWO &&
					m_boardState[i+2][j] == PLAYER_TWO &&
					m_boardState[i+3][j] == EMPTY) {
						
						m_possibleMoves3[i+3] = VALID;
						if(i >=1 && m_boardState[i-1][j] == EMPTY) {
							m_possibleMoves3[i-1] = VALID;
						}
						m_lineFound3 = true;
						movesFound = true;
					}
					
				}
			}
		}
		
		if(movesFound) { return true; }
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(i < BOARD_WIDTH - 2 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j] == PLAYER_TWO &&
					m_boardState[i+2][j] == EMPTY) {
						
						m_possibleMoves2[i+2] = VALID;
						if(i >=1 && m_boardState[i-1][j] == EMPTY) {
							m_possibleMoves2[i-1] = VALID;
						}
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
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(i < BOARD_WIDTH - 3 && 
				j >= 3 &&
				m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j-1] == PLAYER_TWO &&
					   m_boardState[i+2][j-2] == PLAYER_TWO &&
					   m_boardState[i+3][j-3] == EMPTY) {
						
						m_possibleMoves3[i+3] = VALID;
						if(i >=1 && j < BOARD_HEIGHT - 1 &&
					    m_boardState[i-1][j+1] == EMPTY) {
							m_possibleMoves3[i-1] = VALID;
						}
						m_lineFound3 = true;
						movesFound = true;
					}
					
				}
			}
		}
		
		if(movesFound) { return true; }
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(i < BOARD_WIDTH - 3 && 
				   j >= 3 &&
				   m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i+1][j-1] == PLAYER_TWO &&
					   m_boardState[i+2][j-2] == EMPTY) {
						
						m_possibleMoves2[i+2] = VALID;
						if(i >=1 && j < BOARD_HEIGHT - 1 && m_boardState[i-1][j+1] == EMPTY) {
							m_possibleMoves2[i-1] = VALID;
						}
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
			for(int j = 0; j < BOARD_HEIGHT; j++) {				
				if(i >= 3 && j >= 3 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i-1][j-1] == PLAYER_TWO &&
					   m_boardState[i-2][j-2] == PLAYER_TWO &&
					   m_boardState[i-3][j-3] == EMPTY) {
							
							m_possibleMoves3[i-3] = VALID;
							if(i < (BOARD_WIDTH - 1) && j < BOARD_HEIGHT - 1 && 
							m_boardState[i+1][j+1] == EMPTY) {
								m_possibleMoves3[i+1] = VALID;
							}
							m_lineFound3 = true;
							movesFound = true;
					}					
				}			
			}
		}
		
		if(movesFound) { return true; }
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {				
				if(i >= 2 && j >= 2 && m_boardState[i][j] == PLAYER_TWO) {
					if(m_boardState[i-1][j-1] == PLAYER_TWO &&
					   m_boardState[i-2][j-2] == EMPTY) {
							
							m_possibleMoves2[i-2] = VALID;
							if(i < (BOARD_WIDTH - 1) && j > BOARD_HEIGHT - 1 && m_boardState[i+1][j+1] == EMPTY) {
								m_possibleMoves2[i+1] = VALID;
							}
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
	
	private boolean blockPlayerCols() {
		
		boolean playerColsFound = false;
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(j >= 3 && m_boardState[i][j] == PLAYER_ONE) {
					if(m_boardState[i][j-1] == PLAYER_ONE &&
					m_boardState[i][j-2] == PLAYER_ONE &&
					m_boardState[i][j-3] == EMPTY) {
						
						m_blockColMoves[i] = VALID;
						playerColsFound = true;
					}
					
				}
			}
		}
		return playerColsFound;
	}
	
	private boolean blockPlayerRows() {
		
		boolean playerRowsFound = false;
		
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				if(i < BOARD_WIDTH - 3 && m_boardState[i][j] == PLAYER_ONE) {
					if(m_boardState[i+1][j] == PLAYER_ONE &&
					m_boardState[i+2][j] == PLAYER_ONE &&
					m_boardState[i+3][j] == EMPTY) {
						
						m_blockRowMoves[i+3] = VALID;
						
						playerRowsFound = true;
					}
					
				}
				
				if(i < BOARD_WIDTH - 3 && i >= 1 && m_boardState[i][j] == PLAYER_ONE) {
					if(m_boardState[i+1][j] == PLAYER_ONE &&
					m_boardState[i+2][j] == PLAYER_ONE &&
					m_boardState[i-1][j] == EMPTY) {
						
						m_blockRowMoves[i-1] = VALID;
						
						playerRowsFound = true;
					}
					
				}
			}
		}
		
		return playerRowsFound;
	}
	
}


