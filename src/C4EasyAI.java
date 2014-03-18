import java.util.Random;


/**
 * 
 * @file C4EasyAI.java
 * @author Thomas Werner
 * @brief Allows an 'Easy' AI to generate moves in a game of Connect 4
 * @detail An AI that generates moves by generating a number at random and checking
 * 		   if it is a valid move
 * 
 */
public class C4EasyAI {

	private final int BOARD_HEIGHT = 7;
	private final int BOARD_WIDTH = 10;
	private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;
	
	private Random m_rand;
	private int m_selectedCol;
	private boolean m_validMove;
	
	public C4EasyAI() {}
	
	
	/**
	 * Randomly selects a column using an object of the Random class until a valid move is selected
	 * 
	 * @param PC Allows the current game to be accessed
	 * @return Int representing the selected column
	 */
	public int selectCol(ProgramController PC) {
		
		m_rand = new Random();
		m_validMove = false;
		while(m_validMove == false) {
			m_selectedCol = 0;
			m_selectedCol = m_rand.nextInt(BOARD_WIDTH);
			System.out.println("Randomed: " + m_selectedCol);
			m_validMove = PC.getGame().checkValid(m_selectedCol, 0, PC.getGame().getPlayer(PLAYER_TWO));
		}
		
		return m_selectedCol;
	}

}
