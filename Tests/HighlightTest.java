/**
 * \file HighlightTest.java
 * 
 * \author I.C. Skinner
 * 
 * \date 27 Feb '14
 * 
 * \brief Unit test class for the Highlight class.
 * 
 */

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JLabel;

import org.junit.Test;


public class HighlightTest {
	
	@Test
	public void testHighlightWinner() throws IOException{
		GameImplementation game = new Connect4();
		Highlight testHighlight = new Highlight();
		JLabel[][] board = new JLabel[game.getBoard().getBoardWidth()][game.getBoard().getBoardHeight()];
		
		for(int y = 0; y < game.getBoard().getBoardHeight(); y++){
			for(int x = 0; x < game.getBoard().getBoardWidth(); x++){
				board[x][y] = new JLabel();
			}
		}
		
		/* Player 1 has won the game */
		
		game.setWinner(1);
		
		try {
			testHighlight.C4Highlight(0, 0, board, game);
		} catch (IOException e) {
			System.out.println("Exception found");
		}
		
		/* Player 2 has won the game */
		
		game.setWinner(2);
		
		testHighlight.C4Highlight(0, 0, board, game);
	}
	
	/*
	 *	This method shows that a NullPointerException is caught when
	 *	I set the game to having no winner (a draw).
	 *
	 */
	@Test
	public void testHighlightNoWinner() throws IOException{
		GameImplementation game = new Connect4();
		Highlight testHighlight = new Highlight();
		JLabel[][] board = null;
		
		
		/* 
		 * Any number not 1 or 2 means neither player as won. 
		 * It's set as a symbolic constant 3 in our code but anything can be
		 * passed. 
		 */
		game.setWinner(3);
		
		testHighlight.C4Highlight(0, 0, board, game);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testHighlightImpossiblePosition() throws IOException{
		GameImplementation game = new Connect4();
		Highlight testHighlight = new Highlight();
		int boardWidth = game.getBoard().getBoardWidth();
		int boardHeight = game.getBoard().getBoardHeight();
		JLabel[][] board = new JLabel[boardWidth][boardHeight];
		
		/* Player 1 wins but their winning position is impossible */
		game.setWinner(1);
		testHighlight.C4Highlight(42, 42, board, game);
		
		/* Player 2 wins but their winning position is impossible */
		game.setWinner(2);
		testHighlight.C4Highlight(-42, -42, board, game);
	
	}
}