import java.util.Random;


/**
 * 
 * @file OthEasyAI.java
 * @author Thomas Werner
 * @brief Allows an 'Easy' AI to generate moves in a game of Othello
 * @detail An AI that generates moves by generating 2 numbers at random and checking
 * 		   if it is a valid move
 * 
 */
public class OthEasyAI {

	private final int BOARD_ROWS = 8;
	private final int BOARD_COLS = 8;
	private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;
	
	private Random m_rand;
	private int[] m_selectedMoves;
	private int m_selectedRow;
	private int m_selectedCol;
	private boolean m_validMove;
	
	/**
	 * Randomly selects a column and row using an object of the Random class until a valid move is selected
	 * 
	 * @param PC Allows the current game to be accessed
	 * @return int[] array containing 2 indexes representing the selected x,y values
	 */
	public int[] selectMove(ProgramController PC) {
		
		m_selectedMoves = new int[2];
		m_rand = new Random();
		m_validMove = false;
		
		while(m_validMove == false) {
			m_selectedRow = m_rand.nextInt(BOARD_ROWS);
			m_selectedCol = m_rand.nextInt(BOARD_COLS);
			if(PC.getGame().checkValid(m_selectedRow, m_selectedCol, PC.getGame().getPlayer(PLAYER_TWO)) == true) {
				m_validMove = true;
			}
		}
		
		m_selectedMoves[0] = m_selectedRow;
		m_selectedMoves[1] = m_selectedCol;
		
		return m_selectedMoves;
		
	}
}
